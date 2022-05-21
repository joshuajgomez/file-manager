package com.joshgm3z.filemanager.domain;

import android.content.Context;
import android.os.Environment;

import com.joshgm3z.filemanager.data.FileData;
import com.joshgm3z.filemanager.data.RootFolder;
import com.joshgm3z.filemanager.domain.room.dao.FolderDao;
import com.joshgm3z.filemanager.domain.room.FolderDatabase;
import com.joshgm3z.filemanager.domain.room.dao.RootFolderDao;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.Logger;

import java.io.File;

public class FileAccessManager {

    private FolderDao mFolderDao;
    private RootFolderDao mRootFolderDao;

    public FileAccessManager(Context context) {
        FolderDatabase folderDatabase = FolderDatabase.getInstance(context);
        this.mFolderDao = folderDatabase.folderDao();
        this.mRootFolderDao = folderDatabase.rootFolderDao();
    }

    public void loadFiles(File dir) {
        Logger.a("dir" + dir);
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                Logger.a("listFile[i].getName()" + listFile[i].getName());
                if (listFile[i].isDirectory()) {
                    createFolder(listFile[i]);
                    loadFiles(listFile[i]);
                } else {
//                    if (listFile[i].getName().endsWith(pdfPattern)) {
//
//                    }
                }
            }
        }
    }

    private void createFolder(File file) {
        FileData fileData = new FileData();
        fileData.setName(file.getName());
        fileData.setParentId(223);
        mFolderDao.add(fileData);
    }

    public void initRoot() {
        RootFolder rootFolder = new RootFolder();
        File extStorage = Environment.getExternalStorageDirectory();
        rootFolder.setId(0);
        rootFolder.setName(extStorage.getName());
        rootFolder.setUrl(extStorage.getAbsolutePath());
        rootFolder.setType(Const.RootType.EXT_STORAGE);

        mRootFolderDao.add(rootFolder);
    }

}
