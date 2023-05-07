package com.kakaobank.placesearch.domain.place;

import com.kakaobank.placesearch.dto.KakaoDocumentDto;
import com.kakaobank.placesearch.dto.KakaoPlaceResponseDto;
import com.kakaobank.placesearch.dto.NaverItemDto;
import com.kakaobank.placesearch.dto.NaverPlaceResponseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Places {

    private final List<Place> places;

    public Places(List<Place> places) {
        this.places = new ArrayList<>(places);
    }

    public static Places newInstance(KakaoPlaceResponseDto kakaoPlaceResponseDto,
                                     NaverPlaceResponseDto naverPlaceResponseDto) {
        List<Place> kakaoPlaces = kakaoPlaceResponseDto.getDocuments().stream()
                .map(KakaoDocumentDto::toEntity)
                .collect(Collectors.toList());


        List<Place> naverPlaces = naverPlaceResponseDto.getItems().stream()
                .map(NaverItemDto::toEntity)
                .collect(Collectors.toList());

        List<Place> list = new ArrayList<>();
        List<Place> kakaoLeftList = new ArrayList<>();
        List<Place> copyNaverPlaces = new ArrayList<>(naverPlaces);


        for (int k = 0; k < kakaoPlaces.size(); k++) {
            boolean insert = false;
            int n = 0;
            for (; n < naverPlaces.size(); n++) {
                if (isBlank(kakaoPlaces.get(k))) {
                    insert = isInsertByPlaceTitle(kakaoPlaces.get(k), naverPlaces.get(n), list);
                } else {
                    insert = isInsertBySamePlace(kakaoPlaces.get(k), naverPlaces.get(n), list);
                }
            }

            if (!insert) {
                copyNaverPlaces.remove(n);
                kakaoLeftList.add(kakaoPlaces.get(k));
            }
        }

        List<Place> mergePlaceList = new ArrayList<>();
        mergePlaceList.addAll(list);
        mergePlaceList.addAll(kakaoLeftList);
        mergePlaceList.addAll(copyNaverPlaces);

        return new Places(mergePlaceList);

    }

    private static boolean isInsertByPlaceTitle(Place kakao, Place naver, List<Place> list) {
        if(kakao.getNormalizedName().equals(naver.getNormalizedName())) {
            list.add(kakao);
            return true;
        }

        return false;
    }

    private static boolean isInsertBySamePlace(Place kakao, Place naver, List<Place> list) {
        if (isSamePlace(kakao, naver)) {
            list.add(kakao);
            return true;
        }

        return false;
    }

    private static boolean isBlank(Place place) {
        return place.getAddress().isBlank() &&
                place.getRoadAddress().isBlank() &&
                place.getTelephone().isBlank();
    }

    private static boolean isSamePlace(Place kakao, Place naver) {
        return kakao.getAddress().equals(naver.getAddress()) ||
                kakao.getTelephone().equals(naver.getTelephone()) ||
                kakao.getRoadAddress().equals(naver.getRoadAddress());
    }

    public List<Place> getPlaces() {
        return Collections.unmodifiableList(places);
    }
}
