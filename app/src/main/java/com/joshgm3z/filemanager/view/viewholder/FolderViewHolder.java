package com.joshgm3z.filemanager.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.util.Const;

public class FolderViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvFolderName;
    private FolderClickListener mListener;
    private FileData mFileData;
    private ImageView mIvFileIcon;

    public FolderViewHolder(@NonNull View itemView, FolderClickListener listener) {
        super(itemView);
        mTvFolderName = itemView.findViewById(R.id.tv_folder_item_name);
        mIvFileIcon = itemView.findViewById(R.id.iv_file_icon);
        this.mListener = listener;
        itemView.setOnClickListener(this::onClick);
        itemView.setOnLongClickListener(this::onLongClick);
    }

    private boolean onLongClick(View view) {
        mListener.onFileLongCLick(mFileData);
        return false;
    }


    public void setData(FileData fileData) {
        mTvFolderName.setText(fileData.getName());
        mFileData = fileData;
        int iconRes;
        switch (fileData.getType()) {
            case Const.FileType.FOLDER:
                iconRes = R.drawable.ic_folder_yellow;
                break;
            case Const.FileType.TXT:
                iconRes = R.drawable.ic_file_text;
                break;
            case Const.FileType.JPEG:
            case Const.FileType.JPG:
            case Const.FileType.PNG:
                iconRes = R.drawable.ic_image;
                break;
            case Const.FileType.ROOT_EXT_STORAGE:
                iconRes = R.drawable.ic_sdcard;
                break;
            case Const.FileType.ROOT_INT_STORAGE:
                iconRes = R.drawable.ic_internal_memory;
                break;
            default:
                iconRes = R.drawable.ic_file;
        }
        mIvFileIcon.setImageResource(iconRes);
    }


    public void onClick(View view) {
        mListener.onFileCLick(mFileData);
    }

    public interface FolderClickListener {
        void onFileCLick(FileData fileData);

        void onFileLongCLick(FileData fileData);
    }
}
