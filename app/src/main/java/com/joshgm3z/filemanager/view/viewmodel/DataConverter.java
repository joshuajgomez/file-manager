package com.joshgm3z.filemanager.view.viewmodel;

import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.data.Source;

import java.util.ArrayList;
import java.util.List;

public class DataConverter {
    public static List<FileData> convertToFileDataList(List<Source> sourceFolderList) {
        List<FileData> fileDataList = new ArrayList<>();
        for (Source source : sourceFolderList) {
            FileData fileData = new FileData();
            fileData.setName(source.getName());
            fileData.setUrl(source.getUrl());
            fileData.setType(source.getType());
            fileData.setSize(source.getSize());
            fileDataList.add(fileData);
        }
        return fileDataList;
    }
}
