package com.joshgm3z.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.FolderRepository;
import com.joshgm3z.filemanager.util.Logger;
import com.joshgm3z.filemanager.view.adapter.FolderAdapter;
import com.joshgm3z.filemanager.view.adapter.FolderPathAdapter;
import com.joshgm3z.filemanager.view.viewholder.FolderPathViewHolder;
import com.joshgm3z.filemanager.view.viewholder.FolderViewHolder;
import com.joshgm3z.filemanager.view.viewmodel.FolderView;
import com.joshgm3z.filemanager.view.viewmodel.FolderViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FolderView, FolderViewHolder.FolderClickListener, FolderPathViewHolder.FolderPathClickListener, View.OnClickListener {

    private FolderViewModel mViewModel;
    private FolderAdapter mFolderAdapter = new FolderAdapter(this);
    private FolderPathAdapter mFolderPathAdapter = new FolderPathAdapter(this);
    private TextView mTvActionBarTitle;
    private ImageView mIvBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        mViewModel = new FolderViewModel(new FolderRepository(getApplicationContext()), this);

        RecyclerView rvFolder = findViewById(R.id.rv_folder);
        rvFolder.setAdapter(mFolderAdapter);
        RecyclerView rvFolderPath = findViewById(R.id.rv_folder_path);
        rvFolderPath.setAdapter(mFolderPathAdapter);

        mTvActionBarTitle = findViewById(R.id.tv_action_bar_title);
        mIvBackArrow = findViewById(R.id.iv_back_arrow);
        mIvBackArrow.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.refreshContent();
    }

    @Override
    public void updateFolderContent(List<Folder> folderList) {
        Logger.a("folderList: " + folderList);
        mFolderAdapter.setFolderList(folderList);
    }

    @Override
    public void updateFolderPath(List<Folder> folderList) {
        Logger.a("folderPathList: " + folderList);
        mFolderPathAdapter.setPathFolderList(folderList);
    }

    @Override
    public void setFolderName(String name) {
        if (name != null) {
            mTvActionBarTitle.setText(name.toString());
        } else {
            mTvActionBarTitle.setText(getString(R.string.app_name));
        }
    }

    @Override
    public void showBackArrow(boolean isShow) {
        if (isShow) {
            mIvBackArrow.setVisibility(View.VISIBLE);
        } else {
            mIvBackArrow.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFolderCLick(Folder folder) {
        mViewModel.updateCurrentFolder(folder);
    }

    @Override
    public void onBackPressed() {
        if (mViewModel.getCurrentFolder() != null) {
            mViewModel.goToParentFolder();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFolderPathClick(Folder folder) {
        mViewModel.updateCurrentFolder(folder);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mIvBackArrow.getId()) {
            onBackPressed();
        }
    }
}