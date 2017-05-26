package com.welcome.to.sweden.network.response;

public class APIError extends RuntimeException {

    public APIError(Throwable cause) {
        super(cause);
    }

    public APIError(String message) {
        super(message);
    }

}