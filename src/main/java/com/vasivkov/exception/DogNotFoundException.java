package com.vasivkov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DogNotFoundException extends RuntimeException {
    private Long id;
    public DogNotFoundException(int id){
        super(String.format("Dog is not found with id : '%s'", id));
    }
}

