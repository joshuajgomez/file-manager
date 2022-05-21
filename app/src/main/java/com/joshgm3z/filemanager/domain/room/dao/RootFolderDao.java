package com.joshgm3z.filemanager.domain.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joshgm3z.filemanager.data.FileData;
import com.joshgm3z.filemanager.data.RootFolder;

import java.util.List;

@Dao
public interface RootFolderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long add(RootFolder rootFolder);

    @Update
    int update(RootFolder rootFolder);

    @Query("select * from RootFolder")
    List<RootFolder> getAll();

    @Delete
    void delete(RootFolder rootFolder);

    @Query("select * from RootFolder where id = :id")
    RootFolder getRootFolder(long id);

}
