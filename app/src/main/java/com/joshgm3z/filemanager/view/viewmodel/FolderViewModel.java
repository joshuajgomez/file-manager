package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.FolderRepository;
import com.joshgm3z.filemanager.domain.data.Source;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.Logger;

import java.io.File;
import java.util.List;
import java.util.Stack;

public class FolderViewModel {

    private FolderRepository mFolderRepository;
    private FolderView mView;
    private String mCurrentFolderUrl = null;
    private String mSourceUrl = null;
    private String mCurrentFolderName = null;
    private Stack<FileData> mCurrentPathList = new Stack<>();

    public FolderViewModel(FolderRepository folderRepository, FolderView view) {
        mFolderRepository = folderRepository;
        mView = view;
    }

    public void refreshContent() {
        if (mCurrentFolderUrl != null) {
            // load file in folder
            List<FileData> fileDataList = mFolderRepository.getFolderContent(mCurrentFolderUrl);
            if (fileDataList.isEmpty()) {
                mView.showContentEmptyText(true);
            } else {
                mView.showContentEmptyText(false);
                mView.updateFolderContent(fileDataList);
            }
            mView.showBackArrow(true);
            mView.setFolderName(mCurrentFolderName);
            mView.updateFolderPath(mCurrentPathList);
        } else {
            // load source list
            List<Source> sourceFolderList = mFolderRepository.getSourceList();
            List<FileData> fileDataList = DataConverter.convertToFileDataList(sourceFolderList);
            mView.showContentEmptyText(false);
            mView.updateFolderContent(fileDataList);
            mView.showBackArrow(false);
            mView.setFolderName(null);
            mView.updateFolderPath(null);
        }
        checkWritePermission();
    }

    private void checkWritePermission() {
        if (mCurrentFolderUrl != null
                && mFolderRepository.getWriteState(mCurrentFolderUrl)
                && mCurrentPathList != null
                && !mCurrentPathList.isEmpty()) {
            mView.showNewFolderOption(true);
        } else {
            mView.showNewFolderOption(false);
        }
    }

    public void onFileClick(FileData currentFileData) {
        mCurrentFolderUrl = currentFileData.getUrl();
        switch (currentFileData.getType()) {
            case Const.FileType.ROOT_EXT_STORAGE:
            case Const.FileType.ROOT_INT_STORAGE:
            case Const.FileType.ROOT_CLOUD: {
                mCurrentFolderName = currentFileData.getName();
                mSourceUrl = currentFileData.getUrl();
            }
        }
        if (currentFileData.getType() != Const.FileType.FILE) {
            // update folder path
            mCurrentPathList.add(currentFileData);
        }
        refreshContent();
    }

    public void onPathClick(FileData fileData) {
        mCurrentFolderUrl = fileData.getUrl();
        if (!mCurrentPathList.isEmpty()) {
            while (!mCurrentPathList.lastElement().getUrl()
                    .equals(fileData.getUrl())) {
                mCurrentPathList.pop();
            }
        }
        refreshContent();
    }

    public String getCurrentFolderUrl() {
        return mCurrentFolderUrl;
    }

    public void goToParentFolder() {
        if (mCurrentFolderUrl.equals(mSourceUrl)) {
            // go to source list
            mCurrentFolderUrl = null;
            mCurrentPathList.clear();
        } else {
            // go to parent folder
            File file = new File(mCurrentFolderUrl);
            mCurrentFolderUrl = file.getParent();
            mCurrentPathList.remove(mCurrentPathList.size() - 1);
        }
        Logger.a("mCurrentFolder: " + mCurrentFolderUrl);
        refreshContent();
    }

    public void onNewFolderClick(String folderName) {
        if (mFolderRepository.createNewFolder(mCurrentFolderUrl, folderName)) {
            // new folder created
            mView.showMessage(folderName + " created");
        } else {
            // not created
            mView.showMessage("Unable to create new folder");
        }
        refreshContent();
    }

    public void onHomeIconPress() {
        mCurrentFolderUrl = null;
        mCurrentPathList.clear();
        refreshContent();
    }
}
