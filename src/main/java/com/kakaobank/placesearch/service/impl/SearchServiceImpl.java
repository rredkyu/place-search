package com.kakaobank.placesearch.service.impl;

import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.dto.KakaoDocumentDto;
import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import com.kakaobank.placesearch.dto.NaverItemDto;
import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;
import com.kakaobank.placesearch.dto.response.PlaceResponseDto;
import com.kakaobank.placesearch.feign.client.KakaoLocalSearchFeignClient;
import com.kakaobank.placesearch.feign.client.NaverSearchFeignClient;
import com.kakaobank.placesearch.service.RankingService;
import com.kakaobank.placesearch.service.SearchService;
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

    private final RedisTemplate<String, String> redisTemplate;

    private final RankingService rankingService;

    @Override
    public List<PlaceResponseDto> searchPlace(String keyword) {
        rankingService.addKeyword(keyword);

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

        for (KakaoDocumentDto res : responseDto.getDocuments()) {
            // 각 값에 대한 검증도 필요
            list.add(Place.builder()
                    .address(res.getAddressName())
                    .name(res.getPlaceName())
                    .roadAddress(res.getRoadAddressName())
                    .telephone(res.getPhone())
                    .build());
        }


        List<Place> origin = new ArrayList<>(list);
        for (NaverItemDto res : naverPlaceResponseDto.getItems()) {
            for (Place place : list) {
                // address, roadAddress, telePhone 모두 없을때
                // 최후 이름으로 비교 필요

                if(!res.getAddress().equals(place.getAddress()) ||
                        !res.getRoadAddress().equals(place.getRoadAddress()) ||
                        !res.getTelephone().equals(place.getTelephone())){
                    origin.add(Place.builder()
                                    .telephone(res.getTelephone())
                                    .roadAddress(res.getRoadAddress())
                                    .name(res.getTitle())
                                    .address(res.getAddress())
                            .build());
                }
            }
        }


        return createPlaceResponseDtoList(origin);
    }

    private List<PlaceResponseDto> createPlaceResponseDtoList(List<Place> places) {

        return places.stream()
                .map(PlaceResponseDto::of)
                .collect(Collectors.toList());

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
