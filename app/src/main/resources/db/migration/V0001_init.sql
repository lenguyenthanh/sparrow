CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY,

    name  TEXT NOT NULL,
    login TEXT NOT NULL,
    email TEXT NOT NULL
);