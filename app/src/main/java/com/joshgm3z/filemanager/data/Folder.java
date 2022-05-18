package com.joshgm3z.filemanager.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Folder {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int parentId;
    private String name;

    public void setChildFolderIdList(List<Integer> childFolderIdList) {
        this.childFolderIdList = childFolderIdList;
    }

    public void setChildFileDataIdList(List<Integer> childFileDataIdList) {
        this.childFileDataIdList = childFileDataIdList;
    }

    private List<Integer> childFolderIdList = new ArrayList<>();
    private List<Integer> childFileDataIdList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getChildFolderIdList() {
        return childFolderIdList;
    }

    public void addFolder(Folder folder) {
        this.childFolderIdList.add(folder.getId());
        folder.setParentId(getId());
    }

    public List<Integer> getChildFileDataIdList() {
        return childFileDataIdList;
    }

    public void addFileData(FileData fileData) {
        this.childFileDataIdList.add(fileData.getId());
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", parent_id=" + parentId +
                ", name='" + name +
                ", folder_list=" + childFolderIdList +
                ", file_data_list=" + childFileDataIdList +
                '}';
    }
}
