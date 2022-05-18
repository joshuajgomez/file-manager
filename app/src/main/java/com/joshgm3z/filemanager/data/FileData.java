package com.joshgm3z.filemanager.data;

public class FileData {

    private String mName;
    private long mId = 2;
    private Folder mParent;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public Folder getParent() {
        return mParent;
    }

    public void setParent(Folder mParent) {
        this.mParent = mParent;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "mName='" + mName +
                ", mId=" + mId +
                ", mParent=" + mParent +
                '}';
    }
}
