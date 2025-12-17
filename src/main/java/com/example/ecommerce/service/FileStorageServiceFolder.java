package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageServiceFolder implements FileStorageService {
    @Value("${app.images.dir}")
    private String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String newFileName = String.format("%s_%s", uuid, file.getOriginalFilename());

        String newPath = uploadDir + File.separator + newFileName;

        File folder = new File(uploadDir);
        if(!folder.exists()){
            folder.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(newPath));
        return newFileName;
    }
}
