alter table client_jobs drop constraint FK6858lvjgjomn2c2a2jol2hyc0
alter table client_jobs drop constraint FKiba6mnfq0da7q2vayft4kc3b7
alter table employer_jobs drop constraint FK6ixpamwgi5d0pmmyqaky8dno3
alter table employer_jobs drop constraint FKjuimxosw0vpsb4lbigljjwryr
drop table if exists api cascade
drop table if exists client cascade
drop table if exists client_jobs cascade
drop table if exists employer cascade
drop table if exists employer_jobs cascade
drop table if exists job cascade
create table api (key varchar(255) not null, id varchar(255), name varchar(255), url varchar(255), primary key (key))
create table client (id uuid not null, email varchar(255), name varchar(255), primary key (id))
create table client_jobs (client_id uuid not null, jobs_id uuid not null)
create table employer (id varchar(255) not null, email varchar(255), name varchar(255), primary key (id))
create table employer_jobs (employer_id varchar(255) not null, jobs_id uuid not null)
create table job (id uuid not null, description varchar(255), location varchar(255), title varchar(255), url varchar(255), primary key (id))
alter table client add constraint UK_bfgjs3fem0hmjhvih80158x29 unique (email)
alter table client_jobs add constraint FKiba6mnfq0da7q2vayft4kc3b7 foreign key (client_id) references client
alter table employer_jobs add constraint FKjuimxosw0vpsb4lbigljjwryr foreign key (employer_id) references employer
alter table employer_jobs add constraint FK6ixpamwgi5d0pmmyqaky8dno3 foreign key (jobs_id) references job
alter table client_jobs add constraint UK_g4igk5qp5dyutnvtb0q7pid9 unique (jobs_id)
alter table employer add constraint UK_kde6jr4yp7v8fc949kh1tgyvp unique (email)
alter table employer_jobs add constraint UK_dh7ols3g6en4c4ms05e88ax8l unique (jobs_id)
alter table client_jobs add constraint FK6858lvjgjomn2c2a2jol2hyc0 foreign key (jobs_id) references job
