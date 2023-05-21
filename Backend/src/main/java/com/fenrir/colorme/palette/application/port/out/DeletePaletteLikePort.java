package com.fenrir.colorme.palette.application.port.out;

public interface DeletePaletteLikePort {
    void deleteLike(String paletteCode, Long userId);
}
