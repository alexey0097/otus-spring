INSERT INTO AUTHORS (AUTHOR_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME) VALUES (1, 'Гоголь', 'Николай', 'Васильевич');
INSERT INTO AUTHORS (AUTHOR_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME) VALUES (2, 'Толстой', 'Лев', 'Николаевич');
INSERT INTO AUTHORS (AUTHOR_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME) VALUES (3, 'Пушкин', 'Александр', 'Сергеевич');
INSERT INTO AUTHORS (AUTHOR_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME) VALUES (4, 'Барто', 'Агния', 'Львовна');

INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (1, 'Фантастика');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (2, 'Приключения');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (3, 'Мистика');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (4, 'Детективы');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (5, 'Учебная литература');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (6, 'Женские романы');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (7, 'Научные книги');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (8, 'Классика');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (9, 'Научно-популярные');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (10, 'Исторические книги');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (11, 'Классика');
INSERT INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (12, 'Научно популярные');

INSERT INTO USERS (USER_ID, USER_NAME, USER_PASSWORD, IS_ACTIVE, ROLES) VALUES (1, 'admin', 'admin', true, 'ROLE_ADMIN');
INSERT INTO USERS (USER_ID, USER_NAME, USER_PASSWORD, IS_ACTIVE, ROLES) VALUES (2, 'user', 'user', true, 'ROLE_USER');