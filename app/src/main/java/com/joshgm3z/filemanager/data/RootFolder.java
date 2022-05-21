package com.joshgm3z.filemanager.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.joshgm3z.filemanager.util.Const;

import java.util.List;

@Entity
public class RootFolder {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private int type;
    private String url;
    private List<Long> childIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setType(@Const.RootType int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Long> getChildIds() {
        return childIds;
    }

    public void setChildIds(List<Long> childIds) {
        this.childIds = childIds;
    }
}
