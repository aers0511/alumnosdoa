package com.alumnos.daoalumnos.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    String uploadFile(MultipartFile file) throws IOException;

}
