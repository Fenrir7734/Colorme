package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.common.security.annotation.AllowUser;
import com.fenrir.colorme.palette.application.port.in.DeletePaletteLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllowUser
@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/palettes/{paletteCode}/unlike")
@RequiredArgsConstructor
class UnlikePaletteController {
    private final DeletePaletteLikeUseCase deletePaletteLikeUseCase;

    @DeleteMapping
    ResponseEntity<Void> unlikePalette(@PathVariable String paletteCode) {
        deletePaletteLikeUseCase.deleteLike(paletteCode);
        return ResponseEntity.noContent().build();
    }
}
