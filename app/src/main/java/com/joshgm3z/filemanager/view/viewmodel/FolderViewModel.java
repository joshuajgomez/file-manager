package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.data.FileData;
import com.joshgm3z.filemanager.domain.FolderRepository;

import java.util.ArrayList;
import java.util.List;

public class FolderViewModel {

    private FolderRepository mFolderRepository;
    private FolderView mView;
    private FileData mCurrentFolder = null;

    public FolderViewModel(FolderRepository folderRepository, FolderView view) {
        mFolderRepository = folderRepository;
        mView = view;
    }

    public void refreshContent() {
        if (mCurrentFolder != null) {
            List<FileData> fileDataList = mFolderRepository.getFolderContent(mCurrentFolder.getId());
            if (fileDataList.isEmpty()) {
                mView.showContentEmptyText(true);
            } else {
                mView.showContentEmptyText(false);
                mView.updateFolderContent(fileDataList);
            }
            List<FileData> folderPathList = mFolderRepository.getFolderPath(mCurrentFolder.getId());
            mView.updateFolderPath(folderPathList);
            mView.setFolderName(mCurrentFolder.getName());
            mView.showBackArrow(true);
        } else {
            // load root folder list
            mFolderRepository.getRootFolderList();
            FileData rootFolder = mFolderRepository.getRootFolder();
            if (rootFolder != null) {
                List<FileData> list = new ArrayList<>();
                mView.showContentEmptyText(false);
                list.add(rootFolder);
                mView.updateFolderContent(list);
            }
            mView.showBackArrow(false);
            mView.setFolderName(null);
        }
    }

    public void updateCurrentFolder(FileData currentFolder) {
        mCurrentFolder = currentFolder;
        refreshContent();
    }

    public FileData getCurrentFolder() {
        return mCurrentFolder;
    }

    public void goToParentFolder() {
        long parentId = mCurrentFolder.getParentId();
        mCurrentFolder = mFolderRepository.getFolder(parentId);
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
