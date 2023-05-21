package com.fenrir.colorme.palette.application.service.exception;

public class PaletteLikeAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "Palette like already exists for palette = %s";

    public PaletteLikeAlreadyExistsException(String paletteCode) {
        super(String.format(MESSAGE, paletteCode));
    }
}
