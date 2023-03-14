package com.fenrir.colorme.palette.adapter.out.persistence;

import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteColor;
import com.fenrir.colorme.palette.domain.PaletteLike;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
interface PaletteEntityMapper {
    PaletteEntity toPaletteEntity(Palette palette);
    PaletteColorEntity toPaletteColorEntity(PaletteColor color);
    PaletteLikeEntity toPaletteLikeEntity(PaletteLike like);
    List<PaletteTagEntity> toPaletteTagsListEntity(List<Long> tags);
    @Mapping(source = ".", target = "id.tagId")
    PaletteTagEntity toPaletteTagEntity(Long tag);

    Palette toPalette(PaletteEntity palette);
    List<PaletteColor> toPaletteColorList(List<PaletteColorEntity> colors);
    PaletteColor toPaletteColor(PaletteColorEntity color);
    List<PaletteLike> toPaletteLikesList(List<PaletteLikeEntity> likes);
    PaletteLike toPaletteLike(PaletteLikeEntity like);
    List<Long> toTagsList(List<PaletteTagEntity> tags);
    default Long toTag(PaletteTagEntity tag) {
        return tag.getId().getTagId();
    }
}
