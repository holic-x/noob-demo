CREATE TABLE menu
(
    id        BIGINT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES menu (id)
);

INSERT INTO menu (id, name, parent_id)
VALUES (1, 'Root', NULL);

INSERT INTO menu (id, name, parent_id)
VALUES (2, '1 Child 1', 1);

INSERT INTO menu (id, name, parent_id)
VALUES (3, '1 Child 2', 1);

INSERT INTO menu (id, name, parent_id)
VALUES (4, '2 Child 1', 2);

INSERT INTO menu (id, name, parent_id)
VALUES (5, '2 Child 5', 3);

INSERT INTO menu (id, name, parent_id)
VALUES (6, '3 Child 6', 4);

INSERT INTO menu (id, name, parent_id)
VALUES (7, '3 Child 7', 5);