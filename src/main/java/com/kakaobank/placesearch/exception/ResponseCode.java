package com.kakaobank.placesearch.exception;

public enum ResponseCode {

    OK("P000", "정상 처리"),

    INTERNAL_SERVER_ERROR("P100", "서버 에러 발생");


    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
