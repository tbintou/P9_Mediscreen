package com.bintou.mediscreen.rapport.config;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.*;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
public class FeignBadResponseWrapper extends HystrixBadRequestException {

    private final int status;
    private final HttpHeaders headers;
    private final String body;

    public FeignBadResponseWrapper(int status, HttpHeaders headers, String body) {
        super("Bad request");
        this.status = status;
        this.headers = headers;
        this.body = body;
    }
}
