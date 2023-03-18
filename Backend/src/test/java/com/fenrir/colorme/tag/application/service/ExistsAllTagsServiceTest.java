package com.fenrir.colorme.tag.application.service;

import com.fenrir.colorme.tag.application.port.out.ExistsAllTagsPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class ExistsAllTagsServiceTest {
    private final ExistsAllTagsPort existsAllTagsPort = Mockito.mock(ExistsAllTagsPort.class);

    private final ExistsAllTagsService existsAllTagsService = new ExistsAllTagsService(existsAllTagsPort);

    @Test
    void returnsTrueWhenAllTagsExists() {
        // given
        final List<Long> tagIds = List.of(1L, 2L);

        givenAllTagsExists(tagIds);

        // when
        final boolean allTagsExists = existsAllTagsService.existsAllTags(tagIds);

        // then
        assertThat(allTagsExists).isTrue();
    }

    @Test
    void returnsTrueForEmptyTagsList() {
        // given
        final List<Long> tagIds = new ArrayList<>();

        givenAllTagsExists(tagIds);

        // when
        final boolean allTagsExists = existsAllTagsService.existsAllTags(tagIds);

        // then
        assertThat(allTagsExists).isTrue();
    }

    @Test
    void returnsFalseWhenNotAllTagsExists() {
        // given
        final List<Long> tagIds = List.of(1L, 2L);

        givenNotAllTagsExists(tagIds);

        // when
        final boolean allTagsExists = existsAllTagsService.existsAllTags(tagIds);

        // then
        assertThat(allTagsExists).isFalse();
    }

    private void givenAllTagsExists(List<Long> tagsIds) {
        given(existsAllTagsPort.existsAllTags(tagsIds)).willReturn(true);
    }

    private void givenNotAllTagsExists(List<Long> tagsIds) {
        given(existsAllTagsPort.existsAllTags(tagsIds)).willReturn(false);
    }
}