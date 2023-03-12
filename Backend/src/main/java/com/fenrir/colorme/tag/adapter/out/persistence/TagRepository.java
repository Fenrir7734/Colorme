package com.fenrir.colorme.tag.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

interface TagRepository extends JpaRepository<TagEntity, Long> {
    Integer countAllByIdIn(Set<Long> ids);
    default boolean existsAllById(List<Long> ids) {
        Set<Long> idSet = new HashSet<>(ids);
        return countAllByIdIn(idSet).equals(idSet.size());
    }
}
