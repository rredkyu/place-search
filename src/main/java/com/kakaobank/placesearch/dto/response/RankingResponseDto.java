package com.kakaobank.placesearch.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;


@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RankingResponseDto {
    private String placeName;
    private int count;

    public static RankingResponseDto of(ZSetOperations.TypedTuple<String> typedTuple) {
        return RankingResponseDto.builder()
                .placeName(typedTuple.getValue())
                .count(typedTuple.getScore().intValue())
                .build();
    }
}
