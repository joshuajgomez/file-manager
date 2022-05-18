package com.joshgm3z.filemanager.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.view.viewholder.FolderViewHolder;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderViewHolder> {

    private List<Folder> mFolderList;

    private FolderViewHolder.FolderClickListener mListener;

    public FolderAdapter(FolderViewHolder.FolderClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_folder, parent, false);
        return new FolderViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        holder.setData(mFolderList.get(position));
    }

    @Override
    public int getItemCount() {
        return mFolderList != null ? mFolderList.size() : 0;
    }

    public void setFolderList(List<Folder> mFolderList) {
        this.mFolderList = mFolderList;
        notifyDataSetChanged();
    }
}
