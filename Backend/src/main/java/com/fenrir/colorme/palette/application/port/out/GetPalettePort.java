package com.fenrir.colorme.palette.application.port.out;

import com.fenrir.colorme.palette.domain.Palette;

import java.util.Optional;

public interface GetPalettePort {
    Optional<Palette> getPalette(String code);
}
