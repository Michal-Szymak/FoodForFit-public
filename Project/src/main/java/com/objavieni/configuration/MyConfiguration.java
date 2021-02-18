package com.objavieni.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
//@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "com.objavieni")
public class MyConfiguration {

    private String appID;
    private String appKey;
    private String query;

}