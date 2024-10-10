CREATE TABLE quartos (
    id VARCHAR(255) NOT NULL,
    numero VARCHAR(50) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    capacidade INT NOT NULL,
    preco_por_noite DOUBLE NOT NULL,
    disponivel TINYINT DEFAULT 1,
    
    PRIMARY KEY (id)
);
