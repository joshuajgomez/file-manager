package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.FolderRepository;
import com.joshgm3z.filemanager.domain.data.Source;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderViewModel {

    private FolderRepository mFolderRepository;
    private FolderView mView;
    private String mCurrentFolder = null;
    private String mSourceUrl = null;
    private String mCurrentFolderName = null;

    public FolderViewModel(FolderRepository folderRepository, FolderView view) {
        mFolderRepository = folderRepository;
        mView = view;
    }

    public void refreshContent() {
        Logger.a("mCurrentFolder: " + mCurrentFolder);
        if (mCurrentFolder != null) {
            // load file in folder
            List<FileData> fileDataList = mFolderRepository.getFolderContent(mCurrentFolder);
            if (fileDataList.isEmpty()) {
                mView.showContentEmptyText(true);
            } else {
                mView.showContentEmptyText(false);
                mView.updateFolderContent(fileDataList);
            }
            updateFolderPath();
            mView.showBackArrow(true);
            mView.setFolderName(mCurrentFolderName);
        } else {
            // load source list
            List<Source> sourceFolderList = mFolderRepository.getSourceList();
            List<FileData> fileDataList = DataConverter.convertToFileDataList(sourceFolderList);
            mView.showContentEmptyText(false);
            mView.updateFolderContent(fileDataList);
            mView.showBackArrow(false);
            mView.setFolderName(null);
        }
    }

    private void updateFolderPath() {
//        mView.updateFolderPath(null);
    }

    public void onFileClick(FileData currentFileData) {
        mCurrentFolder = currentFileData.getUrl();
        switch (currentFileData.getType()) {
            case Const.FileType.ROOT_EXT_STORAGE:
            case Const.FileType.ROOT_INT_STORAGE:
            case Const.FileType.ROOT_CLOUD: {
                mCurrentFolderName = currentFileData.getName();
                mSourceUrl = currentFileData.getUrl();
            }
        }
        refreshContent();
    }

    public String getCurrentFolder() {
        return mCurrentFolder;
    }

    public void goToParentFolder() {
        if (mCurrentFolder.equals(mSourceUrl)) {
            // go to source list
            mCurrentFolder = null;
        } else {
            // go to parent folder
            File file = new File(mCurrentFolder);
            mCurrentFolder = file.getParent();
        }
        Logger.a("mCurrentFolder: " + mCurrentFolder);
        refreshContent();
    }

    public void onNewFolderClick(String folderName) {
        mFolderRepository.createNewFolder(mCurrentFolder, folderName);
        refreshContent();
    }

    public void onHomeIconPress() {
        mCurrentFolder = null;
        refreshContent();
    }
}
