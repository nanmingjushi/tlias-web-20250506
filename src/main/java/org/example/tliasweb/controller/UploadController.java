package org.example.tliasweb.controller;

/* 
    @author nanchao 
    @date 2025/4/10
*/


import org.example.tliasweb.pojo.Result;
import org.example.tliasweb.utils.AliyunOSSOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
public class UploadController {
    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    //本地磁盘存储方案
    /*
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("文件上传，name={},age={},file={}",name,age,file);
        if(!file.isEmpty()){
            //获取原始文件名
            String originalFilename = file.getOriginalFilename();
            //后缀
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //用UUID生成唯一文件名
            String newFileName = UUID.randomUUID() +extName;
            //将接受的文件转存到磁盘中
            file.transferTo(new File("/Users/nanchao/Documents/南超/0 头像/"+newFileName));

        }
        return Result.success();
    }*/


    //阿里云OSS存储方案

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传，file={}", file.getOriginalFilename());
        //将文件交给OSS存储原理
        if (!file.isEmpty()) {
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
            // 上传文件
            String url = aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);
            log.info("文件上传OSS，url={}", url);
            return Result.success(url);
        }

        return Result.error("上传失败");

    }


}
