CREATE TABLE item_pedido (id_item_pedido SERIAL PRIMARY KEY,
quantidade INTEGER NOT NULL,
preco_venda INTEGER NOT NULL,
 id_pedido INTEGER, FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
 id_produto INTEGER,  FOREIGN KEY (id_produto) REFERENCES produto(id_produto));