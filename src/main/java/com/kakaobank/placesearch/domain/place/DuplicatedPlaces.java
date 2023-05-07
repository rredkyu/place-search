package com.kakaobank.placesearch.domain.place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DuplicatedPlaces {

    private final List<Place> places;

    public DuplicatedPlaces(List<Place> places) {
        this.places = new ArrayList<>(places);
    }

    public List<Place> getDuplicatedPlaces() {
        return Collections.unmodifiableList(places);
    }
}
