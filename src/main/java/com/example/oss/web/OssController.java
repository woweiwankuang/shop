package com.example.oss.web;

import com.example.oss.domain.model.Base64File;
import com.example.oss.domain.service.MinioOssService;
import com.example.utils.IoUtils;
import com.sun.javaws.exceptions.InvalidArgumentException;
import io.minio.ObjectStat;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.NoResponseException;
import io.minio.errors.RegionConflictException;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

/**
 * 文件上传服务
 */
@Slf4j
@RestController
@RequestMapping("/oss")
@AllArgsConstructor
public class OssController {

    private MinioOssService minioOssService;

    /**
     * 上传对象
     */
    @PostMapping("/upload")
    public String putObject(@RequestParam("file") MultipartFile file,
                                @RequestParam(value = "Content-Type", required = false) String type,
                                @RequestParam("bucketName") String bucketName) throws IOException, XmlPullParserException, NoSuchAlgorithmException, RegionConflictException, InvalidKeyException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException, io.minio.errors.InternalException, io.minio.errors.InvalidArgumentException {
        Assert.isTrue(!file.isEmpty(), "文件为空！");

        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        long size = file.getSize();
        if (StringUtils.hasLength(type) && !Objects.equals("null", type)) {
            contentType = type;  // 使用传输的类型覆盖掉原始类型
        }
        String objectName = mix(file.getOriginalFilename());
        this.minioOssService.putObject(bucketName, objectName, inputStream, contentType);
        String path = String.format("/oss/%s/%s", bucketName, objectName);
        log.info("文件上传成功,url:{},contentType:{},fileName:{},size:{}", path, contentType, objectName, size);
        return path;
    }

    /**
     * 上传对象，使用Base64
     */
    @PostMapping("/upload-b64")
    public String putObject(@RequestBody Base64File base64File,
                                @RequestParam("bucketName") String bucketName) throws IOException, XmlPullParserException, NoSuchAlgorithmException, RegionConflictException, InvalidKeyException, InvalidArgumentException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException, io.minio.errors.InternalException, io.minio.errors.InvalidArgumentException {
        Assert.hasText(base64File.getBody(), "文件为空！");
        Assert.hasText(base64File.getFilename(), "原始文件名为空！");
        Assert.hasText(base64File.getContentType(), "Content Type为空！");

        byte[] decode = Base64.getDecoder().decode(base64File.getBody());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(decode);
        String contentType = base64File.getContentType();
        String objectName = mix(base64File.getFilename());

        this.minioOssService.putObject(bucketName, objectName, inputStream, contentType);

        String path = String.format("/oss/%s/%s", bucketName, objectName);
        log.info("文件上传成功,url:{},contentType:{},fileName:{}", path, contentType, objectName);
        return path;
    }


    /**
     * 下载对象
     */
    @GetMapping("/**")
    public void getObject(HttpServletRequest request, HttpServletResponse response) throws IOException, XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, InvalidArgumentException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException, io.minio.errors.InternalException, io.minio.errors.InvalidArgumentException {
        String path = URLDecoder.decode(request.getRequestURI(), "utf8");

        String[] strings = path.split("/");
        Assert.isTrue(strings.length == 4, "资源路径异常！");
        String bucket = strings[2];
        String object = strings[3];
        Optional<InputStream> fi = this.minioOssService.getObject(bucket, object);
        Optional<ObjectStat> source = this.minioOssService.getObjectStat(bucket, object);
        response.setCharacterEncoding("UTF-8");
        if (source.isPresent()) {
            ObjectStat obj = source.get();  //获取资源状态
            long length = obj.length();
            String contentType = obj.contentType();
            response.setHeader("Content-Length", String.valueOf(length)); // 设置文件大小，从而看下载进度
            response.setContentType(contentType);  // 取到存储的文件类型，并设置到response中,从而让一些文件能够在浏览器端预览
            if (fi.isPresent()) {
                try (
                        InputStream in = fi.get();
                        ServletOutputStream out = response.getOutputStream()) {
                    IoUtils.pipe(in, out);
                    log.info("download file success!:bucket:{},filePath:{},contentType:{},size:{}", bucket, object, contentType, length);
                }
            }
        }
    }

    private static String mix(String origin) {
        Assert.notNull(origin, "原始文件名丢失！");
        String suffix = origin.replaceAll(".*\\.", "");
        String name = origin.replaceFirst("\\." + suffix, "");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(formatter);
        return name + "-" + date + "." + suffix;
    }
}
