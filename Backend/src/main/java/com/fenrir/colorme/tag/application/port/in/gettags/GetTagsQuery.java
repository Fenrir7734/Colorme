package com.fenrir.colorme.tag.application.port.in.gettags;

import com.fenrir.colorme.tag.application.port.in.gettags.response.TagResponse;

import java.util.List;

public interface GetTagsQuery {
    List<TagResponse> getTags();
}
