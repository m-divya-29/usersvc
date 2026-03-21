ALTER TABLE usersvc.token
    ADD is_deleted BIT(1) NULL;

ALTER TABLE usersvc.token
    MODIFY is_deleted BIT (1) NOT NULL;