package com.fenrir.colorme.tag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class TagCategory {
    private final Long id;
    private final Integer code;
    private final String name;
}
