package com.merchant.offer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OffersExistException extends RuntimeException {
    public OffersExistException(String message) {
        super(message);
    }
}
