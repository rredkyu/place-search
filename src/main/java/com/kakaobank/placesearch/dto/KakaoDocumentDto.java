package com.kakaobank.placesearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class KakaoDocumentDto {
    private String id;

    @JsonProperty(value = "place_name")
    private String placeName;

    @JsonProperty(value = "category_name")
    private String categoryName;

    @JsonProperty(value = "category_group_code")
    private String categoryGroupCode;

    @JsonProperty(value = "category_group_name")
    private String categoryGroupName;

    private String phone;

    @JsonProperty(value = "address_name")
    private String addressName;

    @JsonProperty(value = "road_address_name")
    private String roadAddressName;

    @JsonProperty(value = "x")
    private String posX;

    @JsonProperty(value = "y")
    private String posY;

    @JsonProperty(value = "place_url")
    private String placeUrl;

    private String distance;
}
