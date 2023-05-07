package com.kakaobank.placesearch.dto;


import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class KakaoPlaceResponseDto {

    private List<KakaoDocumentDto> documents;

}
