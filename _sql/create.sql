create table product (
 id SERIAL PRIMARY KEY,
 ean text,
 name text,
 description text
);
insert into product(ean,name,description) values ('111','Buch','xxxxxxx');
insert into product(ean,name,description) values ('222','Auto','yyy yyyy yyyy');
insert into product(ean,name,description) values ('333','Flugzeug','zzzzzzzz');

select *
from product;