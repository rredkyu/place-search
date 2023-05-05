package com.kakaobank.placesearch.exception;

public enum ResponseCode {

    OK("P000", "정상 처리"),

    INTERNAL_SERVER_ERROR("P100", "서버 에러 발생"),

    INVALID_INPUT("P200", "요청 값의 형식이 올바르지 않습니다"),

    NOT_FOUND_RANKING("P300", "순위가 존재 하지 않습니다."),;

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
