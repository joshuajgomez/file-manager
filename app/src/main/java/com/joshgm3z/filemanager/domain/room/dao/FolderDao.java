package com.joshgm3z.filemanager.domain.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joshgm3z.filemanager.data.FileData;

import java.util.List;

@Dao
public interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(FileData fileData);

    @Update
    int update(FileData fileData);

    @Query("select * from Folder")
    List<FileData> getAll();

    @Delete
    void delete(FileData fileData);

    @Query("select * from Folder where id = :id")
    FileData getFolder(long id);

    @Query("select * from Folder where parentId = -1")
    FileData getRootFolder();
}
