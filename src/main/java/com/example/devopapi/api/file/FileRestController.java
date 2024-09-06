package com.example.devopapi.api.file;

import com.example.devopapi.api.base.BestRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> download(@PathVariable String fileName){
        Resource resource = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header("Content-Disposition", "attachment; filename=" + resource.getFilename() + "\"")
                .body(resource);

    }

    @PostMapping("/single")
    public BestRest<?> uploadFileSingle(@RequestPart MultipartFile file) {
        log.info("File Request = {}", file);
        FileDto fileDto = fileService.uploadFileSingle(file);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File upload success.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @PostMapping("/multiple")
    public BestRest<?> uploadMultipleFile(@RequestPart List<MultipartFile> files) {
        log.info("File Request = {}", files);
        List<FileDto> fileDto = fileService.uploadFileMultiple(files);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File upload success.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        fileService.deletedFile(fileName);
        return "File have been deleted successfully.";
    }

    @GetMapping("")
    public BestRest<?> findAllFiles() {
        List<FileDto> fileDtos = fileService.findAllFiles();
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File found.")
                .timestamp(LocalDateTime.now())
                .data(fileDtos)
                .build();
    }

    @GetMapping("/{fileName}")
    public BestRest<?> findFileByName(@PathVariable String fileName) throws IOException {
        FileDto fileDto = fileService.findFileByName(fileName);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File found.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
}
