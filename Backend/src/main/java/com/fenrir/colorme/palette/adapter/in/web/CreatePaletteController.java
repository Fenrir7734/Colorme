package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.common.security.annotation.AllowUser;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteCommand;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteResponse;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllowUser
@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/palettes")
@RequiredArgsConstructor
class CreatePaletteController {
    private final CreatePaletteUseCase createPaletteUseCase;

    @PostMapping
    ResponseEntity<CreatePaletteResponse> createPalette(@Valid @RequestBody CreatePaletteCommand command) {
        final CreatePaletteResponse response = createPaletteUseCase.createPalette(command);
        final String location = String.format("/api/v1/palettes/%s", response.getCode());
        return ResponseEntity.created(URI.create(location)).body(response);
    }
}
