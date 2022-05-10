package com.example.oss.domain.service;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.NoResponseException;
import io.minio.errors.RegionConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * Minio封装
 */
@Service
@AllArgsConstructor
public class MinioOssService {

    private MinioClient minioClient;

    /**
     * 将文件存储到 minio中
     * @param bucketName 桶名称。名称只能小写，-，严格规范
     * @param objectName 文件名称，最终的存储路径
     * @param inputStream 输入流
     * @param contentType 类型，根据文件的类型获取
     * @throws IOException 异常
     * @throws InvalidKeyException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InsufficientDataException 异常
     * @throws InvalidArgumentException 异常
     * @throws InternalException 异常
     * @throws NoResponseException 异常
     * @throws InvalidBucketNameException 异常
     * @throws XmlPullParserException 异常
     * @throws ErrorResponseException 异常
     * @throws RegionConflictException 异常
     */
    public void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException {
        Assert.isTrue(bucketName.length() > 2, "桶名称长度需要大于2！");
        if (!this.minioClient.bucketExists(bucketName)) {
            this.minioClient.makeBucket(bucketName);
        }
        this.minioClient.putObject(bucketName, objectName, inputStream, contentType);
    }

    /**
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @return 输入流
     * @throws IOException 异常
     * @throws InvalidKeyException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InsufficientDataException 异常
     * @throws InvalidArgumentException 异常
     * @throws InternalException 异常
     * @throws NoResponseException 异常
     * @throws InvalidBucketNameException 异常
     * @throws XmlPullParserException 异常
     * @throws ErrorResponseException 异常
     */
    public Optional<InputStream> getObject(String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        InputStream inputStream = this.minioClient.getObject(bucketName, objectName);
        return Optional.of(inputStream);
    }

    //获取文件状态，比如大小
    public Optional<ObjectStat> getObjectStat(String bucketName, String objectName) throws IOException, XmlPullParserException, InsufficientDataException, NoSuchAlgorithmException, NoResponseException, InternalException, InvalidKeyException, InvalidBucketNameException, ErrorResponseException {
        ObjectStat objectStat = minioClient.statObject(bucketName, objectName);
        return Optional.of(objectStat);
    }
}
