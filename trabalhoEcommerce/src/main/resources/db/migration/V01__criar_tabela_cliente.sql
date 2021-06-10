CREATE TABLE cliente (id_cliente SERIAL PRIMARY KEY,
email varchar(30) NOT NULL,
nome_usuario varchar(20) NOT NULL,
nome_completo varchar(60) NOT NULL,
senha varchar(255),
cpf varchar(14) NOT NULL,
telefone varchar(11),
data_nasc DATE, 
id_endereco INTEGER, FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco));