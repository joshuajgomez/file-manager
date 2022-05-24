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
            FileType.UNKNOWN,
            FileType.ROOT_EXT_STORAGE,
            FileType.ROOT_INT_STORAGE,
            FileType.ROOT_CLOUD,
            FileType.FOLDER,
            FileType.FILE,
            FileType.PNG,
            FileType.JPEG,
            FileType.JPG,
            FileType.TXT,
    })
    public @interface FileType {
        int UNKNOWN = 0;
        int ROOT_EXT_STORAGE = 1;
        int ROOT_INT_STORAGE = 2;
        int ROOT_CLOUD = 3;
        int FOLDER = 4;
        int FILE = 5;
        int PNG = 6;
        int JPEG = 7;
        int JPG = 8;
        int TXT = 9;
    }

    public @interface FileExtension {
        String JPEG = ".jpeg";
        String JPG = ".jpg";
        String PNG = ".png";
        String TXT = ".txt";
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
