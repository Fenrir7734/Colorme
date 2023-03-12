package com.fenrir.colorme.tag.adapter.in;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.tag.application.port.in.gettags.GetTagsQuery;
import com.fenrir.colorme.tag.application.port.in.gettags.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
class GetTagsController {
    private final GetTagsQuery getTagsQuery;

    @PostMapping
    ResponseEntity<List<TagResponse>> getTags() {
        return ResponseEntity.ok(getTagsQuery.getTags());
    }
}
