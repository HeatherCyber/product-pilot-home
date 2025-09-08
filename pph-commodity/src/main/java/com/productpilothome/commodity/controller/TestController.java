package com.productpilothome.commodity.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.productpilothome.common.utils.R;

import javax.annotation.Resource;

/**
 * @author Heather
 * @version 1.0
 */
@RestController
@Slf4j
public class TestController {
    // test upload file by aliyun oss SDK
    @RequestMapping("/test")
    public R testUpload() throws FileNotFoundException {
        log.info("test Upload");
        // yourEndpoint should be filled with the Endpoint corresponding to the region where the Bucket is located.
        String endpoint = "oss-us-west-1.aliyuncs.com";
        // Aliyun account AccessKey has access to all APIs, which is very risky. It is strongly recommended to create and use RAM users for API access or daily operations, please log in to the RAM console to create RAM users.
        // Sensitive information has been moved to environment variables or configuration center
        String accessKeyId = System.getenv("ALIYUN_ACCESS_KEY");
        String accessKeySecret = System.getenv("ALIYUN_SECRET_KEY");
        // Create OSSClient instance.
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // Fill in the complete path of the local file. If no local path is specified, it will upload the file stream from the local path corresponding to the project where the sample program belongs by default.
        InputStream inputStream = new FileInputStream("/Users/heatherwang/Desktop/IO Stream File/Ronan.jpeg");
        // Fill in the Bucket name (such as examplebucket) and Object complete path (such as exampledir/exampleobject.txt) in sequence. The Object complete path cannot contain the Bucket name.
        ossClient.putObject("product-pilot-home", "Ronan.jpeg", inputStream);
        // Close OSSClient.
        ossClient.shutdown();
        return null;
    }


//  Test usage (integrated springcloud + alibaba oss) simplified way, upload files
//  Auto-assemble OSSClient instance
    @Resource
    private OSSClient ossClient;

    @RequestMapping("/test2")
    public R testUpload2() throws FileNotFoundException {
        log.info("test Upload2");
        InputStream inputStream =
                new FileInputStream("/Users/heatherwang/Desktop/IO Stream File/Ronan.jpeg");
        ossClient.putObject("product-pilot-home", "test2.jpg", inputStream);
        ossClient.shutdown();
        System.out.println("Upload2 ok~");
        return null;
    }


}
