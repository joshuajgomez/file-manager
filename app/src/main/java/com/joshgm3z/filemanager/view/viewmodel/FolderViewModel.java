package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.FolderRepository;

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
            mView.updateFolderContent(folderList);

            List<Folder> folderPathList = mFolderRepository.getFolderPath(mCurrentFolder.getId());
            mView.updateFolderPath(folderPathList);
            mView.setFolderName(mCurrentFolder.getName());
            mView.showBackArrow(true);
        } else {
            List<Folder> list = new ArrayList<>();
            list.add(mFolderRepository.getRootFolder());
            mView.updateFolderContent(list);
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
        int parentId = mCurrentFolder.getParentId();
        mCurrentFolder = mFolderRepository.getFolder(parentId);
        refreshContent();
    }
}
