drop table if exists task CASCADE 
drop table if exists vehicle CASCADE 
create table task (id bigint generated by default as identity, due_date date, name varchar(255), status varchar(255), vehicle_id bigint, primary key (id))
create table vehicle (id bigint generated by default as identity, colour varchar(255), horse_power integer not null, make varchar(255), model varchar(255), registration varchar(255), primary key (id))
alter table task add constraint FKfsefuw3eu4hk591ysimetjyig foreign key (vehicle_id) references vehicle