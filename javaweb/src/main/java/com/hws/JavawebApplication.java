package com.hws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启了对servlet组件的支持
@SpringBootApplication
@Slf4j
public class JavawebApplication {

    public static void main(String[] args) {
        log.info("项目启动成功");
        SpringApplication.run(JavawebApplication.class, args);
    }

}
