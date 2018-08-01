package com.gslab.linkedin.linkedindemo.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gslab.linkedin.linkedindemo.exception.FileStorageException;
import com.gslab.linkedin.linkedindemo.property.FileStorageProperties;
import com.gslab.linkedin.linkedindemo.service.FileStorageService;

public class FileStorageServiceImpl implements FileStorageService {

	private Path fileStorageLocation ;

    @Autowired
    public void FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }	
	
	@Override
	public String storeFile(MultipartFile file,String username) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("this.fileStorageLocation : "+this.fileStorageLocation);
        try {
            String filename = username + "." + file.getOriginalFilename().split("\\.")[1];
            filename.lastIndexOf(".");
            Path targetLocation = Paths.get(this.fileStorageLocation + "\\" + filename);
            Path fileUploadedPath = Files.write(targetLocation	, file.getBytes());
            return fileUploadedPath.toString();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}

}
