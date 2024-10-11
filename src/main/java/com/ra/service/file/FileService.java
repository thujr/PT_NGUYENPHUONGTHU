package com.ra.service.file;

import com.google.cloud.storage.*;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    public static final String bucketName="test-5096c.appspot.com";
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private Storage storage;
    public String uploadFileServer(MultipartFile file){
        String uploadPath = servletContext.getRealPath("/upload");
        File fileUpload = new File(uploadPath);
        if(!fileUpload.exists()){
            fileUpload.mkdir();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String fileName = dateTimeFormatter.format(LocalDateTime.now())+file.getOriginalFilename();

        try {
            FileCopyUtils.copy(file.getBytes(),new File(uploadPath+File.separator+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadFileFormServerToFirebase(uploadPath+File.separator+fileName);
    }
    public String uploadFileFormServerToFirebase(String filePath){
        Path localPath = Paths.get(filePath);
        String fileName = localPath.getFileName().toString();
        BlobId blobId = BlobId.of(bucketName,fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(),Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
