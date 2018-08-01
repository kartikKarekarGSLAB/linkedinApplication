package com.gslab.linkedin.linkedindemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	public String storeFile(MultipartFile file, String username);
}
