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
import java.util.ArrayList;
import java.util.List;

public class FileAccessManager {

    private SourceDao mSourceDao;

    public FileAccessManager(Context context) {
        AppDatabase folderDatabase = AppDatabase.getInstance(context);
        this.mSourceDao = folderDatabase.sourceDao();
    }

    public void loadFiles(File dir) {
        Logger.a("dir" + dir);
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
        Logger.a("path: " + path);
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
}
