package com.finartz.investtrack.model.response;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String message;

    private String error;

    private String code;

    public ErrorResponse(HttpStatus code, String error, String message) {
        this.message = message;
        this.code = code.toString();
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
