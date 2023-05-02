package com.kakaobank.placesearch.client;

import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoLocalSearchFeignClient", url = "${kakao.api_url.local}")
public interface KakaoLocalSearchFeignClient {

    @GetMapping
    KakaoPlaceResponseDto call(
            @RequestHeader("Authorization") String restAPiKey,
            @RequestParam("keyword") String keyword);

}
