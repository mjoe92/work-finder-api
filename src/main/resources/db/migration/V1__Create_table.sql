alter table client_jobs drop constraint FK6858lvjgjomn2c2a2jol2hyc0;
alter table client_jobs drop constraint FKiba6mnfq0da7q2vayft4kc3b7;
alter table job drop constraint FKnjbsumt44u8xps6yg5f16ynig;
alter table job_client_list drop constraint FKh6h01mffcoie6f0trpvn360jv;
alter table job_client_list drop constraint FKk97cjl9o19t2y7w6iewvjpk9r;
drop table if exists api cascade;
drop table if exists client cascade;
drop table if exists client_jobs cascade;
drop table if exists employer cascade;
drop table if exists job cascade;
drop table if exists job_client_list cascade;
create table api
(
    key  varchar(255) not null,
    id   varchar(255),
    name varchar(255),
    url  varchar(255),
    primary key (key)
);
create table client
(
    id    varchar(255) not null,
    email varchar(255),
    name  varchar(255),
    primary key (id)
);
create table client_jobs
(
    client_id varchar(255) not null,
    jobs_id   varchar(255) not null,
    primary key (client_id, jobs_id)
);
create table employer
(
    id      varchar(255) not null,
    company varchar(255),
    email   varchar(255),
    name    varchar(255),
    primary key (id)
);
create table job
(
    id            varchar(255) not null,
    category      varchar(255),
    company       varchar(255),
    contract_time varchar(255),
    created       varchar(255),
    description   varchar(255),
    location      varchar(255),
    max_salary    int4,
    min_salary    int4,
    title         varchar(255),
    url           varchar(255),
    employer_id   varchar(255),
    primary key (id)
);
create table job_client_list
(
    job_id         varchar(255) not null,
    client_list_id varchar(255) not null
);
alter table client
    add constraint UK_bfgjs3fem0hmjhvih80158x29 unique (email);
alter table employer
    add constraint UK_kde6jr4yp7v8fc949kh1tgyvp unique (email);
alter table client_jobs
    add constraint FK6858lvjgjomn2c2a2jol2hyc0 foreign key (jobs_id) references job;
alter table client_jobs
    add constraint FKiba6mnfq0da7q2vayft4kc3b7 foreign key (client_id) references client;
alter table job
    add constraint FKnjbsumt44u8xps6yg5f16ynig foreign key (employer_id) references employer;
alter table job_client_list
    add constraint FKh6h01mffcoie6f0trpvn360jv foreign key (client_list_id) references client;
alter table job_client_list
    add constraint FKk97cjl9o19t2y7w6iewvjpk9r foreign key (job_id) references job;