package com.joshgm3z.filemanager.domain.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.joshgm3z.filemanager.data.FileData;
import com.joshgm3z.filemanager.data.RootFolder;
import com.joshgm3z.filemanager.domain.room.dao.FolderDao;
import com.joshgm3z.filemanager.domain.room.dao.RootFolderDao;

@Database(version = 4, entities = {FileData.class, RootFolder.class})
@TypeConverters(CustomConverter.class)
public abstract class FolderDatabase extends RoomDatabase {

    private static FolderDatabase mFolderDatabase;

    public static FolderDatabase getInstance(Context context) {
        if (mFolderDatabase == null)
            mFolderDatabase = Room.databaseBuilder(
                            context,
                            FolderDatabase.class,
                            "folder-db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        return mFolderDatabase;
    }

    public abstract FolderDao folderDao();
    public abstract RootFolderDao rootFolderDao();

}
