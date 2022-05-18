package com.joshgm3z.filemanager.domain;

import android.content.Context;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.room.FolderDao;
import com.joshgm3z.filemanager.domain.room.FolderDatabase;
import com.joshgm3z.filemanager.util.Const;

import java.util.ArrayList;
import java.util.List;

public class FolderRepository {

    private Context context;
    private FolderDao mFolderDao;

    public FolderRepository(Context context) {
        this.context = context;
//        FakeData.addFakeFolderData(context);
        mFolderDao = FolderDatabase.getInstance(context).folderDao();
    }

    public void addFolder(Folder folder) {
        mFolderDao.add(folder);
    }

    public List<Folder> getFolderContent(long folderId) {
        Folder folder = mFolderDao.getFolder(folderId);
        List<Long> folderIdList = folder.getChildFolderIdList();
        List<Folder> folderList = new ArrayList<>();
        for (Long id : folderIdList) {
            folderList.add(mFolderDao.getFolder(id));
        }
        return folderList;
    }

    public List<Folder> getFolderPath(long folderId) {
        Folder folder = mFolderDao.getFolder(folderId);
        List<Folder> folderPathList = new ArrayList<>();
        while (folder.getParentId() != Const.INVALID_ID) {
            folder = mFolderDao.getFolder(folder.getParentId());
            folderPathList.add(folder);
        }
        return folderPathList;
    }

    public Folder getRootFolder() {
        return mFolderDao.getRootFolder();
    }

    public Folder getFolder(long id) {
        return mFolderDao.getFolder(id);
    }

    public void createNewFolder(Folder parentFolder, String folderName) {
        Folder folder = new Folder();
        folder.setName(folderName);
        folder.setParentId(parentFolder.getId());
        long folderId = mFolderDao.add(folder);
        parentFolder.addFolder(folderId);
        mFolderDao.update(parentFolder);
    }
}
