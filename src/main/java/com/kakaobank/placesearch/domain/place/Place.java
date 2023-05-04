package com.kakaobank.placesearch.domain.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class Place {

    private String title;
    private String telephone;
    private String address;
    private String roadAddress;



}
