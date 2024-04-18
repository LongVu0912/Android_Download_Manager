package com.example.download_manager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DownloadModel {
    private String status;
    private String title;
    private String file_size;
    private String progress;
    private boolean isPaused;
    private long downloadId;
    private String file_path;
}
