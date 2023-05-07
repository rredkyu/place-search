package com.kakaobank.placesearch.dto.response;

import com.kakaobank.placesearch.exception.ResponseCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommonListResponse<T> {
    private StatusResponse status = new StatusResponse(ResponseCode.OK);

    private final List<T> data;

    public CommonListResponse() {
        this.data = null;
    }

    public CommonListResponse(List<T> data) {
        this.data = data;
    }

}
