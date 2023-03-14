package com.fenrir.colorme.palette.application.service.exception;

public class PaletteNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Palette not found for id = %s";

    public PaletteNotFoundException(Long paletteId) {
        super(String.format(MESSAGE, paletteId));
    }
}
