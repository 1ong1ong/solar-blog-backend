package com.cxlsky.config;

import com.cxlsky.pojo.bean.OssClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssClientConfig {

    @Value("${oss.host}")
    private String host;
    @Value("${oss.access-key}")
    private String accessKey;
    @Value("${oss.secret-key}")
    private String secretKey;

    @Bean
    public OssClient ossClient() {
        return new OssClient(host, accessKey, secretKey);
    }
}
