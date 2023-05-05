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
            for (n = 0; n < naverPlaces.size(); n++) {
                if (kakaoPlaces.get(k).getAddress().isBlank() &&
                        kakaoPlaces.get(k).getRoadAddress().isBlank() &&
                        kakaoPlaces.get(k).getTelephone().isBlank()) {
                    if (kakaoPlaces.get(k).equals(naverPlaces.get(n))) {
                        list.add(kakaoPlaces.get(k));
                        insert = true;
                    }
                } else {
                    if (!kakaoPlaces.get(k).getAddress().equals(naverPlaces.get(n).getAddress()) ||
                            !kakaoPlaces.get(k).getRoadAddress().equals(naverPlaces.get(n).getRoadAddress()) ||
                            !kakaoPlaces.get(k).getTelephone().equals(naverPlaces.get(n).getTelephone())) {
                        list.add(kakaoPlaces.get(k));
                        insert = true;
                    }
                }
            }

           if (!insert) {
               copyNaverPlaces.remove(n);
               kakaoLeftList.add(kakaoPlaces.get(k));
           }
        }

        List<Place> placeList = new ArrayList<>();
        placeList.addAll(list);
        placeList.addAll(kakaoLeftList);
        placeList.addAll(copyNaverPlaces);

        return new Places(placeList);

    }

    public List<Place> getPlaces() {
        return Collections.unmodifiableList(places);
    }
}
