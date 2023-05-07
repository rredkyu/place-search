package com.kakaobank.placesearch.feign.decoder;

import com.kakaobank.placesearch.exception.PlaceSearchException;
import com.kakaobank.placesearch.exception.ResponseCode;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        final HttpStatus httpStatus = HttpStatus.resolve(response.status());

        if (httpStatus == HttpStatus.NOT_FOUND) {
            log.warn("Http Status = {}", httpStatus);
            throw new PlaceSearchException(ResponseCode.NOT_FOUND_PLACE);
        } else if(httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.warn("Http Status = {}", httpStatus);
            throw new PlaceSearchException(ResponseCode.INTERNAL_SERVER_ERROR);
        }



        return errorDecoder.decode(methodKey, response);
    }
}
