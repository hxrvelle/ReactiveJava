CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    age INT
);

INSERT INTO users (name, age) VALUES
    ('Alice', 25),
    ('Alice', 255),
    ('Bob', 30),
    ('Bob', 300),
    ('Charlie', 22),
    ('Charlie', 222);