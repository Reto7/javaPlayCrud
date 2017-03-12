# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product_jpa (
  id                            serial not null,
  ean                           varchar(255),
  name                          varchar(255),
  description                   varchar(255),
  constraint pk_product_jpa primary key (id)
);


# --- !Downs

drop table if exists product_jpa cascade;

