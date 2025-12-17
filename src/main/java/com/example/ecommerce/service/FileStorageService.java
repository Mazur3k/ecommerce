package com.example.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    String uploadFile(String path, MultipartFile file) throws IOException;
}
