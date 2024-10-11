--liquibase formatted sql

--changeset felipe:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--rollback drop extension if exists "uuid-ossp";

--changeset felipe:2
CREATE TABLE IF NOT EXISTS "cliente" (
    "id"                    uuid           not null,
    "nome"                  varchar(500)   not null,
    "nome_responsavel"      varchar(500)   not null,
    "email"                 varchar(500)   not null,
    "documento"             varchar        not null,
    "documento_responsavel" varchar        not null,
    "telefone"              varchar        not null,
    "tipo_plano"            varchar        not null,
    "credito"               numeric(10, 2) not null,
    "limite_consumo"        numeric(10, 2) not null,
    constraint "cliente_pk" primary key ("id")
);

--rollback drop table if exists "cliente";

