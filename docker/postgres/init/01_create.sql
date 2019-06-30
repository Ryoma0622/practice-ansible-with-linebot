CREATE TABLE login_user
(
    id        INTEGER      NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL
);

INSERT INTO login_user (id, user_name, password)
VALUES (1, 'user1', '$2a$10$pqoPLb4PgUzMyfzHZOWdzemN641lZJ5ut1HbX98QdU/DsBrpUy0l6' /*user1*/),
       (2, 'user2', '$2a$10$skmpESIl3T/N8/wQSXmIEuk8stiA4AYdsVwIFlx6XL8c/w71deZ1C' /*user2*/),
       (3, 'user3', '$2a$10$5KYHU4MPalqJR94V0q30XejyZ/OAlCtbCUEZaTUQbxHPzqd4Vbkh.' /*user3*/)
;