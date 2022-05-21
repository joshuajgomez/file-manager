package com.joshgm3z.filemanager.view;

import android.content.Context;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.Logger;

public class OptionsBottomSheet {

    private OptionFragmentListener mListener;
    private FileData mSelectedFileData;
    private BottomSheetDialog mBottomSheetDialog;

    public OptionsBottomSheet(Context context, FileData selectedFileData, OptionFragmentListener listener) {
        mListener = listener;
        mSelectedFileData = selectedFileData;
        mBottomSheetDialog = new BottomSheetDialog(context);
        mBottomSheetDialog.setContentView(R.layout.fragment_options);
        mBottomSheetDialog.findViewById(R.id.ll_option_delete).setOnClickListener(this::onOptionClick);
        mBottomSheetDialog.findViewById(R.id.ll_option_rename).setOnClickListener(this::onOptionClick);
        mBottomSheetDialog.findViewById(R.id.ll_option_copy).setOnClickListener(this::onOptionClick);
        mBottomSheetDialog.findViewById(R.id.ll_option_move).setOnClickListener(this::onOptionClick);
        mBottomSheetDialog.findViewById(R.id.ll_option_share).setOnClickListener(this::onOptionClick);
        mBottomSheetDialog.findViewById(R.id.ll_option_properties).setOnClickListener(this::onOptionClick);
    }

    public void show() {
        mBottomSheetDialog.show();
    }

    private void onOptionClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.ll_option_delete:
                    mListener.onOptionClick(Const.Option.DELETE, mSelectedFileData);
                    break;
                case R.id.ll_option_rename:
                    mListener.onOptionClick(Const.Option.RENAME, mSelectedFileData);
                    break;
                case R.id.ll_option_copy:
                    mListener.onOptionClick(Const.Option.COPY, mSelectedFileData);
                    break;
                case R.id.ll_option_move:
                    mListener.onOptionClick(Const.Option.MOVE, mSelectedFileData);
                    break;
                case R.id.ll_option_share:
                    mListener.onOptionClick(Const.Option.SHARE, mSelectedFileData);
                    break;
                case R.id.ll_option_properties:
                    mListener.onOptionClick(Const.Option.PROPERTIES, mSelectedFileData);
                    break;
            }
        } else {
            Logger.e("OptionFragmentListener not available");
        }
        mBottomSheetDialog.dismiss();
    }

    public interface OptionFragmentListener {
        void onOptionClick(@Const.Option int option, FileData selectedFileData);
    }
}