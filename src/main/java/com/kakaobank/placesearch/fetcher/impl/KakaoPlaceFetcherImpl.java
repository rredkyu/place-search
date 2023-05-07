package com.kakaobank.placesearch.fetcher.impl;

import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.dto.KakaoDocumentDto;
import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import com.kakaobank.placesearch.feign.client.KakaoLocalSearchFeignClient;
import com.kakaobank.placesearch.fetcher.PlaceFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class KakaoPlaceFetcherImpl implements PlaceFetcher {

    private final KakaoLocalSearchFeignClient feignClient;

    private final Executor executor;

    @Override
    public CompletableFuture<List<Place>> fetchPlacesFor(String keyword) {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(5); // set the core pool size
//        executor.setMaxPoolSize(10); // max pool size
//        executor.setThreadNamePrefix("place-fetcher-async-"); // give an optional name to your threads
//        executor.initialize();

        return CompletableFuture.supplyAsync(() -> {
            KakaoPlaceResponseDto kakaoPlaceResponseDto =  feignClient.call(
                    "KakaoAK 16d5f6a7bef175b8649df70e9249e996",
                    1,
                    5,
                    "accuracy",
                    keyword);

            return kakaoPlaceResponseDto.getDocuments().stream()
                    .map(KakaoDocumentDto::toEntity)
                    .collect(Collectors.toList());
        }, executor);
    }
}
