package com.fenrir.colorme.tag.adapter.out.persistence;

import com.fenrir.colorme.tag.domain.Tag;
import com.fenrir.colorme.tag.domain.TagCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
interface TagEntityMapper {
    List<Tag> toTagsList(List<TagEntity> tags);
    Tag toTag(TagEntity tag);
    TagCategory toTagCategory(TagCategoryEntity category);
}
