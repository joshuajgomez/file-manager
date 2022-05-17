package com.joshgm3z.filemanager.domain;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.util.FakeData;

import java.util.List;

public class FolderRepository {

    public List<Folder> getFolderStructure() {
        return FakeData.getFolderData();
    }

}
