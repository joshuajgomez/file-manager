package com.joshgm3z.filemanager.domain.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joshgm3z.filemanager.data.Folder;

import java.util.List;

@Dao
public interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(Folder folder);

    @Update
    int update(Folder folder);

    @Query("select * from Folder")
    List<Folder> getAll();

    @Delete
    void delete(Folder folder);

    @Query("select * from Folder where id = :id")
    Folder getFolder(long id);

    @Query("select * from Folder where parentId = -1")
    Folder getRootFolder();
}
