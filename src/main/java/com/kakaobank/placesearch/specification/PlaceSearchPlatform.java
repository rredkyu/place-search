package com.kakaobank.placesearch.specification;

import com.kakaobank.placesearch.exception.PlaceSearchException;
import com.kakaobank.placesearch.exception.ResponseCode;
import lombok.Getter;

@Getter
public enum PlaceSearchPlatform {

    KAKAO("kakao"), NAVER("naver");

    private final String code;

    PlaceSearchPlatform(String code) {
        this.code = code;
    }

    public static PlaceSearchPlatform codeOf(String code) {
        for(PlaceSearchPlatform target: PlaceSearchPlatform.values()) {
            if(target.getCode().equals(code)) {
                return target;
            }
        }

        throw new PlaceSearchException(ResponseCode.NOT_SUPPORTED_PROVIDER);
    }
}
