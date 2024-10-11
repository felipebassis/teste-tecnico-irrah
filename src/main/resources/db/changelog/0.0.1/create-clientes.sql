--liquibase formatted sql

--changeset felipe:1
INSERT INTO
    "cliente"
values
    (uuid_generate_v4(),
     'Empresa teste Pre-Pago',
     'nome_responsavel',
     'teste@teste.com',
     '74897997000160',
     '20446927082',
     '+55 44 999999999',
     'PRE_PAGO',
     100.50,
     0
    ),
    (uuid_generate_v4(),
     'Empresa teste Pos-Pago',
     'nome_responsavel',
     'teste@teste.com',
     '74897997000160',
     '20446927082',
     '+55 44 999999999',
     'POS_PAGO',
     0,
     200.00
    )