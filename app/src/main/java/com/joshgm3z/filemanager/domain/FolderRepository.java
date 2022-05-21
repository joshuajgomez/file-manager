package com.joshgm3z.filemanager.domain;

import android.content.Context;

import com.joshgm3z.filemanager.data.FileData;
import com.joshgm3z.filemanager.data.RootFolder;
import com.joshgm3z.filemanager.domain.room.dao.FolderDao;
import com.joshgm3z.filemanager.domain.room.FolderDatabase;
import com.joshgm3z.filemanager.domain.room.dao.RootFolderDao;
import com.joshgm3z.filemanager.util.Const;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FolderRepository {

    private Context context;
    private FolderDao mFolderDao;
    private RootFolderDao mRootFolderDao;

    public FolderRepository(Context context) {
        this.context = context;
//        FakeData.addFakeFolderData(context);
        FolderDatabase instance = FolderDatabase.getInstance(context);
        mFolderDao = instance.folderDao();
        mRootFolderDao = instance.rootFolderDao();
    }

    public void addFolder(FileData fileData) {
        mFolderDao.add(fileData);
    }

    public List<FileData> getFolderContent(long folderId) {
        FileData fileData = mFolderDao.getFolder(folderId);
        List<Long> folderIdList = fileData.getChildFolderIdList();
        List<FileData> fileDataList = new ArrayList<>();
        for (Long id : folderIdList) {
            fileDataList.add(mFolderDao.getFolder(id));
        }
        return fileDataList;
    }

    public List<FileData> getFolderPath(long folderId) {
        FileData fileData = mFolderDao.getFolder(folderId);
        List<FileData> folderPathList = new ArrayList<>();
        folderPathList.add(fileData);
        while (fileData.getParentId() != Const.INVALID_ID) {
            fileData = mFolderDao.getFolder(fileData.getParentId());
            folderPathList.add(fileData);
        }
        Collections.reverse(folderPathList);
        return folderPathList;
    }

    public FileData getRootFolder() {
        return mFolderDao.getRootFolder();
    }

    public FileData getFolder(long id) {
        return mFolderDao.getFolder(id);
    }

    public void createNewFolder(FileData parentFolder, String folderName) {
        FileData fileData = new FileData();
        fileData.setName(folderName);
        fileData.setParentId(parentFolder.getId());
        long folderId = mFolderDao.add(fileData);
        parentFolder.addFolder(folderId);
        mFolderDao.update(parentFolder);
    }

    public List<RootFolder> getRootFolderList() {
        List<RootFolder> rootFolderList = mRootFolderDao.getAll();
        return rootFolderList;
    }
}
