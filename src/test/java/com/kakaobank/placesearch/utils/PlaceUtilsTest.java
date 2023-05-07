package com.kakaobank.placesearch.utils;

import com.kakaobank.placesearch.domain.place.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PlaceUtilsTest {

    @Test
    void 병합_중복_미존재_테스트() {
        List<Place> test1 = new ArrayList<>();
        test1.add(Place.builder()
                .name("hongA")
                .normalizedName("hongA")
                .roadAddress("hongA 507")
                .address("hongA")
                .telephone("010-9800-hongA")
                .build());

        test1.add(Place.builder()
                .name("hongB")
                .normalizedName("hongB")
                .roadAddress("hongB 507")
                .address("hongB")
                .telephone("010-9800-hongB")
                .build());

        test1.add(Place.builder()
                .name("hongC")
                .normalizedName("hongC")
                .roadAddress("hongC 507")
                .address("hongC")
                .telephone("010-9800-hongC")
                .build());

        test1.add(Place.builder()
                .name("hongD")
                .normalizedName("hongD")
                .roadAddress("hongD 507")
                .address("hongD")
                .telephone("010-9800-hongD")
                .build());




        List<Place> test2 = new ArrayList<>();


        test2.add(Place.builder()
                .name("hongE")
                .normalizedName("hongE")
                .roadAddress("hongE 507")
                .address("hongE")
                .telephone("010-9800-hongE")
                .build());


        List<Place> mergeList = PlaceUtils.merge(test1, test2);
        System.out.println(mergeList);
        assertThat(mergeList.size()).isEqualTo(5);
        assertThat(mergeList.get(0).getAddress()).isEqualTo("hongA");
        assertThat(mergeList.get(1).getAddress()).isEqualTo("hongB");
        assertThat(mergeList.get(2).getAddress()).isEqualTo("hongC");
        assertThat(mergeList.get(3).getAddress()).isEqualTo("hongD");
        assertThat(mergeList.get(4).getAddress()).isEqualTo("hongE");

    }

    @Test
    @DisplayName("중복 존재시")
    void 병합_테스트() {
        List<Place> test1 = new ArrayList<>();
        test1.add(Place.builder()
                .name("hongA")
                .normalizedName("hongA")
                .roadAddress("hongA 507")
                .address("hongA")
                .telephone("010-9800-hongA")
                .build());

        test1.add(Place.builder()
                .name("hongB")
                .normalizedName("hongB")
                .roadAddress("hongB 507")
                .address("hongB")
                .telephone("010-9800-hongB")
                .build());

        test1.add(Place.builder()
                .name("hongC")
                .normalizedName("hongC")
                .roadAddress("hongC 507")
                .address("hongC")
                .telephone("010-9800-hongC")
                .build());

        test1.add(Place.builder()
                .name("hongD")
                .normalizedName("hongD")
                .roadAddress("hongD 507")
                .address("hongD")
                .telephone("010-9800-hongD")
                .build());




        List<Place> test2 = new ArrayList<>();

        test2.add(Place.builder()
                .name("hongA")
                .normalizedName("hongA")
                .roadAddress("hongA 507")
                .address("hongA")
                .telephone("010-9800-hongA")
                .build());

        test2.add(Place.builder()
                .name("hongE")
                .normalizedName("hongE")
                .roadAddress("hongE 507")
                .address("hongE")
                .telephone("010-9800-hongE")
                .build());

        test2.add(Place.builder()
                .name("hongD")
                .normalizedName("hongD")
                .roadAddress("hongD 507")
                .address("hongD")
                .telephone("010-9800-hongD")
                .build());

        test2.add(Place.builder()
                .name("hongC")
                .normalizedName("hongC")
                .roadAddress("hongC 507")
                .address("hongC")
                .telephone("010-9800-hongC")
                .build());


        List<Place> mergeList = PlaceUtils.merge(test1, test2);
        System.out.println(mergeList);
        assertThat(mergeList.size()).isEqualTo(5);
        assertThat(mergeList.get(0).getAddress()).isEqualTo("hongA");
        assertThat(mergeList.get(1).getAddress()).isEqualTo("hongC");
        assertThat(mergeList.get(2).getAddress()).isEqualTo("hongD");
        assertThat(mergeList.get(3).getAddress()).isEqualTo("hongB");
        assertThat(mergeList.get(4).getAddress()).isEqualTo("hongE");

    }


}
