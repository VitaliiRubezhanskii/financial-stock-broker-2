create table ticket (id bigint not null unique primary key,
 ticket varchar(25), company varchar(50), description varchar(255));

insert into ticket (id, ticket, company, description) VALUES (1, 'MSFT', 'Microsoft', 'Software production & services');