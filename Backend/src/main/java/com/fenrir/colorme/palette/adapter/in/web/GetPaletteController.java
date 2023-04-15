package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteResponse;
import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/palettes")
@RequiredArgsConstructor
public class GetPaletteController {
    private final GetPaletteUseCase getPaletteUseCase;

    @GetMapping("/{code}")
    ResponseEntity<GetPaletteResponse> getPalette(@PathVariable("code") String code) {
        return ResponseEntity.ok(getPaletteUseCase.getPalette(code));
    }
}
