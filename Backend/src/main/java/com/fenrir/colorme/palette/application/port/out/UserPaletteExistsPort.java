package com.fenrir.colorme.palette.application.port.out;

public interface UserPaletteExistsPort {
    boolean paletteExists(String paletteCode, Long userId);
}
