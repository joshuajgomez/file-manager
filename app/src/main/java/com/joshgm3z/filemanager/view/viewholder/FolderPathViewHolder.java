package com.joshgm3z.filemanager.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.data.Folder;

public class FolderPathViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTextView;
    private FolderPathClickListener mListener;
    private Folder mFolder;

    public FolderPathViewHolder(@NonNull View itemView, FolderPathClickListener listener) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.tv_folder_item_path);
        this.mListener = listener;
        itemView.setOnClickListener(this);
    }

    public void setData(Folder folder) {
        mFolder = folder;
        mTextView.setText(folder.getName());
    }

    @Override
    public void onClick(View view) {
        mListener.onFolderPathClick(mFolder);
    }

    public interface FolderPathClickListener {
        void onFolderPathClick(Folder folder);
    }
}
