package com.condominio.ConvivoApp.document.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public interface FileStorageService {
    String store(MultipartFile file, String key) throws Exception;
    Resource loadAsResource(String key) throws Exception;
    URL generatePresignedUrl(String key, long expiresInSeconds) throws Exception;
}