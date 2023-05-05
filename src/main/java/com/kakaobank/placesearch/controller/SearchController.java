package com.kakaobank.placesearch.controller;

import com.kakaobank.placesearch.dto.response.CommonListResponse;
import com.kakaobank.placesearch.dto.response.PlaceResponseDto;
import com.kakaobank.placesearch.dto.response.RankingResponseDto;
import com.kakaobank.placesearch.service.RankingService;
import com.kakaobank.placesearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;


@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    private final RankingService rankingService;


    @GetMapping("/place")
    public CommonListResponse<PlaceResponseDto> searchPlace(@RequestParam @NotBlank String keyword) {
        List<PlaceResponseDto> placeResponseDtos = searchService.s
        earchPlace(keyword);

        return new CommonListResponse<>(placeResponseDtos);
    }


    @GetMapping("/keyword/ranking")
    public CommonListResponse<RankingResponseDto> getKeywordRanking() {
        List<RankingResponseDto> rankingResponseDtos = rankingService.getRankingList();

        return new CommonListResponse<>(rankingResponseDtos);
    }

}
