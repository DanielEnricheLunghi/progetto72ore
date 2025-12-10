package com.condominio.ConvivoApp.document.service.impl;

import com.condominio.ConvivoApp.document.config.FileStorageProperties;
import com.condominio.ConvivoApp.document.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.*;
import software.amazon.awssdk.core.sync.RequestBody;

import java.net.URL;
import java.time.Duration;

/**
 * S3-based implementation of FileStorageService using AWS SDK v2
 */
@Service
@RequiredArgsConstructor
public class S3FileStorageService implements FileStorageService {

    private final S3Client s3Client;
    private final FileStorageProperties props;

    @Override
    public String store(MultipartFile file, String key) throws Exception {
        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(props.getBucketName())
                .key(key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        s3Client.putObject(putReq, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        return key;
    }

    @Override
    public Resource loadAsResource(String key) throws Exception {
        GetObjectRequest req = GetObjectRequest.builder().bucket(props.getBucketName()).key(key).build();
        ResponseInputStream<GetObjectResponse> s3is = s3Client.getObject(req);
        return new InputStreamResource(s3is);
    }

    @Override
    public URL generatePresignedUrl(String key, long expiresInSeconds) throws Exception {
        S3Presigner presigner = S3Presigner.create();
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(props.getBucketName())
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(Duration.ofSeconds(expiresInSeconds))
                .build();

        PresignedGetObjectRequest presigned = presigner.presignGetObject(presignRequest);
        return presigned.url();
    }
}
