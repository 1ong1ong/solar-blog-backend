package com.cxlsky.pojo.bean;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
        String fileUrl = "";
        if (!file.isEmpty()) {
            log.error("文件为空");
            return fileUrl;
        }
        String originalFilename = file.getOriginalFilename(); // 文件名
        String userDIr = System.getProperty("user.dir") + File.separator + "temp" + File.separator;
        String newPath = userDIr + originalFilename;
        File dest = new File(newPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            minioClient.putObject(bucketName, fileName, newPath);
            deleteTempFile(dest);
            fileUrl = host + bucketName + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileUrl;

    }

    private void deleteTempFile(File file) {
        boolean delete = file.delete();
        if (!delete) {
            file.deleteOnExit();
        }
    }
}
