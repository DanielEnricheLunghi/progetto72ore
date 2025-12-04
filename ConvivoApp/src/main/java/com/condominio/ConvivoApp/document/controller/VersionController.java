package com.condominio.ConvivoApp.document.controller;

import com.condominio.ConvivoApp.document.dto.DocumentVersionDto;
import com.condominio.ConvivoApp.document.service.VersionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

/**
 * Version endpoints: upload, list, download, presigned
 */
@RestController
@RequestMapping("/api/documents/{documentId}/versions")
@RequiredArgsConstructor
public class VersionController {

    private final VersionService versionService;

    @PostMapping("/upload")
    public ResponseEntity<DocumentVersionDto> upload(@PathVariable Long documentId,
                                                     @RequestParam("file") MultipartFile file,
                                                     @RequestParam(name = "createdBy", required = false) String createdBy) throws Exception {
        DocumentVersionDto dto = versionService.uploadNewVersion(documentId, file, createdBy);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DocumentVersionDto>> list(@PathVariable Long documentId) {
        return ResponseEntity.ok(versionService.listVersions(documentId));
    }

    @GetMapping("/{versionNumber}/download")
    public ResponseEntity<Resource> download(@PathVariable Long documentId, @PathVariable Integer versionNumber) throws Exception {
        Resource resource = versionService.downloadVersion(documentId, versionNumber);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/{versionNumber}/presigned")
    public ResponseEntity<URL> presigned(@PathVariable Long documentId, @PathVariable Integer versionNumber,
                                         @RequestParam(name = "expires", defaultValue = "3600") long expires) throws Exception {
        URL url = versionService.generatePresignedUrl(documentId, versionNumber, expires);
        return ResponseEntity.ok(url);
    }
}
