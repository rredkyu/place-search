package com.kakaobank.placesearch.feigntest;

import com.kakaobank.feign.FeignTest;
import com.kakaobank.placesearch.client.KakaoLocalSearchFeignClient;
import com.kakaobank.placesearch.client.NaverSearchFeignClient;
import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import static org.assertj.core.api.Assertions.*;


@FeignTest
public class SearchFeignTest {

    private static final Logger log = LoggerFactory.getLogger(SearchFeignTest.class);

    @Autowired
    private KakaoLocalSearchFeignClient openKakaoFeign;

    @Autowired
    private NaverSearchFeignClient openNaverFeign;

    @ParameterizedTest
    @CsvSource(value = {"1:5", "1:10", "1:15"}, delimiter = ':')
    void 장소조회_카카오(Integer page, Integer size) {
        KakaoPlaceResponseDto responseDto = openKakaoFeign.call("KakaoAK 16d5f6a7bef175b8649df70e9249e996", page, size, "accuracy", "곱창집");
        assertThat(responseDto.getDocuments().size()).isEqualTo(size);
        assertThat(responseDto).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:1", "1:3", "1:5"}, delimiter = ':')
    void 장소조회_네이버(Integer start, Integer display) {
        NaverPlaceResponseDto  responseDto = openNaverFeign.call(
                "4RIC_H_X_eYsIeYbZnSo",
                "okDpCnKRyd",
                display,
                start,
                "random",
                "곱창집");

        assertThat(responseDto.getItems().size()).isEqualTo(display);
        assertThat(responseDto).isNotNull();
    }

}
