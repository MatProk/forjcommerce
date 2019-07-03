create table cars
(
    id                 bigserial PRIMARY KEY not null,
    car_brand          varchar(100) not null,
    car_model          varchar(100) not null,
    date_of_production timestamp    not null,
    description        text         not null,
    engine             varchar(100) not null,
    fuel_type          varchar(255) not null,
    payload            text,
    power_hp           int          not null
);
create table roles
(
    id bigserial PRIMARY KEY,
    name varchar(60)
);
create table users
(
    id      bigserial PRIMARY KEY,
    email    varchar(50),
    name     varchar(50),
    password varchar(100),
    username varchar(50)
);
create table user_roles
(
    user_id BIGINT not null,
    role_id BIGINT not null,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

create table test
(
    id bigserial PRIMARY KEY,
    test varchar(30)
);

