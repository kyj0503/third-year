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
        // 환경 변수에서 값을 읽음
        String keyFilePath = System.getenv("GCS_KEY_FILE");
        this.bucketName = System.getenv("GCS_BUCKET_NAME");

        // 키 파일과 버킷 이름 확인
        if (keyFilePath == null || keyFilePath.isEmpty()) {
            throw new IllegalArgumentException("Google Cloud Storage key file path is not provided.");
        }
        if (this.bucketName == null || this.bucketName.isEmpty()) {
            throw new IllegalArgumentException("Google Cloud Storage bucket name is not provided.");
        }

        // GoogleCredentials를 사용하여 인증
        try (InputStream keyFileStream = getClass().getClassLoader().getResourceAsStream(keyFilePath)) {
            if (keyFileStream == null) {
                throw new IllegalArgumentException("Key file not found: " + keyFilePath);
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
