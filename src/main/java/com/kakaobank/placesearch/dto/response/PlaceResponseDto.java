package com.kakaobank.placesearch.dto.response;

import com.kakaobank.placesearch.domain.place.Place;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PlaceResponseDto {
    private String title;

    public static PlaceResponseDto of(Place place) {
        return PlaceResponseDto.builder()
                .title(place.getName())
                .build();
    }
}
