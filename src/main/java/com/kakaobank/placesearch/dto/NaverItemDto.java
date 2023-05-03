package com.kakaobank.placesearch.dto;

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
}
