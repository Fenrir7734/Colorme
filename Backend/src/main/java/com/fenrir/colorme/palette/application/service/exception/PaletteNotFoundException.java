package com.fenrir.colorme.palette.application.service.exception;

public class PaletteNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Palette not found for code = %s";

    public PaletteNotFoundException(String paletteCode) {
        super(String.format(MESSAGE, paletteCode));
    }
}
