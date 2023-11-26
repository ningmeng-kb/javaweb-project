package com.hws.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "githubclub.config")
@Slf4j
public class GithubClubUtil {
    @Autowired
    HttpUtils httpUtils;


    /**
     * 用户名
     */
    private String username;

    /**
     * token
     */
    private String token;

    /**
     * 仓库名
     */
    private String depository;

    /**
     * 统一提交信息
     */
    private String message;

    /**
     * 自定义域名
     */
    private String domain;

    private String GITHUBAPI = "https://api.github.com";

    private String DOWAPI = "https://raw.githubusercontent.com/ningmeng-kb/image/main/";

    /**
     * 文件推送到github
     * @param newFileName  文件名
     * @param content json 数据格式 {"message":"test commit","content":"bXkgbmV3IGZpbGUgY29udGVudHM="}
     * @return
     */
    
    
    /*
    *     public HashMap<String, Object> upload(MultipartFile image, String content){

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（不能重复） -uuid
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名：{}",newFileName);

        String url = GITHUBAPI + "/repos/"+username+"/"+depository+"/contents/"+newFileName;
        log.info("新的url：{}",url);

        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization","token "+token);
        header.put("Accept","Accept: application/vnd.github.v3+json");
        log.info("新的header：{}",header);
        String json = "{\"message\":\""+message+"\",\"content\":\""+content+"\"}";
        log.info("新的json：{}",json);
        log.info("新的httpUtils：{}",httpUtils);
        String res = httpUtils.putJson(url, json, header);

        return filterResultForContentFile(res,newFileName);*/
    public HashMap<String, Object> contentFile(String newFileName, String content){
        

        
        String url = GITHUBAPI + "/repos/"+username+"/"+depository+"/contents/"+newFileName;
        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization","token "+token);
        header.put("Accept","Accept: application/vnd.github.v3+json");
        String json = "{\"message\":\""+message+"\",\"content\":\""+content+"\"}";
        String res = httpUtils.putJson(url, json, header);
        return filterResultForContentFile(res,newFileName);
    }

    /**
     * 处理调用接口后返回的值
     * @param res
     * @param newFileName
     * @return
     */
    private HashMap<String,Object> filterResultForContentFile(String res,String newFileName){
        log.info("res:{}",res);
        Map<String, Object> map = JSONObject.parseObject(res);
        log.info("map:{}",map);
        Map<String, Object> commit = (Map<String, Object>) map.get("commit");
        log.info("map.get:{}",map.get("commit"));
        log.info("commit的内容：{}",commit);
        HashMap<String, Object> result = new HashMap<>();
        if(commit.get("message").equals(message)){
            result.put("success",true);
            result.put("data", DOWAPI+newFileName);
        }else{
            result.put("success",false);
        }

        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDepository() {
        return depository;
    }

    public void setDepository(String depository) {
        this.depository = depository;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}