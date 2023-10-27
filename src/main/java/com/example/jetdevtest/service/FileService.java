package com.example.jetdevtest.service;

import com.example.jetdevtest.response.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadResponse fileUploadData(MultipartFile multipartFile);
}
