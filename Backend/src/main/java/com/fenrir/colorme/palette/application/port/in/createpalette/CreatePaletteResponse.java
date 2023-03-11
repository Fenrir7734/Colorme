package com.fenrir.colorme.palette.application.port.in.createpalette;

import lombok.Value;

import java.util.List;

@Value
public class CreatePaletteResponse {
    String code;
    List<String> colors;
}
