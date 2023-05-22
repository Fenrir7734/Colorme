package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.common.security.annotation.AllowUser;
import com.fenrir.colorme.palette.application.port.in.CreatePaletteLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllowUser
@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/palettes/{paletteCode}/like")
@RequiredArgsConstructor
class LikePaletteController {
    private final CreatePaletteLikeUseCase createPaletteLikeUseCase;

    @PostMapping
    ResponseEntity<Void> likePalette(@PathVariable String paletteCode) {
        createPaletteLikeUseCase.createLike(paletteCode);
        return ResponseEntity.ok().build();
    }
}
