package com.example.jetdevtest.repository;

import com.example.jetdevtest.repository.entity.FileLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileLibraryRepository extends JpaRepository<FileLibrary,String> {
}
