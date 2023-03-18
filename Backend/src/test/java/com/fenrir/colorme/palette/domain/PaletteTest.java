package com.fenrir.colorme.palette.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaletteTest {

    @Test
    void returnsListOfHexColorCodes() {
        // given
        Palette palette = defaultPalette();

        // when
        List<String> colors = palette.getHexList();

        // then
        assertThat(colors).containsExactlyInAnyOrderElementsOf(List.of("123456", "654321"));
    }

    @Test
    void returnsEmptyListOfHexColorCodes() {
        // given
        Palette palette = defaultPalette();
        palette.setColors(null);

        // when
        List<String> colors = palette.getHexList();

        // then
        assertThat(colors).isEmpty();
    }

    @Test
    void returnsLikesCount() {
        // given
        Palette palette = defaultPalette();

        // when
        Integer count = palette.likesCount();

        // then
        assertThat(count).isEqualTo(2);
    }

    @Test
    void returnsLikesCountEqualToZero() {
        // given
        Palette palette = defaultPalette();
        palette.setLikes(null);

        // when
        Integer count = palette.likesCount();

        // then
        assertThat(count).isEqualTo(0);
    }

    private Palette defaultPalette() {
        Palette palette = new Palette();
        palette.setId(1L);
        palette.setCode("code");
        palette.setColors(List.of(new PaletteColor(1L, "123456"), new PaletteColor(2L, "654321")));
        palette.setTags(List.of(1L, 2L, 3L));
        palette.setLikes(List.of(new PaletteLike(1L), new PaletteLike(2L)));
        return palette;
    }
}