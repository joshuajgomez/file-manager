package com.joshgm3z.filemanager.domain;

import android.content.Context;
import android.os.Environment;

import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.data.Source;
import com.joshgm3z.filemanager.domain.room.AppDatabase;
import com.joshgm3z.filemanager.domain.room.dao.SourceDao;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileAccessManager {

    private SourceDao mSourceDao;

    public FileAccessManager(Context context) {
        AppDatabase folderDatabase = AppDatabase.getInstance(context);
        this.mSourceDao = folderDatabase.sourceDao();
    }

    public void loadFiles(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
//                Logger.a("listFile[i].getName()" + listFile[i].getName());
                createFileData(listFile[i]);
            }
        }
    }

    private FileData createFileData(File file) {
        FileData fileData = new FileData();
        fileData.setName(file.getName());
        fileData.setType(file.isDirectory() ? Const.FileType.FOLDER : Const.FileType.FILE);
        fileData.setName(file.getName());
        fileData.setUrl(file.getAbsolutePath());
        return fileData;
    }

    public Source initRootExtStorage() {
        Source source = new Source();
        if (!isRootExtStorageAdded()) {
            File extStorage = Environment.getExternalStorageDirectory();
            source.setName(Const.NAME_EXT_STORAGE);
            source.setUrl(extStorage.getAbsolutePath());
            source.setType(Const.FileType.ROOT_EXT_STORAGE);
        }
        return source;
    }

    public Source initRootInternalStorage() {
        Source source = new Source();
        if (!isRootInternalStorageAdded()) {
            File internalStorage = Environment.getStorageDirectory();
            source.setName(Const.NAME_INT_STORAGE);
            source.setUrl(internalStorage.getAbsolutePath());
            source.setType(Const.FileType.ROOT_INT_STORAGE);
        }
        return source;
    }

    private boolean isRootExtStorageAdded() {
        return !mSourceDao
                .getSourceByType(Const.FileType.ROOT_EXT_STORAGE)
                .isEmpty();
    }

    private boolean isRootInternalStorageAdded() {
        return !mSourceDao
                .getSourceByType(Const.FileType.ROOT_INT_STORAGE)
                .isEmpty();
    }

    public List<FileData> getFolderContent(String currentFolder) {
        File currentFile = new File(currentFolder);
        File[] files = currentFile.listFiles();
        List<FileData> fileDataList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                fileDataList.add(createFileData(file));
            }
        }
        return fileDataList;
    }

    public boolean getWriteState(String path) {
        return Environment.MEDIA_MOUNTED.equals(
                Environment.getExternalStorageState(new File(path)));
    }

    public boolean createNewFolder(String parentFolder, String folderName) {
        File file = new File(parentFolder + "/" + folderName);
        return file.mkdir();
    }

    public boolean isNameExists(String folder, String name) {
        return new File(folder + "/" + name).exists();
    }

    public boolean renameFile(String selectedFile, String name) {
        File file = new File(selectedFile);
        return file.renameTo(new File(file.getParent() + "/" + name));
    }

    public boolean copyFile(String inputFilePath, String outputFilePath) {
        Logger.a("in: [" + inputFilePath + "], out: [" + outputFilePath + "]");
        String inputFileName = getFileName(inputFilePath);
        InputStream in = null;
        OutputStream out = null;
        boolean isSuccess = false;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputFilePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            in = new FileInputStream(inputFilePath);
            out = new FileOutputStream(outputFilePath + "/" + inputFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();

            // write the output file
            out.flush();
            out.close();

            isSuccess = true;
        } catch (FileNotFoundException fileNotFoundException) {
            Logger.e("FileNotFoundException: " + fileNotFoundException.getMessage());

        } catch (Exception e) {
            Logger.e("Exception: " + e.getMessage());
        }
        return isSuccess;
    }

    private boolean deleteFile(String inputFile) {
        // delete the original file
        return new File(inputFile).delete();
    }

    public String getFileName(String fileDataUrl) {
        return new File(fileDataUrl).getName();
    }
}
