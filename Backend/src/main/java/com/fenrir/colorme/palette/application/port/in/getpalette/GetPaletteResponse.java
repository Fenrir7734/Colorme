package com.fenrir.colorme.palette.application.port.in.getpalette;

import lombok.Value;

import java.util.List;

@Value
public class GetPaletteResponse {
    String code;
    List<String> colors;
    List<Long> tags;
    Integer likes;
}
