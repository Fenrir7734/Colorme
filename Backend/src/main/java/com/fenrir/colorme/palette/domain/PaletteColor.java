package com.fenrir.colorme.palette.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class PaletteColor {
    private final Long id;
    private final String hex;
}
