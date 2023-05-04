package com.kakaobank.placesearch.service.impl;

import com.kakaobank.placesearch.domain.keyword.Keyword;
import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;
import com.kakaobank.placesearch.dto.response.ResponseRankingDto;
import com.kakaobank.placesearch.feign.client.KakaoLocalSearchFeignClient;
import com.kakaobank.placesearch.feign.client.NaverSearchFeignClient;
import com.kakaobank.placesearch.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {


    private static final String KAKAO_HEADER_PREFIX = "KakaoAK";

    private final KakaoLocalSearchFeignClient kakaoLocalSearchFeignClient;

    private final NaverSearchFeignClient naverSearchFeignClient;

    private final RedisTemplate<String, Integer> redisTemplate;


    @Override
    public List<Place> searchPlace(String keyword) {
        // redis 등록 필요

        KakaoPlaceResponseDto responseDto =
                kakaoLocalSearchFeignClient.call(
                        "KakaoAK 16d5f6a7bef175b8649df70e9249e996",
                        1,
                        5,
                        "accuracy",
                        keyword);

        NaverPlaceResponseDto naverPlaceResponseDto =
                naverSearchFeignClient.call(
                        "4RIC_H_X_eYsIeYbZnSo",
                        "okDpCnKRyd",
                        5,
                        1,
                        "random",
                        keyword);


        List<Place> list = new ArrayList<>();

        



        return null;
    }




//    @Override
//    public List<Keyword> getKeywordRank() {
//
//        String key = "ranking";
//        ZSetOperations<String, Integer> stringStringZSetOperations = redisTemplate.opsForZSet();
//        Set<ZSetOperations.TypedTuple<Integer>> typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, 10);
//        List<ResponseRankingDto> collect = typedTuples.stream().map(ResponseRankingDto::convertToResponseRankingDto).collect(Collectors.toList());
//
//        return collect;
//    }
}
