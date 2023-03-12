CREATE TABLE palette_colors
(
    id         SERIAL PRIMARY KEY,
    hex        CHAR(6) NOT NULL,
    palette_id BIGINT  NOT NULL,

    FOREIGN KEY (palette_id) REFERENCES palettes (id)
);