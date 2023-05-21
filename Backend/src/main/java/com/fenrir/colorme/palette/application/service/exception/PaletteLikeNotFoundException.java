package com.fenrir.colorme.palette.application.service.exception;

public class PaletteLikeNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Palette like not found for palette = %s";

    public PaletteLikeNotFoundException(String paletteCode) {
        super(String.format(MESSAGE, paletteCode));
    }
}
