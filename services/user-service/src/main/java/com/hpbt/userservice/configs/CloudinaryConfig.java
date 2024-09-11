package com.hpbt.userservice.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:env.properties")
@RequiredArgsConstructor
public class CloudinaryConfig {
    private final Environment env;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", env.getProperty("cloudinary.cloud-name"),
                "api_key", env.getProperty("cloudinary.api-key"),
                "api_secret", env.getProperty("cloudinary.api-secret")
        ));
    }
}
