package com.yoti.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Illegal Coordinates provided!")
public class IllegalCoordinatesException extends RuntimeException {
    public IllegalCoordinatesException(String s) {
        super(s);
    }
}
