package com.fenrir.colorme.tag.application.port.in;

import java.util.List;

public interface ExistsAllTagsQuery {
    boolean existsAllTags(List<Long> tagIds);
}
