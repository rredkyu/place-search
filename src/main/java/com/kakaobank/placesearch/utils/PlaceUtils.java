package com.kakaobank.placesearch.utils;

import com.kakaobank.placesearch.domain.place.Place;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PlaceUtils {
    private static final String INSTANCE_ERROR_MESSAGE = "PlaceUtils 클래스를 인스턴스화 할수 없습니다";

    private PlaceUtils() {
        throw new AssertionError(INSTANCE_ERROR_MESSAGE);
    }

    public static List<Place> merge(List<Place> list1, List<Place> list2) {
        List<Place> duplicatedList = findDuplicatedElementFrom(
                Stream.concat(list2.stream(), list1.stream())
                        .collect(toList()));

        List<Place> leftList1 = removeDuplicatedElementFrom(list1, duplicatedList);
        List<Place> leftList2 = removeDuplicatedElementFrom(list2, duplicatedList);


        return Stream.concat(duplicatedList.stream(), Stream.concat(leftList1.stream(), leftList2.stream()))
                .collect(toList());
    }

    private static List<Place> findDuplicatedElementFrom(List<Place> list) {
        Set<Place> items = new HashSet<>();
        return list.stream()
                .filter(place -> !items.add(place))
                .collect(toList());
    }

    private static List<Place> removeDuplicatedElementFrom(List<Place> list, List<Place> duplicatedList) {
        Set<Place> duplicatedSet = new HashSet<>(duplicatedList);

        return list.stream()
                .filter(duplicatedSet::add)
                .collect(toList());
    }



}
