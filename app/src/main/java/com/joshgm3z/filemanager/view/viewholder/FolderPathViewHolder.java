package com.joshgm3z.filemanager.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.data.Folder;

public class FolderPathViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    public FolderPathViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.tv_folder_item_path);
    }

    public void setData(Folder folder) {
        mTextView.setText(folder.getName());
    }
}
