package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.service.exception.TagsNotFoundException;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteCommand;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteResponse;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteUseCase;
import com.fenrir.colorme.palette.application.port.out.CreatePalettePort;
import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.tag.application.port.in.ExistsAllTagsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreatePaletteService implements CreatePaletteUseCase {
    private final CreatePalettePort createPalettePort;
    private final ExistsAllTagsQuery existsAllTagsQuery;
    private final PaletteMapper paletteMapper;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public CreatePaletteResponse createPalette(CreatePaletteCommand command) {
        final Palette palette = paletteMapper.toPalette(command);
        palette.setOwnerId(authenticationFacade.getUserId());
        validate(palette);

        createPalettePort.createPalette(palette);
        return paletteMapper.toCreatePaletteResponse(palette);
    }

    private void validate(Palette palette) {
        if (!existsAllTagsQuery.existsAllTags(palette.getTags())) {
            throw new TagsNotFoundException();
        }
    }
}
