package com.example.file_upload_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FileUploadSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadSystemApplication.class, args);
    }

}
