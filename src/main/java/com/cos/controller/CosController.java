package com.cos.controller;

import com.cos.source.ZSYResult;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * cosController
 *
 * @author hsh
 * @create 2021年01月07日
 */

@RestController
@RequestMapping("/cos")
public class CosController {


    @Autowired
    private COSClient cosClient;

    @Value(value = "${cos.appid}")
    private String appid;

    @Value(value = "${cos.region}")
    private String region;


    @PutMapping("/upload/{bucket}/{file}")
    public ZSYResult uploadFile(@PathVariable("bucket")String bucket,@PathVariable("file")String file){

        if(!cosClient.doesBucketExist(bucket + "-" + appid)){
            throw new RuntimeException(String.format("%s不存在,请创建!",bucket));
        }
        // 指定要上传的文件
        File localFile = new File("C:\\Users\\r\\Desktop\\" + file );
        if(!localFile.exists() || !localFile.isFile()){
            throw new RuntimeException("该路径不存在或者不是一个文件");
        }
        // 指定要上传到的存储桶
        bucket = bucket + "-" + appid;
        // 指定要上传到 COS 上对象键
        String key = localFile.getName();

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, localFile);

        try{
            cosClient.putObject(putObjectRequest);
        }catch (Exception e){
            throw new RuntimeException("上传文件失败");
        }

        String url = "https://" + bucket + ".cos." + region + ".myqcloud.com/" + key;
        return ZSYResult.success().data(url);
    }

}