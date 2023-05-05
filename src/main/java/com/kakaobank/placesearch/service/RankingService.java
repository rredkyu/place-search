package com.kakaobank.placesearch.service;

import com.kakaobank.placesearch.dto.response.RankingResponseDto;

import java.util.List;

public interface RankingService {

    void addKeyword(String keyword);

    List<RankingResponseDto> getRankingList();

}
