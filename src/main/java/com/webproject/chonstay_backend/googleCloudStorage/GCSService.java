package com.webproject.chonstay_backend.googleCloudStorage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class GCSService {

    private final Storage storage;
    private final String bucketName;

    public GCSService() throws IOException {
        // 환경 변수에서 값 읽기
        String keyFilePath = System.getenv("GCS_KEY_FILE");
        this.bucketName = System.getenv("GCS_BUCKET_NAME");

        // 필수 환경 변수 검증
        if (keyFilePath == null || keyFilePath.isEmpty()) {
            throw new IllegalArgumentException("Environment variable 'GCS_KEY_FILE' is not set or empty.");
        }
        if (bucketName == null || bucketName.isEmpty()) {
            throw new IllegalArgumentException("Environment variable 'GCS_BUCKET_NAME' is not set or empty.");
        }

        // GoogleCredentials를 사용하여 Storage 초기화
        try (InputStream keyFileStream = getClass().getClassLoader().getResourceAsStream(keyFilePath)) {
            if (keyFileStream == null) {
                throw new IllegalArgumentException("Key file not found in the specified path: " + keyFilePath);
            }
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(keyFileStream))
                    .build()
                    .getService();
        }
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
