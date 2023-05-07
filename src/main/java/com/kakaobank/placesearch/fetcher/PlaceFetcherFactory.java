package com.kakaobank.placesearch.fetcher;

import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.feign.client.KakaoLocalSearchFeignClient;
import com.kakaobank.placesearch.feign.client.NaverSearchFeignClient;
import com.kakaobank.placesearch.fetcher.impl.KakaoPlaceFetcherImpl;
import com.kakaobank.placesearch.fetcher.impl.NaverPlaceFetcherImpl;
import com.kakaobank.placesearch.specification.PlaceSearchPlatform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.Executor;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlaceFetcherFactory {

    private final KakaoPlaceFetcherImpl kakaoPlaceFetcher;

    private final NaverPlaceFetcherImpl naverPlaceFetcher;


    private Map<PlaceSearchPlatform, PlaceFetcher> FACTORY;

    @PostConstruct
    public void init() {
        FACTORY = Map.of(
                PlaceSearchPlatform.KAKAO, kakaoPlaceFetcher,
                PlaceSearchPlatform.NAVER, naverPlaceFetcher
        );
    }


    public PlaceFetcher placeFetcherFrom(PlaceSearchPlatform platform) {
        log.info("Returning PostFetcher implementation for platform {}", platform);
        return FACTORY.get(platform);
    }
}
