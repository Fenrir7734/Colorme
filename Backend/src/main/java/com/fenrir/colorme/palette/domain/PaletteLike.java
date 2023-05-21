package com.fenrir.colorme.palette.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PaletteLike {
    private final Long paletteId;
    private final Long userId;
}
