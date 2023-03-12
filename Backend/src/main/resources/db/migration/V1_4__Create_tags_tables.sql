CREATE TABLE tag_categories
(
    id   SERIAL PRIMARY KEY,
    code INT          NOT NULL,
    name VARCHAR(255) NOT NULL,

    CONSTRAINT uq_tag_categories_code UNIQUE (code)
);

CREATE TABLE tags
(
    id          SERIAL PRIMARY KEY,
    code        INT          NOT NULL,
    name        VARCHAR(255) NOT NULL,
    category_id BIGINT       NOT NULL,

    CONSTRAINT uq_tags_code UNIQUE (code),
    FOREIGN KEY (category_id) REFERENCES tag_categories (id)
);

CREATE TABLE color_palette_tags
(
    palette_id BIGINT REFERENCES palettes,
    tag_id     BIGINT REFERENCES tags,

    CONSTRAINT pk_palettes_tags PRIMARY KEY (palette_id, tag_id)
);

INSERT INTO tag_categories (id, code, name)
VALUES (1, 1, 'Colors'),
       (2, 2, 'Styles'),
       (3, 3, 'Topics');
SELECT setval('tag_categories_id_seq', 3, true);

INSERT INTO tags (id, code, name, category_id)
VALUES (1, 1, 'Red', 1),
       (2, 2, 'Orange', 1),
       (3, 3, 'Brown', 1),
       (4, 4, 'Yellow', 1),
       (5, 5, 'Green', 1),
       (6, 6, 'Blue', 1),
       (7, 7, 'Violet', 1),
       (8, 8, 'Pink', 1),
       (9, 9, 'Gray', 1),
       (10, 10, 'Black', 1),
       (11, 11, 'White', 1),
       (12, 12, 'Warm', 2),
       (13, 13, 'Cold', 2),
       (14, 14, 'Bright', 2),
       (15, 15, 'Dark', 2),
       (16, 16, 'Pastel', 2),
       (17, 17, 'Vintage', 2),
       (18, 18, 'Monochromatic', 2),
       (19, 19, 'Gradient', 2),
       (20, 20, 'Rainbow', 2),
       (21, 21, '2 Colors', 2),
       (22, 22, '3 Colors', 2),
       (23, 23, '4 Colors', 2),
       (24, 24, '5 Colors', 2),
       (25, 25, '6 Colors', 2),
       (26, 26, '7 Colors', 2),
       (27, 27, '8 Colors', 2),
       (28, 28, '9 Colors', 2),
       (29, 29, '10 Colors', 2),
       (30, 30, 'Christmas', 3),
       (31, 31, 'Halloween', 3),
       (32, 32, 'Pride', 3),
       (33, 33, 'Sunset', 3),
       (34, 34, 'Spring', 3),
       (35, 35, 'Winter', 3),
       (36, 36, 'Summer', 3),
       (37, 37, 'Autumn', 3),
       (38, 38, 'Gold', 3),
       (39, 39, 'Wedding', 3),
       (40, 40, 'Party', 3),
       (41, 41, 'Space', 3),
       (42, 42, 'Kids', 3),
       (43, 43, 'Nature', 3),
       (44, 44, 'City', 3),
       (45, 45, 'Food', 3),
       (46, 46, 'Happy', 3),
       (47, 47, 'Water', 3),
       (48, 48, 'Relax', 3);
SELECT setval('tags_id_seq', 48, true);