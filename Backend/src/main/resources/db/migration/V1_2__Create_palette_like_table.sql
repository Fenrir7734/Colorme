CREATE TABLE palette_likes
(
    id         SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    palette_id BIGINT    NOT NULL,

    FOREIGN KEY (palette_id) REFERENCES palettes (id)
);