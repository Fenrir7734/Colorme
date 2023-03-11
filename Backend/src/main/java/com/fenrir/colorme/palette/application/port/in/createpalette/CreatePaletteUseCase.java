package com.fenrir.colorme.palette.application.port.in.createpalette;

public interface CreatePaletteUseCase {
    CreatePaletteResponse createPalette(CreatePaletteCommand command);
}
