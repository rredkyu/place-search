package com.kakaobank.placesearch.service.impl;

import com.kakaobank.placesearch.dto.response.RankingResponseDto;
import com.kakaobank.placesearch.exception.PlaceSearchException;
import com.kakaobank.placesearch.exception.ResponseCode;
import com.kakaobank.placesearch.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String KEY = "ranking";

    @Override
    public void addKeyword(String keyword) {
        redisTemplate.opsForZSet().incrementScore(KEY, keyword, 1);
    }

    @Override
    public List<RankingResponseDto> getRankingList() {
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();

        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringStringZSetOperations
                .reverseRangeWithScores(KEY, 0, 9);

        if (Objects.isNull(typedTuples)) {
            throw new PlaceSearchException(ResponseCode.NOT_FOUND_RANKING);
        }

        return createRankingResponseDtoList(typedTuples);
    }

    public List<RankingResponseDto> createRankingResponseDtoList(Set<ZSetOperations.TypedTuple<String>> typedTuples) {

        return typedTuples.stream().map(RankingResponseDto::of).collect(Collectors.toList());
    }
}
