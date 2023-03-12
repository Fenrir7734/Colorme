package com.fenrir.colorme.tag.application.port.in.gettags.response;

import lombok.Value;

@Value
public class TagResponse {
    Integer code;
    String name;
    CategoryResponse category;
}
