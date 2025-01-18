package com.webproject.chonstay_backend.googleCloudStorage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class GCSService {

    private final Storage storage;
    private final String bucketName = "gdgoc_storage"; // 하드코딩된 bucket name

    public GCSService() throws IOException {
        // 환경 변수에서 JSON 키 파일 경로 가져오기
        String keyFilePath = System.getenv("GCS_KEY_FILE");

        if (keyFilePath == null || keyFilePath.isBlank()) {
            throw new IllegalArgumentException("GCS_KEY_FILE 환경 변수가 설정되지 않았습니다.");
        }

        // GoogleCredentials를 사용하여 인증
        try (InputStream keyFileStream = new FileInputStream(keyFilePath)) {
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
