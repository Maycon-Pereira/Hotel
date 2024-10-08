CREATE TABLE usuario (
    id char(100) NOT NULL,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(100),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo TINYINT DEFAULT 1,
    imagem MEDIUMTEXT,
    
    PRIMARY KEY(id)
);
