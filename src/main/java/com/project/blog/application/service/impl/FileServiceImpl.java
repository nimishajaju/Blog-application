package com.project.blog.application.service.impl;

import com.project.blog.application.service.FileService;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name= file.getOriginalFilename();

        String randomId= UUID.randomUUID().toString();
        String randomfileName= randomId.concat(name.substring(name.lastIndexOf(".")));
        String fullPath= path + File.separator+randomfileName;

        File file1= new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(fullPath));
        return randomfileName;
    }
}
