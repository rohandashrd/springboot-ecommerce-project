package com.springboot.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File names of crrent/original file
        String originalFileName = file.getOriginalFilename();

        //Generate the unique filename
        String randomId= UUID.randomUUID().toString();

        //mat.jpf --> random is 123 so new name = 123.jpg
        String fileName=randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath=path+ File.separator+fileName;

        //Check if the path exist  and create
        File folder=new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }

        //Upload it to the server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        //retunr the filename
        return fileName;
    }
}
