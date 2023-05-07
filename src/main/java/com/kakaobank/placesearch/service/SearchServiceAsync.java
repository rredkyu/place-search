package com.kakaobank.placesearch.service;

import com.kakaobank.placesearch.domain.place.Place;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SearchServiceAsync {
    CompletableFuture<List<Place>> fetchPlaces(String keyword);
}
