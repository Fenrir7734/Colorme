INSERT INTO tag_categories (id, code, name) VALUES (100, 100, 'Colors');

INSERT INTO tags (id, code, name, category_id) VALUES (100, 100, 'Red', 100);
INSERT INTO tags (id, code, name, category_id) VALUES (101, 101, 'Green', 100);

INSERT INTO palettes (id, code, created_at) VALUES (100, '1234', current_timestamp);
INSERT INTO palette_colors (id, hex, palette_id) VALUES (100, 'FFFFFF', 100);
INSERT INTO palette_colors (id, hex, palette_id) VALUES (101, 'AAAAAA', 100);
INSERT INTO palette_tags (palette_id, tag_id) VALUES (100, 100);
INSERT INTO palette_likes (id, created_at, palette_id) VALUES (100, current_timestamp, 100);
INSERT INTO palette_likes (id, created_at, palette_id) VALUES (101, current_timestamp, 100);
INSERT INTO palette_likes (id, created_at, palette_id) VALUES (102, current_timestamp, 100);