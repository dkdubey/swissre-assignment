package com.swissre.exception;

public class PortfolioException extends RuntimeException {

    public PortfolioException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortfolioException(String message) {
        super(message);
    }
}
