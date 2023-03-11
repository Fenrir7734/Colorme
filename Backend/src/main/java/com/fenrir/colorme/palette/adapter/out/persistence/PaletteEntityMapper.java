package com.fenrir.colorme.palette.adapter.out.persistence;

import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteColor;
import com.fenrir.colorme.palette.domain.PaletteLike;
import org.mapstruct.Mapper;

@Mapper
interface PaletteEntityMapper {
    PaletteEntity toPaletteEntity(Palette palette);
    PaletteColorEntity toPaletteColor(PaletteColor color);
    PaletteLikeEntity toPaletteLike(PaletteLike like);
}
