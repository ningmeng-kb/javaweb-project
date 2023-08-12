package com.hws.controller;

import com.hws.pojo.Result;
import com.hws.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
public class UploadController {

    @Autowired
    private FileUtils fileUtils;

    @PostMapping("/upload")
    public Result upload( MultipartFile image) throws Exception {
        log.info("文件上传：{}", image.getOriginalFilename());

        String destFile = fileUtils.upload(image);


        return Result.success(destFile);
    }
}
