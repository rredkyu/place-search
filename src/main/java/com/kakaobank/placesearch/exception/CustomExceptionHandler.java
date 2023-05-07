package com.kakaobank.placesearch.exception;

import com.kakaobank.placesearch.dto.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {



    @ExceptionHandler(PlaceSearchException.class)
    protected CommonResponse<Void> handlePlaceSearchException(PlaceSearchException e, HttpServletRequest request) {
        log.warn("[PlaceSearchException 발생] request url = {}", request.getRequestURI(), e);

        return new CommonResponse<>(e.getResponseCode().getCode(), e.getCustomResponseMessage());
    }

    @ExceptionHandler(BindException.class)
    protected CommonResponse<Void> handleBindException(BindException e, HttpServletRequest request) {
        log.warn("[BindException] 발생 request url = {}", request.getRequestURI(), e);

        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        StringBuilder stringBuilder = getResponseStringBuilder(allErrors);

        return new CommonResponse<>(ResponseCode.INVALID_INPUT.getCode(), stringBuilder.toString());
    }


    private static StringBuilder getResponseStringBuilder(Iterable<ObjectError> allErrors) {
        StringBuilder stringBuilder = new StringBuilder();
        allErrors.forEach(error -> stringBuilder
                .append("['")
                .append(((FieldError) error).getField())
                .append("' is '")
                .append(((FieldError) error).getRejectedValue())
                .append("' :: ")
                .append(error.getDefaultMessage())
                .append("] ")
        );
        return stringBuilder;
    }
}
