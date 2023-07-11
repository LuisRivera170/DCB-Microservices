package com.lara.orderservice.exception;

import com.lara.orderservice.remote.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleOrderServiceException(OrderServiceCustomException exception) {
        return new ResponseEntity<>(ErrorResponse.builder().errorMessage(exception.getMessage()).errorCode(exception.getErrorCode()).build(), HttpStatus.valueOf(exception.getStatus()));
    }

}
