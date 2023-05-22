package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.common.security.annotation.AllowUser;
import com.fenrir.colorme.palette.application.port.in.GetUserLikedPalettesCodesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllowUser
@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/palettes/liked/codes")
@RequiredArgsConstructor
class UserLikedPalettesCodesController {
    private final GetUserLikedPalettesCodesUseCase getUserLikedPalettesCodesUseCase;

    @GetMapping
    ResponseEntity<List<String>> likedPalettesCodes() {
        return ResponseEntity.ok(getUserLikedPalettesCodesUseCase.getUserLikedPalettesCodes());
    }
}
