package com.joshgm3z.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.domain.FileAccessManager;
import com.joshgm3z.filemanager.domain.FolderRepository;
import com.joshgm3z.filemanager.view.FolderNameDialog;
import com.joshgm3z.filemanager.view.OptionsBottomSheet;
import com.joshgm3z.filemanager.view.adapter.FolderAdapter;
import com.joshgm3z.filemanager.view.adapter.FolderPathAdapter;
import com.joshgm3z.filemanager.view.viewholder.FolderPathViewHolder;
import com.joshgm3z.filemanager.view.viewholder.FolderViewHolder;
import com.joshgm3z.filemanager.view.viewmodel.FolderView;
import com.joshgm3z.filemanager.view.viewmodel.FolderViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FolderView,
        FolderViewHolder.FolderClickListener,
        FolderPathViewHolder.FolderPathClickListener,
        FolderNameDialog.FolderNameDialogListener {

    private FolderViewModel mViewModel;
    private FolderAdapter mFolderAdapter = new FolderAdapter(this);
    private FolderPathAdapter mFolderPathAdapter = new FolderPathAdapter(this);
    private TextView mTvActionBarTitle;
    private ImageView mIvBackArrow;
    private ImageView mIvNewFolder;
    private RelativeLayout mRlNoContent;
    private RecyclerView mRvFolderList;
    private RecyclerView mRvFolderPath;
    private LinearLayout mLlAppIcon;
    private RelativeLayout mRlPathContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        FileAccessManager fam = new FileAccessManager(getApplicationContext());
        fam.initRootExtStorage();
    }

    private void initUI() {
        Context context = getApplicationContext();
        mViewModel = new FolderViewModel(
                new FolderRepository(context, new FileAccessManager(context)),
                this);

        mRvFolderList = findViewById(R.id.rv_folder);
        mRvFolderList.setAdapter(mFolderAdapter);
        mRvFolderPath = findViewById(R.id.rv_folder_path);
        mRvFolderPath.setAdapter(mFolderPathAdapter);

        mTvActionBarTitle = findViewById(R.id.tv_action_bar_title);
        mIvBackArrow = findViewById(R.id.iv_back_arrow);
        mIvBackArrow.setOnClickListener(this::onBackArrowPress);

        mRlNoContent = findViewById(R.id.rl_content_info);
        mIvNewFolder = findViewById(R.id.iv_new_folder);
        mIvNewFolder.setOnClickListener(this::onNewFolderPress);
        mLlAppIcon = findViewById(R.id.ll_app_icon);
        mLlAppIcon = findViewById(R.id.ll_app_icon);
        mLlAppIcon.setOnClickListener(this::onHomeIconPress);
        mRlPathContainer = findViewById(R.id.rl_path_container);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.refreshContent();
    }

    @Override
    public void updateFolderContent(List<FileData> fileDataList) {
        mFolderAdapter.setFolderList(fileDataList);
    }

    @Override
    public void updateFolderPath(List<FileData> fileDataList) {
        if (fileDataList != null && !fileDataList.isEmpty()) {
            // inside a folder
            mRlPathContainer.setVisibility(View.VISIBLE);
            mFolderPathAdapter.setPathFolderList(fileDataList);
            mRvFolderPath.scrollToPosition(fileDataList.size() - 1);
        } else {
            // home
            mRlPathContainer.setVisibility(View.GONE);
        }
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
    public void showContentEmptyText(boolean isVisible) {
        mRlNoContent.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mRvFolderList.setVisibility(isVisible ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showNewFolderOption(boolean isShow) {
        mIvNewFolder.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.ll_main_container), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onFileCLick(FileData fileData) {
        mViewModel.onFileClick(fileData);
    }

    @Override
    public void onFileLongCLick(FileData fileData) {
        showOptions(fileData);
    }

    @Override
    public void onBackPressed() {
        if (mViewModel.getCurrentFolderUrl() != null) {
            mViewModel.goToParentFolder();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFolderPathClick(FileData fileData) {
        mViewModel.onPathClick(fileData);
    }

    public void onBackArrowPress(View view) {
        onBackPressed();
    }

    public void onNewFolderPress(View view) {
        new FolderNameDialog().show(this, this);
    }

    public void onHomeIconPress(View view) {
        mViewModel.onHomeIconPress();
    }

    @Override
    public void onFolderNameDialogResult(String folderName) {
        mViewModel.onNewFolderClick(folderName);
    }

    public void showOptions(FileData selectedFileData) {
        OptionsBottomSheet fragment = new OptionsBottomSheet(this,
                selectedFileData,
                this::onOptionsClick);
        fragment.show();
    }

    private void onOptionsClick(int option, FileData fileData) {
        mViewModel.onOptionsClick(option, fileData);
    }
}