package com.fenrir.colorme.tag.application.service.mapper;

import com.fenrir.colorme.tag.application.port.in.gettags.response.CategoryResponse;
import com.fenrir.colorme.tag.application.port.in.gettags.response.TagResponse;
import com.fenrir.colorme.tag.domain.Tag;
import com.fenrir.colorme.tag.domain.TagCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    List<TagResponse> toTagResponseList(List<Tag> tags);
    TagResponse toTagResponse(Tag tag);
    CategoryResponse toCategoryResponse(TagCategory category);
}
