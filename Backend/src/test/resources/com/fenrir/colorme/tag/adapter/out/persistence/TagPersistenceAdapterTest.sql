INSERT INTO tag_categories (id, code, name) VALUES (100, 100, 'Colors');
INSERT INTO tag_categories (id, code, name) VALUES (101, 101, 'Styles');

INSERT INTO tags (id, code, name, category_id) VALUES (100, 100, 'Red', 100);
INSERT INTO tags (id, code, name, category_id) VALUES (101, 101, 'Green', 100);
INSERT INTO tags (id, code, name, category_id) VALUES (102, 102, 'Warm', 101);