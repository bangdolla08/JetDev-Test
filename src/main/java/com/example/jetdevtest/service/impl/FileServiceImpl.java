package com.example.jetdevtest.service.impl;

import com.example.jetdevtest.exception.NotFoundException;
import com.example.jetdevtest.repository.FileLibraryRepository;
import com.example.jetdevtest.repository.entity.FileLibrary;
import com.example.jetdevtest.response.FileUploadResponse;
import com.example.jetdevtest.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileLibraryRepository fileLibraryRepository;
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
                FileLibrary build = FileLibrary.builder()
                        .id(uuidFileUpload)
                        .name(originalFilename)
                        .createDate(LocalDateTime.now())
                        .build();
                fileLibraryRepository.save(build);
                return FileUploadResponse.builder()
                        .idFile(uuidFileUpload)
                        .fileName(originalFilename)
                        .build();
            }
        } catch (IOException e) {
            throw new Exception("Failed to store file.", e);
        }
    }

    @Override
    public Resource loadFileData(String fileId) {
        FileLibrary fileLibrary = fileLibraryRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException("File Not Found"));
        return loadFileData(fileLibrary);
    }

    @Override
    public Resource loadFileData(FileLibrary fileLibrary) {
        try {


            String extension = FilenameUtils.getExtension(fileLibrary.getName());
            String asd = fileLibrary.getId() + "." + extension;
            Path file = rootLocation.resolve(asd);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public Resource loadData(String fileId) {
        FileInputStream fis = new FileInputStream("test.xls");

        return null;
    }
}
