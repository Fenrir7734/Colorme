package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.palette.application.port.in.DeletePaletteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/palettes")
@RequiredArgsConstructor
class DeletePaletteController {
    private final DeletePaletteUseCase deletePaletteUseCase;

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePalette(@PathVariable("id") Long id) {
        deletePaletteUseCase.deletePalette(id);
        return ResponseEntity.noContent().build();
    }
}
