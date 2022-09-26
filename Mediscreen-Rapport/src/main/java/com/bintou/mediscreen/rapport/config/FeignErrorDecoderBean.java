package com.bintou.mediscreen.rapport.config;

import com.bintou.mediscreen.rapport.exception.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignErrorDecoderBean {

    @Bean
    public FeignErrorDecoder myErrorDecoder() {
        return new FeignErrorDecoder();
    }
}
