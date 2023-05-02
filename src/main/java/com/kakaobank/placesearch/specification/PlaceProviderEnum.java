package com.kakaobank.placesearch.specification;

import lombok.Getter;

@Getter
public enum PlaceProviderEnum {

    KAKAO("kakao"), NAVER("naver");

    private final String code;

    PlaceProviderEnum(String code) {
        this.code = code;
    }

    public static PlaceProviderEnum codeOf(String code) {
        for(PlaceProviderEnum target: PlaceProviderEnum.values()) {
            if(target.getCode().equals(code)) {
                return target;
            }
        }

        // TODO: 예외처리
        throw new RuntimeException("error");
    }
}
