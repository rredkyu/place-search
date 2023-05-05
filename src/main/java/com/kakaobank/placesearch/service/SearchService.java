package com.kakaobank.placesearch.service;


import com.kakaobank.placesearch.dto.response.PlaceResponseDto;

import java.util.List;

public interface SearchService {

    List<PlaceResponseDto> searchPlace(String keyword);
}