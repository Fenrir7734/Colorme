package com.fenrir.colorme.palette.application.port.out;

import java.util.List;

public interface GetUserLikedPalettesCodesPort {
    List<String> getLikedPalettesCodes(Long userId);
}
