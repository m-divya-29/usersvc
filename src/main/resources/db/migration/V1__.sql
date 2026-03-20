CREATE TABLE `role`
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE role_users
(
    role_id  BIGINT NOT NULL,
    users_id BIGINT NOT NULL
);

CREATE TABLE token
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    value     VARCHAR(255) NULL,
    expiry_at datetime NULL,
    user_id   BIGINT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    name              VARCHAR(255) NULL,
    email             VARCHAR(255) NULL,
    hashed_password   VARCHAR(255) NULL,
    is_email_verified BIT(1) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

CREATE TABLE user_tokens
(
    user_id   BIGINT NOT NULL,
    tokens_id BIGINT NOT NULL
);

ALTER TABLE user_tokens
    ADD CONSTRAINT uc_user_tokens_tokens UNIQUE (tokens_id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE role_users
    ADD CONSTRAINT fk_roluse_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE role_users
    ADD CONSTRAINT fk_roluse_on_user FOREIGN KEY (users_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_tokens
    ADD CONSTRAINT fk_usetok_on_token FOREIGN KEY (tokens_id) REFERENCES token (id);

ALTER TABLE user_tokens
    ADD CONSTRAINT fk_usetok_on_user FOREIGN KEY (user_id) REFERENCES user (id);