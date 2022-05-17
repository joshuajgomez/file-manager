package com.joshgm3z.filemanager.data;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getParentId() {
        return mParentId;
    }

    public void setParentId(int mParent) {
        this.mParentId = mParent;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public List<Folder> getFolderList() {
        return mFolderList;
    }

    public void addFolder(Folder folder) {
        this.mFolderList.add(folder);
        folder.setParentId(mId);
    }

    public List<FileData> getFileDataList() {
        return mFileDataList;
    }

    public void addFileData(FileData fileData) {
        this.mFileDataList.add(fileData);
    }

    private int mId = 1;
    private int mParentId = 0;
    private String mName;
    private List<Folder> mFolderList = new ArrayList<>();
    private List<FileData> mFileDataList = new ArrayList<>();

    @Override
    public String toString() {
        return "Folder{" +
                "mId=" + mId +
                ", mParent=" + mParentId +
                ", mName='" + mName +
                ", mFolderList=" + mFolderList +
                ", mFileItemList=" + mFileDataList +
                '}';
    }
}
