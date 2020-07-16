CREATE TABLE et_user(
    user_id INTEGER PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    password TEXT NOT NULL

);

CREATE TABLE et_category(
    category_id INTEGER PRIMARY KEY,
    user_id INTEGER REFERENCES et_user(user_id ),
    title VARCHAR(20) NOT NULL,
    description VARCHAR(50) not null
);

CREATE TABLE et_transaction(
    transaction_id INTEGER PRIMARY KEY,
    category_id INTEGER REFERENCES et_category(category_id),
    user_id INTEGER REFERENCES et_user(user_id ),
    amount NUMERIC(10,2) NOT NULL,
    note VARCHAR(50) not null,
    transaction_date BIGINT NOT NULL
);

CREATE SEQUENCE et_user_seq INCREMENT 1 START 1;
CREATE SEQUENCE et_category_seq INCREMENT 1 START 1;
CREATE SEQUENCE et_transaction_seq INCREMENT 1 START 1000;




