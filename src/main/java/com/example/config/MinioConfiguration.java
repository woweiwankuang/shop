package com.example.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * Minio客户端
 */
@Configuration
public class MinioConfiguration {
    /**
     * 采用了minio的client
     * @param env 环境变量
     * @return 客户端
     * @throws InvalidPortException
     * @throws InvalidEndpointException
     */
    @Bean
    public MinioClient minioClient(Environment env) throws InvalidPortException, InvalidEndpointException {
        String endpoint = env.getProperty("minio.endpoint");
        String accessKey = env.getProperty("minio.key.access");
        String secretKey = env.getProperty("minio.key.secret");
        Assert.notNull(endpoint, "Minio endpoint can't be null!");
        return new MinioClient(endpoint, accessKey, secretKey);
    }
}
