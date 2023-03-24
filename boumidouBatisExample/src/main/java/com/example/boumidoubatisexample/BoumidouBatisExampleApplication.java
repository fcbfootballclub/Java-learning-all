package com.example.boumidoubatisexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.boumidoubatisexample.mapper")
public class BoumidouBatisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoumidouBatisExampleApplication.class, args);
    }

}
