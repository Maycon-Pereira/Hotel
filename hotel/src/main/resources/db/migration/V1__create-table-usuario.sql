CREATE TABLE usuario (
    id char(100) NOT NULL,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(100),
    documento_identidade VARCHAR(100) NOT NULL,
    data_nascimento VARCHAR(100) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    endereco VARCHAR(100),
    ativo TINYINT DEFAULT 1,
    imagem MEDIUMTEXT,
    
    PRIMARY KEY(id)
);
