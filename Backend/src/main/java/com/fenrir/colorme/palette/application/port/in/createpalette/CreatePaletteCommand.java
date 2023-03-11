package com.fenrir.colorme.palette.application.port.in.createpalette;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePaletteCommand {

    @Size(min = 2, max = 10)
    private List<@Pattern(regexp = "[A-Fa-f0-9]{6}") String> colors;
}
