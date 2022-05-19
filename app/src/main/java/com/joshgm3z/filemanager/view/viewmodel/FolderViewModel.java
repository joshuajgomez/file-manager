package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.FolderRepository;
import com.joshgm3z.filemanager.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class FolderViewModel {

    private FolderRepository mFolderRepository;
    private FolderView mView;
    private Folder mCurrentFolder = null;

    public FolderViewModel(FolderRepository folderRepository, FolderView view) {
        mFolderRepository = folderRepository;
        mView = view;
    }

    public void refreshContent() {
        if (mCurrentFolder != null) {
            List<Folder> folderList = mFolderRepository.getFolderContent(mCurrentFolder.getId());
            if (folderList.isEmpty()) {
                mView.showContentEmptyText(true);
            } else {
                mView.showContentEmptyText(false);
                mView.updateFolderContent(folderList);
            }
            List<Folder> folderPathList = mFolderRepository.getFolderPath(mCurrentFolder.getId());
            mView.updateFolderPath(folderPathList);
            mView.setFolderName(mCurrentFolder.getName());
            mView.showBackArrow(true);
        } else {
            Folder rootFolder = mFolderRepository.getRootFolder();
            Logger.a("rootFolder: " + rootFolder);
            if (rootFolder != null) {
                List<Folder> list = new ArrayList<>();
                mView.showContentEmptyText(false);
                list.add(rootFolder);
                mView.updateFolderContent(list);
            }
            mView.showBackArrow(false);
            mView.setFolderName(null);
        }
    }

    public void updateCurrentFolder(Folder currentFolder) {
        mCurrentFolder = currentFolder;
        refreshContent();
    }

    public Folder getCurrentFolder() {
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
