package com.kakaobank.placesearch.service;


import com.kakaobank.placesearch.domain.keyword.Keyword;
import com.kakaobank.placesearch.domain.place.Place;

import java.util.List;

public interface SearchService {

    // Response 객체 정의 필요
    List<Place> searchPlace(String keyword);

//    List<Keyword> getKeywordRank();
}