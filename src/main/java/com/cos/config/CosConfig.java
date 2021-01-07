package com.cos.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * cos 配置类
 *
 * @author hsh
 * @create 2021年01月07日
 */
@Configuration
public class CosConfig {

    @Value(value = "${cos.secretId}")
    private String secretId;

    @Value(value = "${cos.secretKey}")
    private String secretKey;

    @Value(value = "${cos.region}")
    private String regionName;

    @Bean
    public COSClient getConsClient(){

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setConnectionTimeout(30000);
        clientConfig.setSocketTimeout(30000);

        COSClient cosClient = new COSClient(cred, clientConfig);

        return cosClient;
    }

}