
use productdb;
drop table if exists product;
create table product (
    product_id bigint primary key auto_increment,
    product_name varchar(100),
    price float not null,
    quantity float not null
);

insert into product(product_name, price, quantity) values
                        ('product1', 123, 100),
                        ('product2', 400, 50);