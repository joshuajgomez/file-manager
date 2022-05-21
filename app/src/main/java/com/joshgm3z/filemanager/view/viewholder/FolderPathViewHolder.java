package com.joshgm3z.filemanager.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.domain.data.FileData;

public class FolderPathViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTextView;
    private FolderPathClickListener mListener;
    private FileData mFolder;

    public FolderPathViewHolder(@NonNull View itemView, FolderPathClickListener listener) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.tv_folder_item_path);
        this.mListener = listener;
        itemView.setOnClickListener(this);
    }

    public void setData(FileData fileData) {
        mFolder = fileData;
        mTextView.setText(fileData.getName());
    }

    @Override
    public void onClick(View view) {
        mListener.onFolderPathClick(mFolder);
    }

    public interface FolderPathClickListener {
        void onFolderPathClick(FileData fileData);
    }
}
