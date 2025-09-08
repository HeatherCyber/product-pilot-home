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
        // yourEndpoint 填写 Bucket 所在地域对应的 Endpoint。
        String endpoint = "oss-us-west-1.aliyuncs.com";
        // 阿里云账号 AccessKey 拥有所有 API 的访问权限，风险很高。强烈建议您创建并使用 RAM用户进行 API 访问或日常运维，请登录 RAM 控制台创建 RAM 用户。
        // 敏感信息已移至环境变量或配置中心
        String accessKeyId = System.getenv("ALIYUN_ACCESS_KEY");
        String accessKeySecret = System.getenv("ALIYUN_SECRET_KEY");
        // 创建 OSSClient 实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("/Users/heatherwang/Desktop/IO Stream File/Ronan.jpeg");
        // 依 次 填 写 Bucket 名 称 （ 例 如 examplebucket ） 和 Object 完 整 路 径 （ 例 如exampledir/exampleobject.txt）。Object 完整路径中不能包含 Bucket 名称。
        ossClient.putObject("product-pilot-home", "Ronan.jpeg", inputStream);
        // 关闭 OSSClient。
        ossClient.shutdown();
        return null;
    }


//  测试使用 (整合 springcloud + alibaba oss) 简化方式，上传文件
//  自动装配 OSSClient 实例
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
