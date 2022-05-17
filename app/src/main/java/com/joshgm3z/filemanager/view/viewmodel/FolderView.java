package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.data.Folder;

import java.util.List;

public interface FolderView {

    void updateFolderList(List<Folder> folderList);

    void updateFolderPath(List<Folder> folderList);

}
