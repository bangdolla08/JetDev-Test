package com.example.jetdevtest.service;

import com.example.jetdevtest.repository.entity.FileLibrary;
import com.example.jetdevtest.response.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadResponse fileUploadData(MultipartFile multipartFile);

    Resource loadFileData(String fileId);
    Resource loadFileData(FileLibrary fileId);
    Resource loadData(String fileId);
}
