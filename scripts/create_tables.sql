CREATE TABLE musicos (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    genero_musical VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL
);

CREATE TABLE eventos (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome_evento VARCHAR(120) NOT NULL,
    local_evento VARCHAR(120) NOT NULL,
    data_evento DATE NOT NULL,
    musico_id BIGINT NOT NULL,
    CONSTRAINT fk_eventos_musicos
        FOREIGN KEY (musico_id)
        REFERENCES musicos(id)
);