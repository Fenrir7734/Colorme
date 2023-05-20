package com.fenrir.colorme.tag.adapter.in;

import com.fenrir.colorme.shared.WebAdapterTest;
import com.fenrir.colorme.tag.application.port.in.gettags.GetTagsQuery;
import com.fenrir.colorme.tag.application.port.in.gettags.response.CategoryResponse;
import com.fenrir.colorme.tag.application.port.in.gettags.response.TagResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GetTagsController.class)
class GetTagsControllerTest extends WebAdapterTest {
    private static final String GET_TAGS_CONTROLLER_ENDPOINT = "/api/v1/tags";

    @MockBean
    private GetTagsQuery getTagsQuery;

    @Test
    void testGetTags() throws Exception {
        // given
        final List<TagResponse> tagResponseList = givenTagsList();

        givenGetTagsQueryWillSuccess(tagResponseList);

        // when
        ResultActions response = mockMvc.perform(get(GET_TAGS_CONTROLLER_ENDPOINT));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].code", is(1)))
                .andExpect(jsonPath("$[0].name", is("tag 1")))
                .andExpect(jsonPath("$[0].category.code", is(1)))
                .andExpect(jsonPath("$[0].category.name", is("category")))
                .andExpect(jsonPath("$[1].code", is(2)))
                .andExpect(jsonPath("$[1].name", is("tag 2")))
                .andExpect(jsonPath("$[1].category.code", is(1)))
                .andExpect(jsonPath("$[1].category.name", is("category")));
    }

    private void givenGetTagsQueryWillSuccess(List<TagResponse> tags) {
        given(getTagsQuery.getTags()).willReturn(tags);
    }

    private List<TagResponse> givenTagsList() {
        CategoryResponse category = new CategoryResponse(1, "category");

        return List.of(
                new TagResponse(1, "tag 1", category),
                new TagResponse(2, "tag 2", category)
        );
    }
}