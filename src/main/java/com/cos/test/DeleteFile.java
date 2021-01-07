package com.cos.test;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

/**
 * TODO
 *
 * @author hsh
 * @create 2021年01月07日
 */
public class DeleteFile {

    public static void main(String[] args) {



        String secretId = "AKIDhQt62jSMGm8E4dvqMYewxiKL0UfAqPDm";
        String secretKey = "UjrjV1GEzBE8vT1KNAakfK3XmKcgbIVT";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucketName = "test-1259716940";
        String key = "ims.xls";

        cosClient.deleteObject(bucketName, key);
    }
}