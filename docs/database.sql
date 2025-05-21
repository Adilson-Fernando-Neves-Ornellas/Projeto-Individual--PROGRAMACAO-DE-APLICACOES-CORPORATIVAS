-- Tabela de Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(11),
    data_nasc DATE,
    senha VARCHAR(255) NOT NULL
);

-- Tabela de Compras
CREATE TABLE compras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    forma_pagamento ENUM('DINHEIRO', 'CARTAO', 'PIX') NOT NULL,
    status ENUM('PENDENTE', 'PAGO', 'CANCELADO') NOT NULL,
    valor FLOAT NOT NULL,
    cliente_id INT NOT NULL,
    qtd_bilhete INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Tabela de Eventos
CREATE TABLE eventos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_hora_inicio DATETIME NOT NULL,
    data_hora_fim DATETIME NOT NULL,
    capacidade INT NOT NULL,
    valor_ingresso DOUBLE NOT NULL
);

-- Tabela de Bilhetes
CREATE TABLE bilhetes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    evento_id INT NOT NULL,
    compra_id INT NOT NULL,
    FOREIGN KEY (evento_id) REFERENCES eventos(id),
    FOREIGN KEY (compra_id) REFERENCES compras(id)
);
