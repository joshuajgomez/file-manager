package com.joshgm3z.filemanager.util;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Const {

    public static final int INVALID_ID = -1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RootType.EXT_STORAGE, RootType.CLOUD})
    public @interface RootType {
        int EXT_STORAGE = 0;
        int CLOUD = 1;
    }
}
