package com.ra.controller;

import com.ra.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/")
public class UploadController {
    @Autowired
    private FileService fileService;
    @PostMapping("upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile){
        String fileUrl=fileService.uploadFileServer(multipartFile);
        return ResponseEntity.ok(fileUrl);
    }
}
