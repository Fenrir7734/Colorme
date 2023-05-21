package com.fenrir.colorme.palette.application.service.exception;

public class NotPaletteOwnerException extends RuntimeException {
    private static final String MESSAGE = "You do not have required permissions to remove palette = %s";

    public NotPaletteOwnerException(String paletteCode) {
        super(String.format(MESSAGE, paletteCode));
    }
}
