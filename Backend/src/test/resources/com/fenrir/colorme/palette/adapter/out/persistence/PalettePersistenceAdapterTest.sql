INSERT INTO users (id, code, provider, external_id, external_name, email, role, created_at, updated_at)
VALUES (1, '1', 'GOOGLE', '1234', 'user1', 'user1@gmail.com', 'USER', current_timestamp, current_timestamp);
INSERT INTO users (id, code, provider, external_id, external_name, email, role, created_at, updated_at)
VALUES (2, '2', 'GOOGLE', '5678', 'user2', 'user3@gmail.com', 'USER', current_timestamp, current_timestamp);
INSERT INTO users (id, code, provider, external_id, external_name, email, role, created_at, updated_at)
VALUES (3, '3', 'FACEBOOK', '4321', 'user3', 'user2@gmail.com', 'ADMIN', current_timestamp, current_timestamp);

INSERT INTO tag_categories (id, code, name) VALUES (100, 100, 'Colors');

INSERT INTO tags (id, code, name, category_id) VALUES (100, 100, 'Red', 100);
INSERT INTO tags (id, code, name, category_id) VALUES (101, 101, 'Green', 100);

INSERT INTO palettes (id, code, owner_id, created_at) VALUES (100, '1234', 1, current_timestamp);
INSERT INTO palette_colors (id, hex, palette_id) VALUES (100, 'FFFFFF', 100);
INSERT INTO palette_colors (id, hex, palette_id) VALUES (101, 'AAAAAA', 100);
INSERT INTO palette_tags (palette_id, tag_id) VALUES (100, 100);
INSERT INTO palette_likes (palette_id, user_id, created_at) VALUES (100, 1, current_timestamp);
INSERT INTO palette_likes (palette_id, user_id, created_at) VALUES (100, 2, current_timestamp);
INSERT INTO palette_likes (palette_id, user_id, created_at) VALUES (100, 3, current_timestamp);

INSERT INTO palettes (id, code, owner_id, created_at) VALUES (101, '5678', 1, current_timestamp);
INSERT INTO palette_colors (id, hex, palette_id) VALUES (102, 'FFFFFF', 101);
INSERT INTO palette_colors (id, hex, palette_id) VALUES (103, 'AAAAAA', 101);