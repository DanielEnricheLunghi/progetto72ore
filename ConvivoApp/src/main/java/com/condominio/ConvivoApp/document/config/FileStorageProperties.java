package com.condominio.ConvivoApp.document.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Properties for file storage (S3 / MinIO)
 */
@Configuration
@ConfigurationProperties(prefix = "document.storage")
@Data
public class FileStorageProperties {
    private String bucketName;
    private String region;
    private String endpoint; // optional for MinIO
    private String accessKey;
    private String secretKey;
    private boolean pathStyleAccess = true; // useful for MinIO

}
