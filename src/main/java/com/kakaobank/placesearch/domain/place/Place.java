package com.kakaobank.placesearch.domain.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class Place {

    private String name;
    private String telephone;
    private String address;
    private String roadAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(name, place.name) && Objects.equals(telephone, place.telephone) && Objects.equals(address, place.address) && Objects.equals(roadAddress, place.roadAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, telephone, address, roadAddress);
    }
}
