package com.hws.controller;

import com.hws.pojo.Result;
import com.hws.utils.FileUtils;
import com.hws.utils.GithubClubUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;


@Slf4j
@RestController
public class UploadController {

    @Autowired
    private GithubClubUtil githubClubUtil;

    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile image) {
        log.info("文件上传：{}", image.getOriginalFilename());

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（不能重复） -uuid
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名：{}",newFileName);

        org.apache.commons.codec.binary.Base64 base64Encoder = new Base64();
        byte[] imageBytes = null;
        String base64EncoderImg="";


        try{
            imageBytes = image.getBytes();
            base64EncoderImg = base64Encoder.encodeToString(imageBytes);
            HashMap map =  githubClubUtil.contentFile(newFileName,base64EncoderImg);

                if((Boolean)map.get("success")){
                    return Result.success(map.get("data"));
                }else{
                    return Result.error("上传失败");
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
    }
}
