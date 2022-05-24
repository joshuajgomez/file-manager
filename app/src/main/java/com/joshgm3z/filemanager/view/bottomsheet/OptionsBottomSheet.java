package com.joshgm3z.filemanager.view.bottomsheet;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.joshgm3z.filemanager.R;
import com.joshgm3z.filemanager.domain.data.FileData;
import com.joshgm3z.filemanager.util.Const;
import com.joshgm3z.filemanager.util.Logger;

public class OptionsBottomSheet {

    private OptionFragmentListener mListener;
    private String mSelectedFileDataUrl;
    private BottomSheetDialog mBottomSheetDialog;
    private int mFileType;
    private LinearLayout mOptionCopy;
    private LinearLayout mOptionMove;
    private LinearLayout mOptionShare;

    public OptionsBottomSheet(Context context, String selectedFileDataUrl, int fileType, OptionFragmentListener listener) {
        mFileType = fileType;
        mListener = listener;
        mSelectedFileDataUrl = selectedFileDataUrl;
        mBottomSheetDialog = new BottomSheetDialog(context);
        mBottomSheetDialog.setContentView(R.layout.fragment_options);
        mBottomSheetDialog.findViewById(R.id.ll_option_delete).setOnClickListener(this::onOptionClick);
        mBottomSheetDialog.findViewById(R.id.ll_option_rename).setOnClickListener(this::onOptionClick);
        mOptionCopy = mBottomSheetDialog.findViewById(R.id.ll_option_copy);
        mOptionCopy.setOnClickListener(this::onOptionClick);
        mOptionMove = mBottomSheetDialog.findViewById(R.id.ll_option_move);
        mOptionMove.setOnClickListener(this::onOptionClick);
        mOptionShare = mBottomSheetDialog.findViewById(R.id.ll_option_share);
        mOptionShare.setOnClickListener(this::onOptionClick);
        mBottomSheetDialog.findViewById(R.id.ll_option_properties).setOnClickListener(this::onOptionClick);

        validateOptions();
    }

    private void validateOptions() {
        if (mFileType == Const.FileType.FOLDER) {
            mOptionCopy.setVisibility(View.GONE);
            mOptionMove.setVisibility(View.GONE);
            mOptionShare.setVisibility(View.GONE);
        } else {
            // keep visible
        }
    }


    public void show() {
        mBottomSheetDialog.show();
    }

    private void onOptionClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.ll_option_delete:
                    mListener.onOptionClick(Const.Option.DELETE, mSelectedFileDataUrl);
                    break;
                case R.id.ll_option_rename:
                    mListener.onOptionClick(Const.Option.RENAME, mSelectedFileDataUrl);
                    break;
                case R.id.ll_option_copy:
                    mListener.onOptionClick(Const.Option.COPY, mSelectedFileDataUrl);
                    break;
                case R.id.ll_option_move:
                    mListener.onOptionClick(Const.Option.MOVE, mSelectedFileDataUrl);
                    break;
                case R.id.ll_option_share:
                    mListener.onOptionClick(Const.Option.SHARE, mSelectedFileDataUrl);
                    break;
                case R.id.ll_option_properties:
                    mListener.onOptionClick(Const.Option.PROPERTIES, mSelectedFileDataUrl);
                    break;
            }
        } else {
            Logger.e("OptionFragmentListener not available");
        }
        mBottomSheetDialog.dismiss();
    }

    public interface OptionFragmentListener {
        void onOptionClick(@Const.Option int option, String selectedFileDataUrl);
    }
}