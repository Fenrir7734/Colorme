package com.fenrir.colorme.tag.adapter.out.persistence;

import com.fenrir.colorme.shared.PersistenceAdapterTest;
import com.fenrir.colorme.tag.domain.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import({ TagPersistenceAdapter.class, TagEntityMapperImpl.class })
@Sql("TagPersistenceAdapterTest.sql")
class TagPersistenceAdapterTest extends PersistenceAdapterTest {
    private static final Long EXISTING_TAG_ID_1 = 100L;
    private static final Long EXISTING_TAG_ID_2 = 101L;
    private static final Long NOT_EXISTING_TAG_ID = 999L;

    @Autowired
    private TagPersistenceAdapter tagPersistenceAdapter;

    @Autowired
    private TagRepository tagRepository;

    @Test
    void getsAllTags() {
        final List<Tag> tags = tagPersistenceAdapter.getAllTags();

        assertThat(tags.size()).isEqualTo(tagRepository.count());
    }

    @Test
    void shouldReturnTrueIfAllTagsExists() {
        // given
        final List<Long> tagIds = List.of(EXISTING_TAG_ID_1, EXISTING_TAG_ID_2);

        // when
        boolean result = tagPersistenceAdapter.existsAllTags(tagIds);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseIfNotAllTagsExists() {
        // given
        final List<Long> tagIds = List.of(EXISTING_TAG_ID_1, NOT_EXISTING_TAG_ID, EXISTING_TAG_ID_2);

        // when
        boolean result = tagPersistenceAdapter.existsAllTags(tagIds);

        // then
        assertThat(result).isFalse();
    }
}