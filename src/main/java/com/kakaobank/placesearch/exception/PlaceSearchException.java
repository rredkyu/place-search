package com.kakaobank.placesearch.exception;

import lombok.Getter;

@Getter
public class PlaceSearchException extends RuntimeException{

    private final ResponseCode responseCode;
    private final String customResponseMessage;

    public PlaceSearchException(ResponseCode responseCode) {
        this(responseCode, responseCode.getMessage());
    }

    public PlaceSearchException(ResponseCode responseCode, String customResponseMessage) {
        super(customResponseMessage);
        this.responseCode = responseCode;
        this.customResponseMessage = customResponseMessage;
    }

}
