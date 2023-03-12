package com.fenrir.colorme.tag.application.port.out;

import java.util.List;

public interface ExistsAllTagsPort {
    boolean existsAllTags(List<Long> tagIds);
}
