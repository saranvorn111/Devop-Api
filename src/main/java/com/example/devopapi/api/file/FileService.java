package com.example.devopapi.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    FileDto uploadFileSingle(MultipartFile file);

    List<FileDto> uploadFileMultiple(List<MultipartFile> files);

    List<FileDto> findAllFiles();

//    FileDto findFileByName(String fileName);
    FileDto findFileByName(String fileName) throws IOException;

    Resource downloadFile(String fileName);

    void deletedFile(String fileName);
}
