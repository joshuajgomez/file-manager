package com.joshgm3z.filemanager.domain;

import android.content.Context;

import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.data.Source;
import com.joshgm3z.filemanager.domain.room.AppDatabase;
import com.joshgm3z.filemanager.domain.room.dao.SourceDao;

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

    public List<FileData> getFolderPath(long folderId) {
//        FileData fileData = mSourceDao.getFileData(folderId);
//        List<FileData> folderPathList = new ArrayList<>();
//        folderPathList.add(fileData);
//
//        Collections.reverse(folderPathList);
        return new ArrayList<>();
    }

    public void createNewFolder(String parentFolder, String folderName) {

    }

    public List<Source> getSourceList() {
        List<Source> sourceList = mSourceDao.getAll();
        Source extSource = mFileAccessManager.initRootExtStorage();
        Source intSource = mFileAccessManager.initRootInternalStorage();
        sourceList.add(extSource);
        sourceList.add(intSource);
        return sourceList;
    }
}
