package com.joshgm3z.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.joshgm3z.filemanager.data.Folder;
import com.joshgm3z.filemanager.domain.FolderRepository;
import com.joshgm3z.filemanager.util.Logger;
import com.joshgm3z.filemanager.view.adapter.FolderAdapter;
import com.joshgm3z.filemanager.view.adapter.FolderPathAdapter;
import com.joshgm3z.filemanager.view.viewmodel.FolderView;
import com.joshgm3z.filemanager.view.viewmodel.FolderViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FolderView {

    private FolderViewModel mViewModel;
    private FolderAdapter mFolderAdapter = new FolderAdapter();
    private FolderPathAdapter mFolderPathAdapter = new FolderPathAdapter();

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.onUiResume();
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
}