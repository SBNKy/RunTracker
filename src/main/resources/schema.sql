-- Usuwanie tabel jeśli istnieją (dla czystego startu)
DROP TABLE IF EXISTS runs;
DROP TABLE IF EXISTS users;

-- Tabela users
CREATE TABLE users
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    created_at TIMESTAMP NOT NULL
);

-- Tabela runs
CREATE TABLE runs
(
    id                INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id           INTEGER        NOT NULL,
    name              VARCHAR(255),
    distance          DECIMAL(10, 2) NOT NULL,
    started_at        TIMESTAMP      NOT NULL,
    completed_on      TIMESTAMP      NOT NULL,
    -- Foreign key
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT
);