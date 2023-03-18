package com.fenrir.colorme.tag.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.tag.application.port.in.gettags.GetTagsQuery;
import com.fenrir.colorme.tag.application.port.in.gettags.response.TagResponse;
import com.fenrir.colorme.tag.application.port.out.GetTagsPort;
import com.fenrir.colorme.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Transactional
class GetTagsService implements GetTagsQuery {
    private final GetTagsPort getTagsPort;
    private final TagMapper tagMapper;

    @Override
    public List<TagResponse> getTags() {
        final List<Tag> tags = getTagsPort.getAllTags()
                .stream()
                .sorted(Tag.tagComparator())
                .collect(Collectors.toList());

        return tagMapper.toTagResponseList(tags);
    }
}
