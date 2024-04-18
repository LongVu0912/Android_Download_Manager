package com.example.download_manager.utils;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.Objects;

public class ExtensionUtil {

    public static void handlePdfFile(Intent intent) {
        Uri pdffile = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (pdffile != null) {
            Log.d("Pdf File Path : ", Objects.requireNonNull(pdffile.getPath()));
        }
    }

    public static  void handleImage(Intent intent) {
        Uri image = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (image != null) {
            Log.d("Image File Path : ", Objects.requireNonNull(image.getPath()));
        }
    }
}
