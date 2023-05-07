package com.kakaobank.placesearch.domain.place;

import com.kakaobank.placesearch.specification.PlaceSearchPlatform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class Place {

    private PlaceSearchPlatform provider;
    private String name;

    private String normalizedName;
    private String telephone;
    private String address;
    private String roadAddress;

    public static String normalized(String name) {
        return name
                .replaceAll("[\\s+]", "")
                .replaceAll("[<b|(/b)>]", "");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return telephone.equals(place.telephone) && address.equals(place.address) && roadAddress.equals(place.roadAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone, address, roadAddress);
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                '}';
    }
}
