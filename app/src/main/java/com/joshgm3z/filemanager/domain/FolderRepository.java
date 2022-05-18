package com.joshgm3z.filemanager.domain;

import android.content.Context;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.room.FolderDao;
import com.joshgm3z.filemanager.domain.room.FolderDatabase;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.FakeData;

import java.util.ArrayList;
import java.util.List;

public class FolderRepository {

    private Context context;
    private FolderDao folderDao;

    public FolderRepository(Context context) {
        this.context = context;
//        FakeData.addFakeFolderData(context);
        folderDao = FolderDatabase.getInstance(context).folderDao();
    }

    public void addFolder(Folder folder) {
        folderDao.add(folder);
    }

    public List<Folder> getFolderContent(int folderId) {
        Folder folder = folderDao.getFolder(folderId);
        List<Integer> folderIdList = folder.getChildFolderIdList();
        List<Folder> folderList = new ArrayList<>();
        for (Integer id : folderIdList) {
            folderList.add(folderDao.getFolder(id));
        }
        return folderList;
    }

    public List<Folder> getFolderPath(int folderId) {
        Folder folder = folderDao.getFolder(folderId);
        List<Folder> folderPathList = new ArrayList<>();
        while (folder.getParentId() != Const.INVALID_ID) {
            folder = folderDao.getFolder(folder.getParentId());
            folderPathList.add(folder);
        }
        return folderPathList;
    }

    public Folder getRootFolder() {
        return folderDao.getRootFolder();
    }
}
