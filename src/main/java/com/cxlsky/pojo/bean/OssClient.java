package com.cxlsky.pojo.bean;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Data
@Slf4j
public class OssClient {

    private MinioClient minioClient;
    private String host;

    public OssClient(String host, String accessKey, String secretKey) {
        try {
            this.host = host;
            this.minioClient = new MinioClient(host, accessKey, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MinIO 初始化失败！请检查配置是否正确");
        }
    }

    public String upload(MultipartFile file, String bucketName, String fileName) {
        if (file.isEmpty()) {
            log.error("文件为空");
        }
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String originalFilename = file.getOriginalFilename();// 文件名
        String userDIr = System.getProperty("user.dir") + File.separator + "temp" + File.separator;
        String newPath = userDIr + originalFilename;
        File dest = new File(newPath);
        if (!dest.getParentFile().exists()) {
            boolean mkdirs = dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            minioClient.putObject(bucketName, fileName, newPath);
            return host + bucketName + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

}
