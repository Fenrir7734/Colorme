package com.fenrir.colorme.palette.application.port.out;

import com.fenrir.colorme.palette.domain.Palette;

public interface CreatePalettePort {
    void createPalette(Palette palette);
}
