package com.joshgm3z.filemanager.util;

import com.joshgm3z.filemanager.data.Folder;

import java.util.ArrayList;
import java.util.List;

public class FakeData {

    public static List<Folder> getFolderData() {
        List<Folder> folderList = new ArrayList<>();
        Folder root = new Folder();
        root.setName("root");
        root.setParentId(-1);

        Folder child1 = new Folder();
        child1.setName("child1");
        Folder child11 = new Folder();
        child11.setName("child11");
        child1.addFolder(child11);
        Folder child12 = new Folder();
        child12.setName("child12");
        child1.addFolder(child12);

        Folder child2 = new Folder();
        child2.setName("child2");
        Folder child21 = new Folder();
        child21.setName("child21");
        child2.addFolder(child21);

        Folder child3 = new Folder();
        child3.setName("child3");

        root.addFolder(child1);
        root.addFolder(child2);
        root.addFolder(child3);

        folderList.add(root);
        return folderList;
    }

}
