package com.joshgm3z.filemanager.domain.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Source {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String url;
    private String name;
    private int type;
    private boolean isWritePermission;
    private int size;
    private int freeSpace;

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", isWritePermission=" + isWritePermission +
                ", size=" + size +
                ", freeSpace=" + freeSpace +
                ", totalSpace=" + totalSpace +
                '}';
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    private int totalSpace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isWritePermission() {
        return isWritePermission;
    }

    public void setWritePermission(boolean writePermission) {
        isWritePermission = writePermission;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
