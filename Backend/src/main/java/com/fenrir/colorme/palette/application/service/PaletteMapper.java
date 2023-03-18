package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteCommand;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteResponse;
import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteResponse;
import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteColor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
interface PaletteMapper {

    Palette toPalette(CreatePaletteCommand command);

    @Mapping(source = ".", target = "hex")
    PaletteColor toPaletteColor(String hex);

    List<String> toColorHexList(List<PaletteColor> colors);
    default String toColorHex(PaletteColor color) {
        return color.getHex();
    }

    @Mapping(target = "colors", expression = "java(palette.getHexList())")
    CreatePaletteResponse toCreatePaletteResponse(Palette palette);

    @Mapping(target = "likes", expression = "java(palette.likesCount())")
    GetPaletteResponse toGetPaletteResponse(Palette palette);
}
