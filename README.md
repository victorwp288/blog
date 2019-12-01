MySQL:

CREATE TABLE IF NOT EXISTS users(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
username VARCHAR(50) NOT NULL,
passwords VARCHAR(60) NOT NULL,
privs VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS post(
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
title VARCHAR(60) NOT NULL,
body VARCHAR(560) NOT NULL,
dt DATE NOT NULL,
user_id INT UNSIGNED NOT NULL,
FOREIGN KEY(user_id) 
REFERENCES users(id)
);

INSERT INTO users(username, passwords, privs)
VALUES('Lotte', '1234', 'user'),
('victor', '1234', 'admin'),
('kim', '1234', 'user');
	

INSERT INTO post(title, body, dt, user_id)
VALUES('title', 'body', '2018-06-06', 1),
('Min test title', 'Min test body', '2019-11-27', 2),
('Title bedst', 'Body næst', '2001-01-01', 3);
