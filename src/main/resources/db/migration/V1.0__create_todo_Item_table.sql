create table todo_item (
    id int not null auto_increment primary key,
    text varchar(255) not null,
    finished BOOLEAN not null
)
