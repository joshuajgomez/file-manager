package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.FolderRepository;

import java.util.List;

public class FolderViewModel {

    private FolderRepository mFolderRepository;
    private FolderView mView;

    public FolderViewModel(FolderRepository folderRepository, FolderView view) {
        mFolderRepository = folderRepository;
        mView = view;
    }

    public void onUiResume() {
        List<Folder> folderStructure = mFolderRepository.getFolderStructure();
        mView.updateFolderList(folderStructure);
    }
}
