package com.kakaobank.placesearch.controller;

import com.kakaobank.placesearch.service.SearchService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @Mock
    SearchService searchService;

    @InjectMocks
    SearchController searchController;

    MockMvc mockMvc;


}