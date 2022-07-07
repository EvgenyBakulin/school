/*Я добавил к машине Car ещё id, потому что же марка и модель могут совпадать, а
  цена в качестве ключа смотрится достаточно нелепо*/
create table Car (
                     id integer primary key,
                     mark varchar(28) not null ,
                     model varchar(28) not null ,
                     price integer not null
);

insert into Car(id,mark, model, price) values (1,'Лада','Гранта', 20000);
insert into Car(id,mark, model, price) values (2,'Лада','Ларгус', 30000);

create table Person (
                        name varchar(64) primary key,
                        age integer check ( age>18 ) not null ,
                        drivecard boolean check(drivecard=true) not null,
                        car_id integer references Car(id)
);

insert into Person(name, age, drivecard,car_id) values ('Саша', 20, true,1);
insert into Person(name, age, drivecard,car_id) values ('Миша', 22, true,2);
insert into Person(name, age, drivecard,car_id) values ('Петя', 25, true,2);