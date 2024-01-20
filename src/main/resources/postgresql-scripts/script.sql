CREATE TABLE USERS
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
);
CREATE TABLE QUIZZES
(
    id     SERIAL PRIMARY KEY,
    answer VARCHAR(255) NOT NULL
);

CREATE TABLE QUESTIONS
(
    id      SERIAL PRIMARY KEY,
    quiz_id INTEGER REFERENCES Quiz (id),
    index   INT          NOT NULL,
    name    VARCHAR(255) NOT NULL
);

CREATE TABLE OPTIONS
(
    id          SERIAL PRIMARY KEY,
    question_id INTEGER REFERENCES Question (id),
    value       VARCHAR(255) NOT NULL,
    is_answer   BOOLEAN      NOT NULL
);


CREATE TABLE RESULTS
(
    id       SERIAL PRIMARY KEY,
    quiz_id  INTEGER REFERENCES Quiz (id),
    username VARCHAR(255) NOT NULL
);

CREATE TABLE USER_ANSWERS
(
    id          SERIAL PRIMARY KEY,
    result_id   INTEGER REFERENCES Result (id),
    question_id INTEGER REFERENCES Question (id),
    answer      VARCHAR(255) NOT NULL,
    correct     boolean      NOT NULL
);
