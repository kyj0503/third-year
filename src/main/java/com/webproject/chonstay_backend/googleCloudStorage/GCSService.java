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

    public GCSService() throws IOException {
        String keyFileContent = System.getenv("GCS_KEY_FILE");

        if (keyFileContent == null) {
            throw new IllegalArgumentException("GCS_KEY_FILE 또는 GCS_BUCKET_NAME 환경 변수가 설정되지 않았습니다.");
        }

        // GCS_KEY_FILE 처리: JSON 내용인지 파일 경로인지 확인
        File keyFile;
        if (keyFileContent.trim().startsWith("{")) {
            // JSON 내용인 경우 임시 파일 생성
            keyFile = createTempFile(keyFileContent);
        } else {
            // 파일 경로인 경우
            keyFile = new File(keyFileContent);
            if (!keyFile.exists()) {
                throw new IllegalArgumentException("GCS_KEY_FILE 경로에 해당하는 파일이 존재하지 않습니다: " + keyFileContent);
            }
        }

        // GoogleCredentials를 사용하여 인증
        try (InputStream keyFileStream = new FileInputStream(keyFile)) {
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(keyFileStream))
                    .build()
                    .getService();
        } finally {
            if (keyFileContent.trim().startsWith("{")) {
                keyFile.delete(); // JSON 내용에서 생성된 임시 파일 삭제
            }
        }
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        String bucketName = "gdgoc_storage";
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }

    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("gcs-key", ".json");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(content.getBytes());
        }
        return tempFile;
    }
}

