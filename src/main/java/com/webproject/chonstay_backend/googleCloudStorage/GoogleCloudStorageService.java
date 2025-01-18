package com.webproject.chonstay_backend.googleCloudStorage;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GoogleCloudStorageService {

    @Value("${gcs.bucket-name}")
    private String bucketName;

    private final Storage storage;

    public GoogleCloudStorageService() {
        // Google Cloud Storage 초기화
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        Bucket bucket = storage.get(bucketName);
        Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

        return blob.getMediaLink(); // 업로드된 파일의 URL 반환
    }
}
