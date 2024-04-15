package com.example.download_manager.databases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.download_manager.models.DownloadModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "downloadManager.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "DownloadModel";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DOWNLOAD_TABLE = "CREATE TABLE DownloadModel (downloadId INTEGER PRIMARY KEY, title TEXT, file_path TEXT, progress TEXT, status TEXT, file_size TEXT, is_paused INTEGER)";
        db.execSQL(CREATE_DOWNLOAD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DownloadModel");
        onCreate(db);
    }

    public void addDownload(DownloadModel downloadModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("downloadId", downloadModel.getDownloadId());
        values.put("title", downloadModel.getTitle());
        values.put("file_path", downloadModel.getFile_path());
        values.put("progress", downloadModel.getProgress());
        values.put("status", downloadModel.getStatus());
        values.put("file_size", downloadModel.getFile_size());
        values.put("is_paused", downloadModel.getIs_paused());

        System.out.println("Add download with id: " + downloadModel.getDownloadId());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    @SuppressLint("Range")
    public List<DownloadModel> getAllDownloads() {
        List<DownloadModel> downloadList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                DownloadModel downloadModel = new DownloadModel();
                downloadModel.setDownloadId(cursor.getLong(cursor.getColumnIndex("downloadId")));
                downloadModel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                downloadModel.setFile_path(cursor.getString(cursor.getColumnIndex("file_path")));
                downloadModel.setProgress(cursor.getString(cursor.getColumnIndex("progress")));
                downloadModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                downloadModel.setFile_size(cursor.getString(cursor.getColumnIndex("file_size")));
                downloadModel.setIs_paused(cursor.getInt(cursor.getColumnIndex("is_paused")) != 0);

                downloadList.add(downloadModel);
                System.out.println("Get download with id: " + downloadModel.getDownloadId());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return downloadList;
    }
    public void deleteAllDownloads() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM DownloadModel");
        db.close();
    }

    public void deleteDownloadById(int downloadId) {
        System.out.println("Start delete download with id: " + downloadId);
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "downloadId=?", new String[]{String.valueOf(downloadId)});
        db.close();
    }
}
