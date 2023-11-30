CREATE DATABASE IF NOT EXISTS dbagenda;
SHOW DATABASES;
USE dbagenda;

CREATE TABLE contatos(
	idcontato int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
	fone varchar(15) NOT NULL,
    email varchar(50)
);

SHOW TABLES;
DESCRIBE contatos;


/* CRUD CREATE */

INSERT INTO contatos (nome, fone, email) VALUE ('Bill Gates', '219999-8888', 'bill@outlook.com');

/* CRUD READ */

SELECT * FROM contatos;

/* CRUD UPDATE */

UPDATE contatos SET nome = "?" WHERE idcontato = ?;
