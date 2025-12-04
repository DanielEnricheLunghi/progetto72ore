package com.condominio.ConvivoApp.document.service.impl;

import com.condominio.ConvivoApp.document.dto.DocumentVersionDto;
import com.condominio.ConvivoApp.document.service.VersionService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

@Service
public class VersionServiceImpl implements VersionService {

    @Override
    public DocumentVersionDto uploadNewVersion(Long documentId, MultipartFile file, String userId) throws Exception {
        //* TODO: implementa logica di upload
        return null;
    }

    @Override
    public List<DocumentVersionDto> listVersions(Long documentId) {
        //* TODO: implementa logica di listing
        return List.of();
    }

    @Override
    public Resource downloadVersion(Long documentId, Integer versionNumber) throws Exception {
        //* TODO: implementa logica di download
        return null;
    }

    @Override
    public URL generatePresignedUrl(Long documentId, Integer versionNumber, long expiresInSeconds) throws Exception {
        //* TODO: implementa logica di presigned URL
        return null;
    }
}