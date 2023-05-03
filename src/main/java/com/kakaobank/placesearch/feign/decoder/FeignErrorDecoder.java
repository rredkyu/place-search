package com.kakaobank.placesearch.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {

    private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        final HttpStatus httpStatus = HttpStatus.resolve(response.status());

        if (httpStatus == HttpStatus.NOT_FOUND) {
            log.warn("Http Status = {}", httpStatus);
        }



        return errorDecoder.decode(methodKey, response);
    }
}
