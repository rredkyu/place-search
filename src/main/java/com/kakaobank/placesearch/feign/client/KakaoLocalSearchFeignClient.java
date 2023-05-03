package com.kakaobank.placesearch.feign.client;

import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "KakaoLocalSearchFeignClient", url = "${kakao.local.url}")
public interface KakaoLocalSearchFeignClient {

    @GetMapping
    KakaoPlaceResponseDto call(
            @RequestHeader("Authorization") String restAPIKey,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sort") String sort,
            @RequestParam("query") String query
    );

}
