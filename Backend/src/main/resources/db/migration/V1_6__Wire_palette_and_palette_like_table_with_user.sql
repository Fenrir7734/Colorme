DELETE
FROM palette_likes;

DELETE
FROM palette_colors;

DELETE
FROM palette_tags;

DELETE
FROM palettes;

ALTER TABLE palettes
    ADD COLUMN owner_id BIGINT;

ALTER TABLE palettes
    ADD FOREIGN KEY (owner_id) REFERENCES users (id);

DROP TABLE palette_likes;

CREATE TABLE palette_likes
(
    user_id    BIGINT    NOT NULL,
    palette_id BIGINT    NOT NULL,
    created_at TIMESTAMP NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (palette_id) REFERENCES palettes (id),
    PRIMARY KEY (user_id, palette_id)
);



