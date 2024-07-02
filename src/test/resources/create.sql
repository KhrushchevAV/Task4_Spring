create table if not exists users (
    id          serial primary key,
    username    varchar not null unique,
    fio         varchar not null
);

create table if not exists logins (
    id          serial primary key,
    access_date timestamp,
    user_id     integer not null,
    application varchar
    foreign key (user_id) references users (id)
);