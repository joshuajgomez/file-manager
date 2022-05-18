package com.joshgm3z.filemanager.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.view.viewholder.FolderPathViewHolder;

import java.util.List;

public class FolderPathAdapter extends RecyclerView.Adapter<FolderPathViewHolder> {

    private List<Folder> mPathFolderList;
    private FolderPathViewHolder.FolderPathClickListener mListener;

    public FolderPathAdapter(FolderPathViewHolder.FolderPathClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public FolderPathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_path, parent, false);
        return new FolderPathViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderPathViewHolder holder, int position) {
        holder.setData(mPathFolderList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPathFolderList != null ? mPathFolderList.size() : 0;
    }

    public void setPathFolderList(List<Folder> mPathFolderList) {
        this.mPathFolderList = mPathFolderList;
        notifyDataSetChanged();
    }
}
