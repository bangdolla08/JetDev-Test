package com.example.jetdevtest.service.impl;

import com.example.jetdevtest.response.FileUploadResponse;
import com.example.jetdevtest.service.FileService;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    Path rootLocation = Paths.get("local");

    @SneakyThrows
    @Override
    public FileUploadResponse fileUploadData(MultipartFile file) {


        try {
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);
            String uuidFileUpload = UUID.randomUUID().toString();
            String uploadedFileName = uuidFileUpload + "." + extension;
            Path destinationFile = rootLocation.resolve(
                            Paths.get(uploadedFileName))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                return FileUploadResponse.builder()
                        .idFile(uuidFileUpload)
                        .fileName(originalFilename)
                        .build();
            }
        } catch (IOException e) {
            throw new Exception("Failed to store file.", e);
        }
    }
}
