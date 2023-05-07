package com.kakaobank.placesearch.dto.response;

import com.kakaobank.placesearch.exception.ResponseCode;
import lombok.*;

@Setter
@Getter
public class CommonResponse<T> {

    private StatusResponse status = new StatusResponse(ResponseCode.OK);

    private final T data;

    public CommonResponse() {
        this.data = null;
    }

    public CommonResponse(T data) {
        this.data = data;
    }

    public CommonResponse(ResponseCode responseCode) {
        this();
        this.status = new StatusResponse(responseCode);
    }

    public CommonResponse(String code, String message) {
        this();
        this.status = new StatusResponse(code, message);
    }
}
