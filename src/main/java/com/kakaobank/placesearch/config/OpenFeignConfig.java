package com.kakaobank.placesearch.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.kakaobank.placesearch.client")
public class OpenFeignConfig {

}
