package com.kakaobank.placesearch.service;


import com.kakaobank.placesearch.domain.keyword.Keyword;
import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.dto.response.PlaceResponseDto;

import java.util.List;

public interface SearchService {

    List<PlaceResponseDto> searchPlace(String keyword);
}