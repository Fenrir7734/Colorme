package com.fenrir.colorme.tag.application.port.out;

import com.fenrir.colorme.tag.domain.Tag;

import java.util.List;

public interface GetTagsPort {
    List<Tag> getAllTags();
}
