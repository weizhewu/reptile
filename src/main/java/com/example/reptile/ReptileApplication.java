package com.example.reptile;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 位哲武
 */
@SpringBootApplication
@ForestScan(basePackages = "com.example.reptile.forest")
public class ReptileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReptileApplication.class, args);
    }

}
