package com.example.download_manager;

import com.example.download_manager.models.DownloadModel;

public interface ItemClickListener {
    void handleClickDownloadItem(String file_path, String status);
    void handleClickShare(DownloadModel downloadModel);
    void handleClickRemove(int position);
    void handleClearAllDownload();
    void handleShowInputDialog();
}
