package com.example.download_manager;

import com.example.download_manager.models.DownloadModel;

public interface ItemClickListener {
    void onClickItem(String file_path, String status);
    void onShareClick(DownloadModel downloadModel);
    void onRemoveClick(int position);
}
