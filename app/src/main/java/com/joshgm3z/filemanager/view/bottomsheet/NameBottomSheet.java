package com.joshgm3z.filemanager.view.bottomsheet;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.joshgm3z.filemanager.R;

public class NameBottomSheet {

    private NameBottomSheetListener mListener;
    private BottomSheetDialog mBottomSheetDialog;
    private EditText mEtName;
    private boolean mIsRename = false;
    private String mCurrentName;
    private String mSelectedFile;
    TextView mSheetTitle;

    public NameBottomSheet(Context context, NameBottomSheetListener listener) {
        mListener = listener;
        mBottomSheetDialog = new BottomSheetDialog(context);
        mBottomSheetDialog.setContentView(R.layout.layout_bottom_sheet_name);
        mSheetTitle = mBottomSheetDialog.findViewById(R.id.tv_new_folder_title);
        mEtName = mBottomSheetDialog.findViewById(R.id.et_new_folder_name);
        mBottomSheetDialog.findViewById(R.id.btn_new_folder_confirm)
                .setOnClickListener(this::onConfirmClick);


    }

    public void setRenameDetails(String selectedFile, String currentName) {
        mIsRename = true;
        mSelectedFile = selectedFile;
        mCurrentName = currentName;
        if (mCurrentName != null && !mCurrentName.isEmpty()) {
            mSheetTitle.setText("Rename folder");
            mEtName.setText(mCurrentName);
        }
    }

    public void show() {
        mBottomSheetDialog.show();
    }

    private void onConfirmClick(View view) {
        String text = mEtName.getText().toString().trim();
        if (!text.isEmpty()) {
            if (!mListener.isNameExists(text)) {
                if (mIsRename) {
                    mListener.onRenameConfirmed(mSelectedFile, text);
                } else {
                    mListener.onNameConfirmed(text);
                }
                mBottomSheetDialog.dismiss();
            } else {
                mEtName.setError("This name already exists");
            }
        } else {
            mEtName.setError("Please enter a valid name");
        }
    }

    public interface NameBottomSheetListener {
        void onNameConfirmed(String name);

        void onRenameConfirmed(String selectedFile, String name);

        boolean isNameExists(String name);
    }
}
