CREATE TABLE inspetor (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT ck_inspetor_nome_nao_vazio
        CHECK (BTRIM(nome) <> '')
);

CREATE TABLE setor (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   nome VARCHAR(100) NOT NULL,
   ativo BOOLEAN NOT NULL DEFAULT TRUE,
   CONSTRAINT ck_setor_nome_nao_vazio
   CHECK (BTRIM(nome) <> '')
);


CREATE TABLE nao_conformidade (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo_painel VARCHAR(50) NOT NULL,
    componente VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    data_registro TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    inspetor_id BIGINT NOT NULL,
    setor_origem_id BIGINT NOT NULL,
    CONSTRAINT fk_nao_conformidade_inspetor
        FOREIGN KEY (inspetor_id)
        REFERENCES inspetor(id),
    CONSTRAINT fk_nao_conformidade_setor_origem
        FOREIGN KEY (setor_origem_id)
        REFERENCES setor(id),
    CONSTRAINT ck_nao_conformidade_status
        CHECK (status IN (
            'ABERTA',
            'EM_TRATAMENTO',
            'AGUARDANDO_REINSPECAO',
            'ENCERRADA'
        ))
);

CREATE TABLE historico_status (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nao_conformidade_id BIGINT NOT NULL,
    status_anterior VARCHAR(30) NOT NULL,
    status_novo VARCHAR(30) NOT NULL,
    data_alteracao TIMESTAMP NOT NULL,
    CONSTRAINT fk_historico_status_nao_conformidade
        FOREIGN KEY (nao_conformidade_id)
        REFERENCES nao_conformidade(id),
    CONSTRAINT ck_historico_status_anterior
        CHECK (status_anterior IN (
                          'ABERTA',
                          'EM_TRATAMENTO',
                          'AGUARDANDO_REINSPECAO',
                          'ENCERRADA'
        )),
    CONSTRAINT ck_historico_status_novo
        CHECK (status_novo IN (
                          'ABERTA',
                          'EM_TRATAMENTO',
                          'AGUARDANDO_REINSPECAO',
                          'ENCERRADA'
        )),
    CONSTRAINT ck_historico_status_diferentes
        CHECK (status_anterior <> status_novo)
);