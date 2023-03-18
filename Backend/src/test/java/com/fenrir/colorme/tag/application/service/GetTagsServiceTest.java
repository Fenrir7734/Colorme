package com.fenrir.colorme.tag.application.service;

import com.fenrir.colorme.tag.application.port.in.gettags.response.CategoryResponse;
import com.fenrir.colorme.tag.application.port.in.gettags.response.TagResponse;
import com.fenrir.colorme.tag.application.port.out.GetTagsPort;
import com.fenrir.colorme.tag.domain.Tag;
import com.fenrir.colorme.tag.domain.TagCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class GetTagsServiceTest {
    private final GetTagsPort getTagsPort = Mockito.mock(GetTagsPort.class);
    private final TagMapper tagMapper = Mockito.mock(TagMapper.class);

    private final GetTagsService getTagsService = new GetTagsService(getTagsPort, tagMapper);

    @Test
    void getsAllTags() {
        // given
        final List<Tag> tags = givenTagsList();
        final List<TagResponse> tagResponseList = toTagResponseList(tags);

        givenGetAllTagsWillSuccess(tags);
        givenMappingToTagResponseWillSuccess(tags, tagResponseList);

        // when
        final List<TagResponse> actualTagResponseList = getTagsService.getTags();

        // then
        assertThat(actualTagResponseList).containsExactlyElementsOf(tagResponseList);
    }

    private void givenGetAllTagsWillSuccess(List<Tag> tags) {
        given(getTagsPort.getAllTags()).willReturn(tags);
    }

    private void givenMappingToTagResponseWillSuccess(List<Tag> tags, List<TagResponse> tagResponseList) {
        given(tagMapper.toTagResponseList(tags)).willReturn(tagResponseList);
    }

    private List<TagResponse> toTagResponseList(List<Tag> tags) {
        if (tags.isEmpty()) {
            return new ArrayList<>();
        }

        TagCategory category = tags.get(0).getCategory();
        CategoryResponse categoryResponse = new CategoryResponse(category.getCode(), category.getName());

        List<TagResponse> tagResponseList = new ArrayList<>();
        for (Tag tag: tags) {
            TagResponse response = new TagResponse(tag.getCode(), tag.getName(), categoryResponse);
            tagResponseList.add(response);
        }

        return tagResponseList;
    }

    private List<Tag> givenTagsList() {
        TagCategory category = new TagCategory(1L, 1, "Tag category");

        return List.of(
                new Tag(1L, 1, "tag 1", category),
                new Tag(2L, 2, "tag 2", category)
        );
    }
}