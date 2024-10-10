CREATE TABLE reservas (
    id VARCHAR(255) NOT NULL,
    usuario_id VARCHAR(255) NOT NULL,
    quarto_id VARCHAR(255) NOT NULL,
    data_checkin DATETIME NOT NULL,
    data_checkout DATETIME NOT NULL,
    data_reserva DATETIME NOT NULL,
    metodo_pagamento VARCHAR(100) NOT NULL,
    valor_reserva DOUBLE NOT NULL,
    disponivel TINYINT DEFAULT 1,
    capacidade INT NOT NULL,
    
    PRIMARY KEY (id)
);
