package com.fenrir.colorme.palette.adapter.out.persistence;

import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteColor;
import com.fenrir.colorme.shared.PersistenceAdapterTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Import({ PalettePersistenceAdapter.class, PaletteEntityMapperImpl.class })
@Sql("PalettePersistenceAdapterTest.sql")
class PalettePersistenceAdapterTest extends PersistenceAdapterTest {
    private static final String EXISTING_PALETTE_CODE = "1234";
    private static final String NOT_EXISTING_PALETTE_CODE = "not existing";

    private static final Long EXISTING_TAG_1_ID = 100L;
    private static final Long EXISTING_TAB_2_ID = 200L;

    private static final Integer EXISTING_PALETTES_COUNT = 1;

    @Autowired
    private PalettePersistenceAdapter palettePersistenceAdapter;

    @Autowired
    private PaletteRepository paletteRepository;

    @Autowired
    private PaletteTagRepository paletteTagRepository;

    @Autowired
    private PaletteColorRepository paletteColorRepository;

    @Autowired
    private PaletteLikeRepository paletteLikeRepository;

    @Test
    void shouldReturnPaletteIfExists() {
        // when
        Optional<Palette> result = palettePersistenceAdapter.getPalette(EXISTING_PALETTE_CODE);

        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getCode().trim()).isEqualTo(EXISTING_PALETTE_CODE);
    }

    @Test
    void shouldReturnEmptyOptionalIfPaletteNotExists() {
        // when
        Optional<Palette> result = palettePersistenceAdapter.getPalette(NOT_EXISTING_PALETTE_CODE);

        // then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void shouldReturnTrueIfPaletteExists() {
        // when
        boolean result = palettePersistenceAdapter.paletteExists(EXISTING_PALETTE_CODE);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseIfPaletteNotExists() {
        // when
        boolean result = palettePersistenceAdapter.paletteExists(NOT_EXISTING_PALETTE_CODE);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldSaveNewPalette() {
        // give
        final Palette palette = getDefaultPalette();

        // when
        palettePersistenceAdapter.createPalette(palette);

        // then
        assertThat(palette.getId()).isNotNull();
        assertThat(palette.getCode()).isNotBlank();

        assertThat(paletteRepository.count()).isEqualTo(EXISTING_PALETTES_COUNT + 1);

        PaletteEntity newPalette = paletteRepository.findByCode(palette.getCode()).get();
        assertThat(newPalette.getColors()).isNotEmpty();
        assertThat(newPalette.getTags()).isNotEmpty();
        assertThat(newPalette.getLikes()).isEmpty();
        assertThat(newPalette.getCreatedAt()).isNotNull();
    }

    private Palette getDefaultPalette() {
        final PaletteColor white = new PaletteColor(null, "FFFFFF");
        final PaletteColor black = new PaletteColor(null, "000000");

        final Palette palette = new Palette();
        palette.setColors(List.of(white, black));
        palette.setTags(List.of(EXISTING_TAG_1_ID, EXISTING_TAB_2_ID));

        return palette;
    }

    @Test
    void shouldDeletePalette() {
        // when
        palettePersistenceAdapter.deletePalette(EXISTING_PALETTE_CODE);

        // then
        assertThat(paletteRepository.count()).isEqualTo(EXISTING_PALETTES_COUNT - 1);
        assertThat(paletteRepository.findByCode(EXISTING_PALETTE_CODE).isPresent()).isFalse();
        assertThat(paletteTagRepository.findAllByPaletteCode(EXISTING_PALETTE_CODE)).isEmpty();
        assertThat(paletteColorRepository.findAllByPaletteCode(EXISTING_PALETTE_CODE)).isEmpty();
        assertThat(paletteLikeRepository.findAllByPaletteCode(EXISTING_PALETTE_CODE)).isEmpty();
    }
}