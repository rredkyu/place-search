package com.kakaobank.placesearch.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class NaverPlaceResponseDto {

    private List<NaverItemDto> items;
}
