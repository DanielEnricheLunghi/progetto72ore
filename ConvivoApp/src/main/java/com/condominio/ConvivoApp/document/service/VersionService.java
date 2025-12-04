package com.condominio.ConvivoApp.document.service;

import com.condominio.ConvivoApp.document.dto.DocumentVersionDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

public interface VersionService {
    DocumentVersionDto uploadNewVersion(Long documentId, MultipartFile file, String userId) throws Exception;
    List<DocumentVersionDto> listVersions(Long documentId);
    Resource downloadVersion(Long documentId, Integer versionNumber) throws Exception;
    URL generatePresignedUrl(Long documentId, Integer versionNumber, long expiresInSeconds) throws Exception;
}
