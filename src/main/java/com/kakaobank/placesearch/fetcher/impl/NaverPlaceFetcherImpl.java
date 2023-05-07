package com.kakaobank.placesearch.fetcher.impl;


import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.dto.NaverItemDto;
import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;
import com.kakaobank.placesearch.feign.client.NaverSearchFeignClient;
import com.kakaobank.placesearch.fetcher.PlaceFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverPlaceFetcherImpl implements PlaceFetcher {

    private final NaverSearchFeignClient feignClient;

    private final Executor executor;

    @Override
    public CompletableFuture<List<Place>> fetchPlacesFor(String keyword) {


        return CompletableFuture.supplyAsync(() -> {
            NaverPlaceResponseDto naverPlaceResponseDto = feignClient.call(
                    "4RIC_H_X_eYsIeYbZnSo",
                    "okDpCnKRyd",
                    1,
                    5,
                    "random",
                    keyword);

            return naverPlaceResponseDto.getItems().stream()
                    .map(NaverItemDto::toEntity)
                    .collect(Collectors.toList());
        }, executor);
    }
}
