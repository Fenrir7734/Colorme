package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.common.security.annotation.AllowUser;
import com.fenrir.colorme.palette.application.port.in.DeletePaletteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllowUser
@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/palettes")
@RequiredArgsConstructor
class DeletePaletteController {
    private final DeletePaletteUseCase deletePaletteUseCase;

    @DeleteMapping("/{code}")
    ResponseEntity<Void> deletePalette(@PathVariable("code") String code) {
        deletePaletteUseCase.deletePalette(code);
        return ResponseEntity.noContent().build();
    }
}
