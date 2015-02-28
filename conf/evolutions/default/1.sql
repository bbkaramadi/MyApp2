# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table employee (
  employee_id               bigint auto_increment not null,
  employee_name             varchar(255),
  salary                    integer,
  department                varchar(255),
  constraint pk_employee primary key (employee_id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists employee;

SET REFERENTIAL_INTEGRITY TRUE;

