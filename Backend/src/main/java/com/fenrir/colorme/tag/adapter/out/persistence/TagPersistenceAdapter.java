package com.fenrir.colorme.tag.adapter.out.persistence;

import com.fenrir.colorme.common.annotation.PersistenceAdapter;
import com.fenrir.colorme.tag.application.port.out.GetTagsPort;
import com.fenrir.colorme.tag.domain.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
class TagPersistenceAdapter implements GetTagsPort {
    private final TagRepository tagRepository;
    private final TagEntityMapper tagMapper;

    @Override
    public List<Tag> getTagsPort() {
        final List<TagEntity> tags = tagRepository.findAll();
        return tagMapper.toTagsList(tags);
    }
}
