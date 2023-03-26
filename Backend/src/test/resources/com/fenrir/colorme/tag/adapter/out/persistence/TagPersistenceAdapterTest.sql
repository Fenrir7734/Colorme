INSERT INTO tag_categories (id, code, name) VALUES (100, 1, 'Colors');
INSERT INTO tag_categories (id, code, name) VALUES (101, 2, 'Styles');

INSERT INTO tags (id, code, name, category_id) VALUES (100, 1, 'Red', 100);
INSERT INTO tags (id, code, name, category_id) VALUES (101, 2, 'Green', 100);
INSERT INTO tags (id, code, name, category_id) VALUES (102, 3, 'Warm', 101);