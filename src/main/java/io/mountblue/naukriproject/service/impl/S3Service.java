package io.mountblue.naukriproject.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.*;

import java.nio.file.Paths;

@Service
public class S3Service {
    private final S3Client s3Client;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(String key, String filePath) {
        // Upload the file to S3
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(request, RequestBody.fromFile(Paths.get(filePath)));

        // Generate and return the file URL
        S3Utilities s3Utilities = s3Client.utilities();
        return s3Utilities.getUrl(b -> b.bucket(bucketName).key(key)).toString();
    }

}
