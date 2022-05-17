package com.joshgm3z.filemanager.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.data.FileData;
import com.joshgm3z.filemanager.data.Folder;

public class FolderViewHolder extends RecyclerView.ViewHolder {

    TextView mTvFolderName;

    public FolderViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvFolderName = itemView.findViewById(R.id.tv_folder_item_name);
    }

    public void setData(Folder folder) {
        mTvFolderName.setText(folder.getName());
    }

    public void setData(FileData fileData) {

    }
}
