## Exemplo de CRUD utilizando JDBC

### SQL da demonstração

```
CREATE DATABASE IF NOT EXISTS agencia;
USE agencia;

CREATE TABLE IF NOT EXISTS comprador (
	  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome varchar(70)  NOT NULL,
    cpf varchar(14) NOT NULL
);

CREATE TABLE IF NOT EXISTS carro (
	  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    placa varchar(8) NOT NULL,
    nome varchar(50)  NOT NULL,
    compradorid int 
);

ALTER TABLE carro ADD FOREIGN KEY (compradorid) REFERENCES comprador(id)

```
