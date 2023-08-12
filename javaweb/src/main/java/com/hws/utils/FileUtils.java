package com.hws.utils;

import com.hws.pojo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Component
@Slf4j
public class FileUtils {
    public String upload(MultipartFile image) throws IOException {

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（不能重复） -uuid
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名：{}",newFileName);

        //将文件存储在服务器磁盘目录中 E:\spring\images
        File directory = new File("E:\\spring\\images\\"+newFileName);
        image.transferTo(directory);
        String uploadDir = "E:\\spring\\images\\";

        String destFile = (uploadDir + newFileName);

        return destFile;
    }
}
