package com.joshgm3z.filemanager.domain.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.joshgm3z.filemanager.domain.data.Source;
import com.joshgm3z.filemanager.domain.room.dao.SourceDao;

@Database(version = 6, entities = {Source.class})
@TypeConverters(CustomConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sAppDatabase;

    public static AppDatabase getInstance(Context context) {
        if (sAppDatabase == null)
            sAppDatabase = Room.databaseBuilder(
                            context,
                            AppDatabase.class,
                            "folder-db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        return sAppDatabase;
    }

    public abstract SourceDao sourceDao();

}
