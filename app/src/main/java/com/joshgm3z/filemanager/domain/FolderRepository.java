package com.joshgm3z.filemanager.domain;

import android.content.Context;

import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.data.Source;
import com.joshgm3z.filemanager.domain.room.AppDatabase;
import com.joshgm3z.filemanager.domain.room.dao.SourceDao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FolderRepository {

    private SourceDao mSourceDao;
    private FileAccessManager mFileAccessManager;

    public FolderRepository(Context context, FileAccessManager fileAccessManager) {
        mFileAccessManager = fileAccessManager;
//        FakeData.addFakeFolderData(context);
        mSourceDao = AppDatabase.getInstance(context).sourceDao();
        mFileAccessManager.initRootExtStorage();
        mFileAccessManager.initRootInternalStorage();
    }

    public void addSource(String url, int type, String name) {
        mSourceDao.add(new Source());
    }

    public List<FileData> getFolderContent(String mCurrentFolder) {
        return mFileAccessManager.getFolderContent(mCurrentFolder);
    }

    public boolean createNewFolder(String parentFolder, String folderName) {
        return mFileAccessManager.createNewFolder(parentFolder, folderName);
    }

    public List<Source> getSourceList() {
        List<Source> sourceList = mSourceDao.getAll();
        Source extSource = mFileAccessManager.initRootExtStorage();
        Source intSource = mFileAccessManager.initRootInternalStorage();
        sourceList.add(extSource);
        sourceList.add(intSource);
        return sourceList;
    }

    public boolean getWriteState(String path) {
        return mFileAccessManager.getWriteState(path);
    }

    public boolean isNameExists(String folder, String name) {
        return mFileAccessManager.isNameExists(folder, name);
    }

    public boolean rename(String selectedFile, String name) {
        return mFileAccessManager.renameFile(selectedFile, name);
    }

    public String getFileName(String fileDataUrl) {
        return mFileAccessManager.getFileName(fileDataUrl);
    }

    public boolean copyFile(String inputFilePath, String outputFilePath) {
        return mFileAccessManager.copyFile(inputFilePath, outputFilePath);
    }
}
