package com.joshgm3z.filemanager.domain.data;

public class FileData {

    private String name;
    private long sourceId;
    private double size;
    private boolean isFolder;
    private String url;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileData{" +
                ", name='" + name + '\'' +
                ", sourceId=" + sourceId +
                ", size=" + size +
                ", isFolder=" + isFolder +
                ", url='" + url + '\'' +
                ", type=" + type +
                '}';
    }
}
