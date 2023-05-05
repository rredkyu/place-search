package com.kakaobank.placesearch.domain.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Place {

    private String name;
    private String telephone;
    private String address;
    private String roadAddress;



}
