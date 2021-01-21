create table USUARIO
(
    ID       LONG
        constraint USUARIO_PK
            unique,
    NOME     VARCHAR(255),
    ENDERECO VARCHAR(255),
    TELEFONE INT,
    EMAIL    VARCHAR(11),
    SENHA    VARCHAR(64)
);

comment on table USUARIO is 'Cadastro de usuários com acesso ao sistema';

create table LISTAS
(
    ID         LONG,
    USUARIO_ID LONG,
    NOME       VARCHAR(255),
    constraint LISTA_USUARIO_ID_FK
        foreign key (ID) references USUARIO (ID)
);

comment on table LISTA is 'Cadastro de Lista de Tarefas';

create table TAREFAS
(
    ID             LONG
        constraint TAREFAS_PK
            unique,
    USUARIO_ID     LONG,
    DESCRICAO      TEXT,
    DATA_PREVISTA  DATETIME,
    DATA_CONCLUIDA DATETIME,
    STATUS         INT,
    LISTA_ID       LONG,
    constraint TAREFAS_USUARIO_ID_FK
        foreign key (ID) references USUARIO (ID)
);

comment on table TAREFAS is 'Cadastro de tarefas realizadas pelo usuário';

