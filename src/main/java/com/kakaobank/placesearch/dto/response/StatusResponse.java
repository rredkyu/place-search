package com.kakaobank.placesearch.dto.response;

import com.kakaobank.placesearch.exception.ResponseCode;
import lombok.Getter;

@Getter
public class StatusResponse {

    private String code;

    private String message;

    public StatusResponse(ResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getMessage());
    }


    public StatusResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
