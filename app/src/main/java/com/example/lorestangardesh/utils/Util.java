package com.example.lorestangardesh.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class Util {


    public static void copyToClipboard(Context context, String text) {
        // Get the clipboard system service
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        // Create a ClipData object with your text
        ClipData clip = ClipData.newPlainText("label", text);

        // Set the ClipData to the clipboard
        clipboard.setPrimaryClip(clip);
    }
}
