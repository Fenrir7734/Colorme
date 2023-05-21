package com.fenrir.colorme.palette.application.port.out;

public interface PaletteLikeExistsPort {
    boolean likeExits(String paletteCode, Long userId);
}
