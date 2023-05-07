package com.kakaobank.placesearch.service.impl;

import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.fetcher.PlaceFetcher;
import com.kakaobank.placesearch.fetcher.PlaceFetcherFactory;
import com.kakaobank.placesearch.fetcher.impl.KakaoPlaceFetcherImpl;
import com.kakaobank.placesearch.fetcher.impl.NaverPlaceFetcherImpl;
import com.kakaobank.placesearch.service.SearchServiceAsync;
import com.kakaobank.placesearch.specification.PlaceSearchPlatform;
import com.kakaobank.placesearch.utils.PlaceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchServiceAsyncImpl implements SearchServiceAsync {


    private final PlaceFetcherFactory placeFetcherFactory;

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


    @Override
    public CompletableFuture<List<Place>> fetchPlaces(String keyword) {


        List<CompletableFuture<List<Place>>> futures = Stream.of(PlaceSearchPlatform.values())
                .map(platform -> {
                    PlaceFetcher fetcher = FACTORY.get(platform);
                    return fetcher.fetchPlacesFor(keyword);
                }).collect(toList());


//                FACTORY.get(PlaceSearchPlatform.KAKAO).fetchPlacesFor(keyword)
//                .thenCombine(FACTORY.get(PlaceSearchPlatform.NAVER).fetchPlacesFor(keyword),
//                        (kakao, naver) -> {
//                            List<Place> duplicatedList = PlaceUtils.findDuplicatedElementFrom(
//                                    Stream.concat(kakao.stream(), naver.stream())
//                                    .collect(toList()));
//
//
//
//                            return duplicatedList;
//                        });


//

        return futures.stream()
                .reduce(combineApiCalls())
                .orElse(CompletableFuture.completedFuture(emptyList()));

    }


    private BinaryOperator<CompletableFuture<List<Place>>> combineApiCalls() {
        return (list1, list2) -> list1
                .thenCombine(list2, PlaceUtils::merge);
    }
}
