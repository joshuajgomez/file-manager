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
    private String mCopiedFilePath;

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
        if (currentFileData.getType() == Const.FileType.FOLDER
                || currentFileData.getType() == Const.FileType.ROOT_EXT_STORAGE
                || currentFileData.getType() == Const.FileType.ROOT_INT_STORAGE
        ) {
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
        } else {
            mView.showMessage("Unable to open file");
        }
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

    public void onOptionsClick(int option, String fileDataUrl) {
        Logger.a("option: " + option + ", fileDataUrl: " + fileDataUrl);
    }

    public boolean isNameExists(String name) {
        return mFolderRepository.isNameExists(mCurrentFolderUrl, name);
    }

    public void onRenameClick(String selectedFile, String name) {
        if (mFolderRepository.rename(selectedFile, name)) {
            // folder renames
            mView.showMessage(name + " renamed");
        } else {
            // rename failed
            mView.showMessage("Unable to rename folder");
        }
        refreshContent();
    }

    public String getFileName(String fileDataUrl) {
        return mFolderRepository.getFileName(fileDataUrl);
    }

    public void onCopyClick(String fileDataUrl) {
        mCopiedFilePath = fileDataUrl;
        mView.showPasteIcon(true);
        mView.showMessage(mFolderRepository.getFileName(fileDataUrl) + " copied");
    }

    public void onPasteClick() {
        if (mCopiedFilePath != null) {
            if (mFolderRepository.copyFile(mCopiedFilePath, mCurrentFolderUrl)) {
                mView.showMessage(mFolderRepository.getFileName(mCopiedFilePath) + " copied to current folder");
                mView.showPasteIcon(false);
                refreshContent();
            } else {
                mView.showMessage(mFolderRepository.getFileName("Unable to copy file"));
            }
        } else {
            mView.showMessage("No file copied");
            mView.showPasteIcon(false);
        }
    }
}
