package com.fenrir.colorme.palette.adapter.out.persistence;

import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteColor;
import com.fenrir.colorme.palette.domain.PaletteLike;
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
    private static final String EXISTING_PALETTE_WITHOUT_LIKES_CODE = "5678";
    private static final String NOT_EXISTING_PALETTE_CODE = "not existing";

    private static final Long EXISTING_PALETTE_WITHOUT_LIKES_ID = 101L;

    private static final Long PALETTE_OWNER_ID = 1L;
    private static final Long ADMIN_ID = 3L;

    private static final Long EXISTING_TAG_1_ID = 100L;
    private static final Long EXISTING_TAB_2_ID = 200L;

    private static final Integer EXISTING_PALETTES_COUNT = 2;
    private static final Integer LIKES_COUNT = 3;

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
        assertThat(result).isPresent();
        assertThat(result.get().getCode().trim()).isEqualTo(EXISTING_PALETTE_CODE);
    }

    @Test
    void shouldReturnEmptyOptionalIfPaletteNotExists() {
        // when
        Optional<Palette> result = palettePersistenceAdapter.getPalette(NOT_EXISTING_PALETTE_CODE);

        // then
        assertThat(result).isNotPresent();
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
    void shouldReturnTrueIfUserPaletteExists() {
        // when
        boolean result = palettePersistenceAdapter.paletteExists(EXISTING_PALETTE_CODE, PALETTE_OWNER_ID);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseIfUserPaletteNotExists() {
        // when
        boolean result = palettePersistenceAdapter.paletteExists(EXISTING_PALETTE_CODE, ADMIN_ID);

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
        assertThat(paletteRepository.findByCode(EXISTING_PALETTE_CODE)).isNotPresent();
        assertThat(paletteTagRepository.findAllByPaletteCode(EXISTING_PALETTE_CODE)).isEmpty();
        assertThat(paletteColorRepository.findAllByPaletteCode(EXISTING_PALETTE_CODE)).isEmpty();
        assertThat(paletteLikeRepository.findAllByPaletteCode(EXISTING_PALETTE_CODE)).isEmpty();
    }

    @Test
    void shouldCreateLike() {
        // given
        final PaletteLike like = new PaletteLike(EXISTING_PALETTE_WITHOUT_LIKES_ID, ADMIN_ID);

        // when
        palettePersistenceAdapter.createLike(like);

        // then
        assertThat(paletteLikeRepository.count()).isEqualTo(LIKES_COUNT + 1);
    }

    @Test
    void shouldDeleteLike() {
        // when
        palettePersistenceAdapter.deleteLike(EXISTING_PALETTE_CODE, ADMIN_ID);

        // then
        assertThat(paletteLikeRepository.count()).isEqualTo(LIKES_COUNT - 1);
    }

    @Test
    void shouldReturnTrueIfPaletteLikeExists() {
        // when
        boolean result = palettePersistenceAdapter.likeExits(EXISTING_PALETTE_CODE, ADMIN_ID);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseIfPaletteLikeNotExists() {
        // when
        boolean result = palettePersistenceAdapter.likeExits(EXISTING_PALETTE_WITHOUT_LIKES_CODE, ADMIN_ID);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnUserLikePalettesCodes() {
        // when
        List<String> result = palettePersistenceAdapter.getLikedPalettesCodes(ADMIN_ID);

        // then
        result = result.stream().map(String::trim).toList();
        assertThat(result).containsExactly(EXISTING_PALETTE_CODE);
    }
}