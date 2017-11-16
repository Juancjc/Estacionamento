# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table produto (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  descricao                     varchar(255),
  unidade                       varchar(255),
  preco                         double,
  constraint pk_produto primary key (id)
);


# --- !Downs

drop table if exists produto;

