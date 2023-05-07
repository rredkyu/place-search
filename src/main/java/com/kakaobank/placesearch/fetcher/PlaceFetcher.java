package com.kakaobank.placesearch.fetcher;

import com.kakaobank.placesearch.domain.place.Place;
import com.kakaobank.placesearch.domain.place.Places;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PlaceFetcher {
    CompletableFuture<List<Place>> fetchPlacesFor(String keyword);
}
