package com.kakaobank.placesearch.controller;

import com.kakaobank.placesearch.domain.keyword.Keyword;
import com.kakaobank.placesearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/place")
    public void searchPlace(@RequestParam String keyword) {
        searchService.searchPlace(keyword);
    }

//    @GetMapping("/keyword/rank")
//    public void getKeywordRank() {
//        List<Keyword> list = searchService.getKeywordRank();
//
//    }

//    @GetMapping("/search")
//    public CommonListResponse<PlaceResponseDto> search(@RequestParam String keyword) {
//
//    }

}
