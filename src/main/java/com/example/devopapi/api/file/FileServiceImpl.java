package com.example.devopapi.api.file;

import com.example.devopapi.util.FileUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileUtil fileUtil;

    @Value("${file.download.url}")
    private String fileDownloadUrl;

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
    public FileDto findFileByName(String fileName) throws IOException {
        Resource resource = fileUtil.findFileByName(fileName);

        return FileDto.builder()
                .name(resource.getFilename())
                .extension(fileUtil.getExtension(resource.getFilename()))
                .url(String.format("%s%s", fileUtil.getFileBaseUrl(), resource.getFilename()))
                .downloadUrl(String.format("%s%s", fileDownloadUrl, fileName))
                .size(resource.contentLength())
                .build();
    }

    @Override
    public Resource downloadFile(String fileName) {
        return fileUtil.findFileByName(fileName);
    }

//    @Override
//    public FileDto findFileByName(String fileName) {
//        return fileUtil.findFileByName(fileName);
//    }

    @Override
    public void deletedFile(String fileName) {
        fileUtil.deleteFile(fileName);
    }
}
