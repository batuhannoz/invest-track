package com.finartz.investtrack.exception;


import com.finartz.investtrack.controller.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { BadCredentialsException.class })
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.toString(),
                        e.getMessage(),
                        "The username or password is incorrect"
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = { AccountStatusException.class })
    public ResponseEntity<ErrorResponse> handleAccountStatusException(AccountStatusException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.toString(),
                        e.getMessage(),
                        "The account is locked"
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.UNAUTHORIZED.toString(),
                        e.getMessage(),
                        "You are not authorized to access this resource"
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(value = { SignatureException.class })
    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.UNAUTHORIZED.toString(),
                        e.getMessage(),
                        "The JWT signature is invalid"
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(value = { ExpiredJwtException.class })
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.UNAUTHORIZED.toString(),
                        e.getMessage(),
                        "The JWT has expired"
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(value = { InsufficientFundsException.class })
    public ResponseEntity<ErrorResponse> handleInsufficientFundsException(InsufficientFundsException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.toString(),
                        e.getMessage(),
                        "The user's balance is not sufficient for this transaction"
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        e.getMessage(),
                        "Internal server error"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
