create table ticket_quote (
    id bigint not null unique primary key, close numeric(19,2),
     date timestamp , high numeric(19,2),
      low numeric(19,2), open numeric(19,2),
       ticket bigint, volume integer );

insert into ticket_quote (id, close, date, high, low, open, ticket, volume) values (
    1, 157.400,  '2019-12-20 16:00:00', 157.5300, 157.2400, 157.5200, 1, 785507);
