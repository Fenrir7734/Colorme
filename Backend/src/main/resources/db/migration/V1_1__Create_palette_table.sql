CREATE TABLE palettes
(
    id         SERIAL PRIMARY KEY,
    code       CHAR(32)  NOT NULL DEFAULT md5(random()::text),
    created_at TIMESTAMP NOT NULL
);