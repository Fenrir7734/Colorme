package com.fenrir.colorme.tag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@AllArgsConstructor
@Getter
@Setter
public class Tag {
    private Long id;
    private Integer code;
    private String name;
    private TagCategory category;

    public static Comparator<Tag> tagComparator() {
        Comparator<Tag> comparator = Comparator.comparing(tag -> tag.getCategory().getName());
        return comparator.thenComparing(tag -> tag.name);
    }
}
