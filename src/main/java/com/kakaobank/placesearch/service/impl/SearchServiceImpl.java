package com.kakaobank.placesearch.service.impl;

import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.domain.place.Places;
import com.kakaobank.placesearch.dto.KakaoDocumentDto;
import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import com.kakaobank.placesearch.dto.NaverItemDto;
import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;
import com.kakaobank.placesearch.dto.response.PlaceResponseDto;
import com.kakaobank.placesearch.feign.client.KakaoLocalSearchFeignClient;
import com.kakaobank.placesearch.feign.client.NaverSearchFeignClient;
import com.kakaobank.placesearch.service.RankingService;
import com.kakaobank.placesearch.service.SearchService;
import com.kakaobank.placesearch.service.SearchServiceAsync;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {


    private static final String KAKAO_HEADER_PREFIX = "KakaoAK";

    private final KakaoLocalSearchFeignClient kakaoLocalSearchFeignClient;

    private final NaverSearchFeignClient naverSearchFeignClient;


    private final RankingService rankingService;

    private final SearchServiceAsync searchServiceAsync;

    @Override
    public List<PlaceResponseDto> searchPlace(String keyword) {
        rankingService.addKeyword(keyword);

//        KakaoPlaceResponseDto kakaoPlaceResponseDto =
//                kakaoLocalSearchFeignClient.call(
//                        "KakaoAK 16d5f6a7bef175b8649df70e9249e996",
//                        1,
//                        5,
//                        "accuracy",
//                        keyword);
//
//        NaverPlaceResponseDto naverPlaceResponseDto =
//                naverSearchFeignClient.call(
//                        "4RIC_H_X_eYsIeYbZnSo",
//                        "okDpCnKRyd",
//                        1,
//                        5,
//                        "random",
//                        keyword);
//
//        Places places = Places.newInstance(kakaoPlaceResponseDto, naverPlaceResponseDto);

        List<Place> places = searchServiceAsync.fetchPlaces(keyword).join();


        return createPlaceResponseDtoList(places);
    }

    private List<PlaceResponseDto> createPlaceResponseDtoList(List<Place> places) {

        return places.stream()
                .map(PlaceResponseDto::of)
                .collect(Collectors.toList());

    }
}
