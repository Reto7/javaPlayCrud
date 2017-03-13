create sequence product_seq start 1;

create table ProductJPA (
 id integer default nextval('product_seq') PRIMARY KEY,
 ean text,
 name text,
 description text
);
insert into ProductJPA(id,ean,name,description) values (nextval('product_seq'),'111','Buch','xxxxxxx');
insert into ProductJPA(id,ean,name,description) values (nextval('product_seq'),'222','Auto','yyy yyyy yyyy');
insert into ProductJPA(id,ean,name,description) values (nextval('product_seq'),'333','Flugzeug','zzzzzzzz');

select *
from ProductJPA;