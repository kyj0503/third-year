package com.webproject.chonstay_backend.googleCloudStorage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class GCSService {

    private final Storage storage;

    @Value("${google.cloud.storage.bucket-name}")
    private String bucketName;

    public GCSService(@Value("${google.cloud.storage.key-file}") String keyFilePath) throws IOException {
        // Load the key file as an InputStream
        InputStream keyFileStream = getClass().getClassLoader().getResourceAsStream(keyFilePath);
        if (keyFileStream == null) {
            throw new IllegalArgumentException("Key file not found: " + keyFilePath);
        }

        this.storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(keyFileStream))
                .build()
                .getService();
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}