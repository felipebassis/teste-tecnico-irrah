--liquibase formatted sql

--changeset felipe:1
CREATE TABLE IF NOT EXISTS "mensagem" (
    "id"              uuid      NOT NULL,
    "cliente_id"      uuid      not null,
    "numero_telefone" varchar   not null,
    "texto"           text      not null,
    "plataforma"      varchar   not null,
    "status"          varchar   not null,
    "data_criacao"    timestamp not null,
    constraint "mensagem_pk" primary key ("id"),
    constraint "mensagem_cliente_fk" foreign key ("cliente_id") references cliente ("id")
);

--rollback drop table if exists "mensagem";