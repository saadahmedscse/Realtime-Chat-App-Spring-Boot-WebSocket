package com.saadahmedsoft.sparkconvo.exceptionHandler;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletResponse res) {
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse handleNotFound(NoHandlerFoundException ex, HttpServletResponse res) {
        res.setStatus(HttpStatus.NOT_FOUND.value());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
    }

    private record ErrorResponse(int status, String message) {}
}
