package com.fenrir.colorme.palette.application.service.exception;

public class TagsNotFoundException extends RuntimeException {
    private static final String MESSAGE = "One or more of provided tags has not been found";

    public TagsNotFoundException() {
        super(MESSAGE);
    }
}
