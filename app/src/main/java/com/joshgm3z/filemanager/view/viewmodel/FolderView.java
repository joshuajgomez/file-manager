package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.domain.data.FileData;

import java.util.List;

public interface FolderView {

    void updateFolderContent(List<FileData> fileDataList);

    void updateFolderPath(List<FileData> fileDataList);

    void setFolderName(String name);

    void showBackArrow(boolean isShow);

    void showContentEmptyText(boolean isVisible);
}
