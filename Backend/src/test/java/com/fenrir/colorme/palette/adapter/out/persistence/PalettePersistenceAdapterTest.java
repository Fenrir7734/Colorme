package com.fenrir.colorme.palette.adapter.out.persistence;

import com.fenrir.colorme.shared.PersistenceAdapterTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Import({ PalettePersistenceAdapter.class, PaletteEntityMapperImpl.class })
@Sql("PalettePersistenceAdapterTest.sql")
class PalettePersistenceAdapterTest extends PersistenceAdapterTest {
    private static final String EXISTING_PALETTE_CODE = "1234";
    private static final String NOT_EXISTING_PALETTE_CODE = "not existing";

    @Autowired
    private PalettePersistenceAdapter palettePersistenceAdapter;

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
}