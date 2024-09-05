package com.example.devopapi.api.file;

import com.example.devopapi.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileUtil fileUtil;

    @Override
    public FileDto uploadFileSingle(MultipartFile file) {
        return fileUtil.uploadFile(file);
    }

    @Override
    public List<FileDto> uploadFileMultiple(List<MultipartFile> files) {
        List<FileDto> fileDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            fileDtos.add(fileUtil.uploadFile(file));
        }
        return fileDtos;
    }

    @Override
    public List<FileDto> findAllFiles() {
        return fileUtil.findAllFiles();
    }

    @Override
    public FileDto findFileByName(String fileName) {
        return fileUtil.findFileByName(fileName);
    }

    @Override
    public void deletedFile(String fileName) {
        fileUtil.deleteFile(fileName);
    }
}
