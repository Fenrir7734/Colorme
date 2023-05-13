CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    code          CHAR(32)     NOT NULL DEFAULT md5(random()::text),
    provider      VARCHAR(50)  NOT NULL,
    external_id   VARCHAR(256) NOT NULL,
    external_name VARCHAR(100) NOT NULL,
    email         VARCHAR(256) NOT NULL,
    role          VARCHAR(50)  NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    updated_at    TIMESTAMP    NOT NULL,

    CONSTRAINT uq_users_code UNIQUE (code),
    CONSTRAINT uq_users_email UNIQUE (email),
    CONSTRAINT uq_provider_external_id UNIQUE (provider, external_id)
)