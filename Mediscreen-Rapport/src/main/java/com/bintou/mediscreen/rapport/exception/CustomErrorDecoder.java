package com.bintou.mediscreen.rapport.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder{

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();


    @Override
    public Exception decode(String invoker, Response response) {
        if(response.status() == 404) {
            String resourceNotFoundException = "Rapport non trouvé";
            return new ResourceNotFoundException(resourceNotFoundException);
        }
        return defaultErrorDecoder.decode(invoker, response);
    }
}
