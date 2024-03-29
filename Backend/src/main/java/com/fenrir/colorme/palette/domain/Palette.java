package com.fenrir.colorme.palette.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Palette {
    private Long id;
    private String code;
    private List<PaletteColor> colors = new ArrayList<>();
    private List<Long> tags = new ArrayList<>();
    private List<PaletteLike> likes = new ArrayList<>();
    private Long ownerId;

    public List<String> getHexList() {
        if (colors == null) {
            return new ArrayList<>();
        }

        return colors.stream()
                .map(PaletteColor::getHex)
                .collect(Collectors.toList());
    }

    public Integer likesCount() {
        if (null == likes) {
            return 0;
        }

        return likes.size();
    }
}
