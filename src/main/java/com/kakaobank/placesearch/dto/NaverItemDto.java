package com.kakaobank.placesearch.dto;

import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.specification.PlaceSearchPlatform;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class NaverItemDto {

    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    private Integer mapX;
    private Integer mapY;

    public static Place toEntity(NaverItemDto naverItemDto) {
        return Place.builder()
                .provider(PlaceSearchPlatform.NAVER)
                .telephone(naverItemDto.getTelephone())
                .address(naverItemDto.getAddress())
                .name(naverItemDto.getTitle())
                .normalizedName(Place.normalized(naverItemDto.getTitle()))
                .roadAddress(naverItemDto.getRoadAddress())
                .build();
    }
}
