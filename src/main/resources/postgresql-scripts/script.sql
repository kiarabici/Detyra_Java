CREATE TABLE USERS
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
);
CREATE TABLE Quiz
(
    id     SERIAL PRIMARY KEY,
    answer VARCHAR(255) NOT NULL
);

CREATE TABLE Question
(
    id          SERIAL PRIMARY KEY,
    quiz_id     INTEGER REFERENCES Quiz (id),
    index       INT          NOT NULL,
    answer      VARCHAR(255) NOT NULL,
    rightAnswer VARCHAR(255) NOT NULL
);

CREATE TABLE Option
(
    id          SERIAL PRIMARY KEY,
    question_id INTEGER REFERENCES Question (id),
    value       VARCHAR(255) NOT NULL
);

CREATE TABLE Result
(
    id       SERIAL PRIMARY KEY,
    quiz_id  INTEGER REFERENCES Quiz (id),
    username VARCHAR(255) NOT NULL
);

CREATE TABLE UserAnswer
(
    id          SERIAL PRIMARY KEY,
    result_id   INTEGER REFERENCES Result (id),
    question_id INTEGER REFERENCES Question (id),
    answer      VARCHAR(255) NOT NULL,
    correct     boolean      NOT NULL
);
