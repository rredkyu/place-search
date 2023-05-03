package com.kakaobank.placesearch.feigntest;

import com.kakaobank.feign.FeignTest;
import com.kakaobank.placesearch.client.KakaoLocalSearchFeignClient;
import com.kakaobank.placesearch.client.NaverSearchFeignClient;
import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;


@FeignTest
public class SearchFeignTest {


    private static final Logger log = LoggerFactory.getLogger(SearchFeignTest.class);

    @Autowired
    private KakaoLocalSearchFeignClient openKakaoFeign;

    @Autowired
    private NaverSearchFeignClient openNaverFeign;

    @Test
    void 장소조회_카카오() {
        KakaoPlaceResponseDto responseDto = openKakaoFeign.call("KakaoAK 16d5f6a7bef175b8649df70e9249e996", 1, 15, "accuracy", "곱창집");
        assertThat(responseDto).isNotNull();
    }

    @Test
    void 장소조회_네이버() {
        NaverPlaceResponseDto  responseDto = openNaverFeign.call(
                "4RIC_H_X_eYsIeYbZnSo",
                "okDpCnKRyd",
                10,
                1,
                "random",
                "곱창집");

        assertThat(responseDto).isNotNull();
    }

}
