# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table carro (
  id                            bigint auto_increment not null,
  placa                         varchar(255),
  modelo                        varchar(255),
  constraint pk_carro primary key (id)
);

create table estacionamento (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  cep                           varchar(255),
  vaga                          integer not null,
  constraint pk_estacionamento primary key (id)
);

create table produto (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  descricao                     varchar(255),
  unidade                       varchar(255),
  preco                         double,
  constraint pk_produto primary key (id)
);


# --- !Downs

drop table if exists carro;

drop table if exists estacionamento;

drop table if exists produto;

