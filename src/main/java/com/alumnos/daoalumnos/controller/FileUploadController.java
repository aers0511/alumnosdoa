package com.alumnos.daoalumnos.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alumnos.daoalumnos.service.impl.FileUploadService;

@RestController
@RequestMapping("/files")
public class FileUploadController {
    private final FileUploadService fileUploadService; // Inyectamos la dependecia.

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/uploads")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            String path = fileUploadService.uploadFile(file);

            return ResponseEntity.ok(path);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al cargar el archivo: " + e.getMessage());
        }

    }

}
