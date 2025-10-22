package com.nazire.booktrack_pro.exception;

import jakarta.validation.constraints.NotNull;

public class NotFoundException extends RuntimeException{
    public NotFoundException(@NotNull String message) {
        super(message);
    }
}
