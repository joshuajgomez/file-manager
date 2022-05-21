package com.joshgm3z.filemanager.domain.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.data.Source;

import java.util.List;

@Dao
public interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(Source source);

    @Update
    int update(Source source);

    @Query("select * from Source")
    List<Source> getAll();

    @Delete
    void delete(Source source);

    @Query("select * from Source where id = :id")
    Source getSource(long id);

    @Query("select * from Source where type = :type")
    List<Source> getSourceByType(int type);

}
