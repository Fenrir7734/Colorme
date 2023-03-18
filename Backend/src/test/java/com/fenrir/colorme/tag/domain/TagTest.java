package com.fenrir.colorme.tag.domain;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class TagTest {

    @Test
    void shouldCompareByTagCategoryName() {
        // given
        final TagCategory category1 = new TagCategory(1L, 1, "category B");
        final TagCategory category2 = new TagCategory(2L, 2, "category A");

        final Tag tag1 = new Tag(1L, 1, "tag", category1);
        final Tag tag2 = new Tag(1L, 1, "tag", category2);

        final Comparator<Tag> comparator = Tag.tagComparator();

        // when
        int result = comparator.compare(tag1, tag2);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldCompareByTagName() {
        // given
        final TagCategory category1 = new TagCategory(1L, 1, "category");
        final TagCategory category2 = new TagCategory(2L, 2, "category");

        final Tag tag1 = new Tag(1L, 1, "tag A", category1);
        final Tag tag2 = new Tag(1L, 1, "tag B", category2);

        final Comparator<Tag> comparator = Tag.tagComparator();

        // when
        int result = comparator.compare(tag1, tag2);

        // then
        assertThat(result).isEqualTo(-1);
    }
}