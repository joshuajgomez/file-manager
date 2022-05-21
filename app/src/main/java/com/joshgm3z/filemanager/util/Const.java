package com.joshgm3z.filemanager.util;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Const {

    public static final int INVALID_ID = -1;
    public static final String NAME_EXT_STORAGE = "sdcard";
    public static final String NAME_INT_STORAGE = "internal";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            FileType.ROOT_EXT_STORAGE,
            FileType.ROOT_INT_STORAGE,
            FileType.ROOT_CLOUD,
            FileType.FOLDER,
            FileType.FILE,
    })
    public @interface FileType {
        int ROOT_EXT_STORAGE = 0;
        int ROOT_INT_STORAGE = 1;
        int ROOT_CLOUD = 2;
        int FOLDER = 3;
        int FILE = 4;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            Option.DELETE,
            Option.RENAME,
            Option.COPY,
            Option.MOVE,
            Option.SHARE,
            Option.PROPERTIES,
    })
    public @interface Option {
        int DELETE = 0;
        int RENAME = 1;
        int COPY = 2;
        int MOVE = 3;
        int SHARE = 4;
        int PROPERTIES = 5;
    }
}
