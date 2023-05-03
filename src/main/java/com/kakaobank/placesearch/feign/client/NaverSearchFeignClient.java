package com.kakaobank.placesearch.feign.client;

import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "NaverSearchFeignClient", url = "${naver.local.url}")
public interface NaverSearchFeignClient {

    @GetMapping
    NaverPlaceResponseDto call(
            @RequestHeader("X-Naver-Client-Id") String clientId,
            @RequestHeader("X-Naver-Client-Secret") String secret,
            @RequestParam("display") Integer display,
            @RequestParam("start") Integer start,
            @RequestParam("sort") String sort,
            @RequestParam("query") String query
    );

}
