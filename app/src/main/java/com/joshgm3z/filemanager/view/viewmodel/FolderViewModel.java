package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.FolderRepository;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.FakeData;

import java.util.ArrayList;
import java.util.List;

public class FolderViewModel {

    private FolderRepository mFolderRepository;
    private FolderView mView;
    private Folder mCurrentFolder;

    public FolderViewModel(FolderRepository folderRepository, FolderView view) {
        mFolderRepository = folderRepository;
        mView = view;
        mCurrentFolder = mFolderRepository.getRootFolder();
    }

    public void onUiResume() {
        if (mCurrentFolder.getParentId() != Const.INVALID_ID) {
            List<Folder> folderList = mFolderRepository.getFolderContent(mCurrentFolder.getId());
            mView.updateFolderContent(folderList);

            List<Folder> folderPathList = mFolderRepository.getFolderPath(mCurrentFolder.getId());
            mView.updateFolderPath(folderPathList);
        } else {
            List<Folder> list = new ArrayList<>();
            list.add(mCurrentFolder);
            mView.updateFolderContent(list);
        }
    }
}
