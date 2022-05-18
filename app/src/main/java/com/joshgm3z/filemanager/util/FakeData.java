package com.joshgm3z.filemanager.util;

import android.content.Context;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.room.FolderDao;
import com.joshgm3z.filemanager.domain.room.FolderDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeData {

    public static void addFakeFolderData(Context context) {

        FolderDao folderDao = FolderDatabase.getInstance(context).folderDao();

        Folder root = new Folder();
        root.setName("root");
        root.setId(234);
        root.setParentId(Const.INVALID_ID);

        Folder child1 = new Folder();
        child1.setId(2);
        child1.setName("child1");
        child1.setParentId(root.getParentId());
        Folder child11 = new Folder();
        child11.setId(3);
        child11.setName("child11");
        child11.setParentId(child1.getParentId());
        child1.addFolder(child11.getId());
        Folder child12 = new Folder();
        child12.setId(4);
        child12.setName("child12");
        child12.setParentId(child1.getParentId());
        child1.addFolder(child12.getId());

        Folder child2 = new Folder();
        child2.setId(5);
        child2.setName("child2");
        child2.setParentId(root.getParentId());
        Folder child21 = new Folder();
        child21.setId(6);
        child21.setName("child21");
        child21.setParentId(child2.getParentId());
        child2.addFolder(child21.getId());

        Folder child3 = new Folder();
        child3.setId(7);
        child3.setName("child3");
        child3.setParentId(root.getParentId());

        root.addFolder(child1.getId());
        root.addFolder(child2.getId());
        root.addFolder(child3.getId());

        Logger.a("root: " + root);

        folderDao.add(child1);
        folderDao.add(child11);
        folderDao.add(child12);
        folderDao.add(child2);
        folderDao.add(child21);
        folderDao.add(child3);
        folderDao.add(root);
    }

}
