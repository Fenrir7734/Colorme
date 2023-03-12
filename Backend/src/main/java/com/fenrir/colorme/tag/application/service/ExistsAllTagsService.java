package com.fenrir.colorme.tag.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.tag.application.port.in.ExistsAllTagsQuery;
import com.fenrir.colorme.tag.application.port.out.ExistsAllTagsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ExistsAllTagsService implements ExistsAllTagsQuery {
    private final ExistsAllTagsPort existsAllTagsPort;

    @Override
    public boolean existsAllTags(List<Long> tagIds) {
        return existsAllTagsPort.existsAllTags(tagIds);
    }
}
