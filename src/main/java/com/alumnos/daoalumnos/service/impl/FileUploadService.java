package com.alumnos.daoalumnos.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alumnos.daoalumnos.service.IFileUploadService;

@Service
public class FileUploadService implements IFileUploadService {

    @Value("${file.upload-dir}") // Inyecta la ruta configurada en application.properties
    private String uploadDir;

    /**
     * Método para cargar un archivo en la ruta especificada.
     * 
     * @param file El archivo que se va a cargar.
     * @return Un mensaje indicando la ubicacion del archivo.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // Asegúrate de que el directorio de carga exista
        Path directoryPath = Paths.get(uploadDir.replace("\\", "/"));
        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath); // Si no existe, crea el directorio
        }

        // Obtén el nombre del archivo y la extensión
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ""; // Si no tiene extensión, retorna cadena vacía

        // Define el archivo de destino con barras normales
        Path targetPath = Paths.get(uploadDir.replace("\\", "/"),
                UUID.randomUUID() + fileExtension);

        // Transfiere el archivo al directorio de destino
        file.transferTo(targetPath.toFile());

        return targetPath.toString();
    }
}