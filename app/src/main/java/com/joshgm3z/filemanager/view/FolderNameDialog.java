package com.joshgm3z.filemanager.view;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;


public class FolderNameDialog {

    public void show(Context context, FolderNameDialogListener listener) {
        final EditText editText = new EditText(context);
        editText.setText("New folder");
        new AlertDialog.Builder(context)
                .setTitle("Create new folder")
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    if (listener != null) {
                        listener.onFolderNameDialogResult(editText.getText().toString());
                    }
                })//
                .setNegativeButton(android.R.string.cancel, null)
                .setView(editText)
                .show();
    }

    public interface FolderNameDialogListener {
        void onFolderNameDialogResult(String result);
    }
}
