package com.example.devopapi.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileDto uploadFileSingle(MultipartFile file);

    List<FileDto> uploadFileMultiple(List<MultipartFile> files);

    List<FileDto> findAllFiles();

    FileDto findFileByName(String fileName);

    void deletedFile(String fileName);
}
