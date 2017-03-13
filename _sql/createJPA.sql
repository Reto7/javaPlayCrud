create table ProductJPA (
 id SERIAL PRIMARY KEY,
 ean text,
 name text,
 description text
);
insert into ProductJPA(ean,name,description) values ('111','Buch','xxxxxxx');
insert into ProductJPA(ean,name,description) values ('222','Auto','yyy yyyy yyyy');
insert into ProductJPA(ean,name,description) values ('333','Flugzeug','zzzzzzzz');

select *
from ProductJPA;