package com.joshgm3z.filemanager.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.data.FileData;

public class FolderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTvFolderName;
    private FolderClickListener mListener;
    private FileData mFileData;

    public FolderViewHolder(@NonNull View itemView, FolderClickListener listener) {
        super(itemView);
        mTvFolderName = itemView.findViewById(R.id.tv_folder_item_name);
        this.mListener = listener;
        itemView.setOnClickListener(this);
    }

    public void setData(FileData fileData) {
        mTvFolderName.setText(fileData.getName());
        mFileData = fileData;
    }

    public void setData(FileData fileData) {

    }

    @Override
    public void onClick(View view) {
        mListener.onFolderCLick(mFileData);
    }

    public interface FolderClickListener {
        void onFolderCLick(FileData fileData);
    }
}
