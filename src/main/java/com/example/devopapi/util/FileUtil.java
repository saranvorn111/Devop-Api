package com.example.devopapi.util;

import com.example.devopapi.api.file.FileDto;
import com.example.devopapi.api.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUtil {

    @Value("${file.server.path}")
    private String fileServerPath;

    @Value("${file.client.path}")
    private String fileClientPath;
    @Value("${file.base.url}")
    private String fileBaseUrl;

    public FileDto uploadFile(MultipartFile file) {
        int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex + 1);
        long size = file.getSize();
        String fileName = String.format("%s.%s", UUID.randomUUID(), extension);
        String url = String.format("%s%s", fileBaseUrl, fileName);

        Path path = Paths.get(fileServerPath, fileName);
        try {
            Files.copy(file.getInputStream(), path);
            return FileDto.builder()
                    .name(fileName)
                    .url(url)
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed.");
        }
    }

    public void deleteFile(String fileName) {
        Path path = Paths.get(fileServerPath, fileName);
        try {
            boolean deleted = Files.deleteIfExists(path);
            if (!deleted) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found.");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File delete failed.");
        }
    }

    public List<FileDto> findAllFiles() {
        List<FileDto> fileDtoList = new ArrayList<>();
        try {
            Files.walk(Paths.get(fileServerPath))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();
                        String url = String.format("%s%s", fileBaseUrl, fileName);
                        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                        long size = 0;
                        try {
                            size = Files.size(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fileDtoList.add(FileDto.builder()
                                .name(fileName)
                                .url(url)
                                .extension(extension)
                                .size(size)
                                .build());
                    });
            return fileDtoList;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File not found.");
        }
    }

    public FileDto findFileByName(String fileName) {
        Path path = Paths.get(fileServerPath, fileName);
        try {
            if (Files.exists(path)) {
                String url = String.format("%s%s", fileBaseUrl, fileName);
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                long size = Files.size(path);
                return FileDto.builder()
                        .name(fileName)
                        .url(url)
                        .extension(extension)
                        .size(size)
                        .build();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found.");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File not found.");
        }
    }
}


