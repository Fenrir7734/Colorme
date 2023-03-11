package com.fenrir.colorme.palette.application.mapper;

import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteCommand;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteResponse;
import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteColor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaletteMapper {

    Palette toPalette(CreatePaletteCommand command);

    @Mapping(source = ".", target = "hex")
    PaletteColor toPaletteColor(String hex);

    @Mapping(target = "colors", expression = "java(palette.getHexList())")
    CreatePaletteResponse toCreatePaletteResponse(Palette palette);
}
