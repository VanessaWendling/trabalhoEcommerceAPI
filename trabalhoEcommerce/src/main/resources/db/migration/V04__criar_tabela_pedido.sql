CREATE TABLE pedido (id_pedido SERIAL PRIMARY KEY,
data_pedido DATE NOT NULL,
data_entrega DATE, 
data_envio DATE,
status VARCHAR(20),
id_cliente INTEGER, FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente));