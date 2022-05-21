package com.joshgm3z.filemanager.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FileData {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long parentId;
    private String name;
    private long rootId;
    private double size;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public long getRootId() {
        return rootId;
    }

    public void setRootId(long rootId) {
        this.rootId = rootId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    private boolean isFolder;

    public void setChildFolderIdList(List<Long> childFolderIdList) {
        this.childFolderIdList = childFolderIdList;
    }

    public void setChildFileDataIdList(List<Long> childFileDataIdList) {
        this.childFileDataIdList = childFileDataIdList;
    }

    private List<Long> childFolderIdList = new ArrayList<>();
    private List<Long> childFileDataIdList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getChildFolderIdList() {
        return childFolderIdList;
    }

    public void addFolder(long folderId) {
        this.childFolderIdList.add(folderId);
    }

    public List<Long> getChildFileDataIdList() {
        return childFileDataIdList;
    }

    public void addFileData(FileData fileData) {
        this.childFileDataIdList.add(fileData.getId());
    }

    @Override
    public String toString() {
        return "FileData{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", rootId=" + rootId +
                ", size=" + size +
                ", isFolder=" + isFolder +
                ", childFolderIdList=" + childFolderIdList +
                ", childFileDataIdList=" + childFileDataIdList +
                '}';
    }
}
