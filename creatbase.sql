DROP DATABASE IF EXISTS cocktailebar;
CREATE DATABASE cocktailebar;
use cocktailebar;
create table role
(
  id tinyint auto_increment
    primary key,
  name varchar(30) not null,
  constraint name_UNIQUE
    unique (name)
);


create table user_state
(
  id tinyint auto_increment
    primary key,
  name varchar(30) not null
);


create table user
(
  id smallint(6) auto_increment
    primary key,
  login varchar(100) not null,
  password varchar(64) not null,
  email varchar(100) not null,
  first_name varchar(50) null,
  last_name varchar(50) null,
  registr_date date null,
  role tinyint default 1 not null,
  state_id tinyint default 1 not null,
  constraint email_UNIQUE
    unique (email),
  constraint login_UNIQUE
    unique (login),
  constraint fk_user_role1
    foreign key (role) references role (id),
  constraint fk_user_user_state1
    foreign key (state_id) references user_state (id)
);

create table cocktail
(
  id smallint(6) auto_increment
    primary key,
  name varchar(20) not null,
  price int not null,
  description mediumtext null
);

create table ingredient
(
  id smallint(6) auto_increment
    primary key,
  name varchar(40) not null,
  description mediumtext null
);

create table cocktail_ingredient
(
  id smallint(6) auto_increment
    primary key,
  cocktail_id smallint(6) not null,
  ingredient_id smallint(6) not null,
  constraint fk_cocktail_ingredient_cocktail1
    foreign key (cocktail_id) references cocktail (id),
  constraint fk_cocktail_ingredient_ingredient1
    foreign key (ingredient_id) references ingredient (id)
);


create table barmen_feedback
(
  id smallint(6) auto_increment
    primary key,
  from_user_id smallint(6) not null,
  to_user_id smallint(6) not null,
  title varchar(100) not null,
  mark tinyint(1) not null,
  comment mediumtext null,
  constraint fk_barmen_user_user1
    foreign key (from_user_id) references user (id),
  constraint fk_barmen_user_user2
    foreign key (to_user_id) references user (id)
);

create table cocktaile_feedback
(
  id smallint(6) auto_increment
    primary key,
  user_id smallint(6) not null,
  cocktail_id smallint(6) not null,
  title varchar(100) not null,
  mark tinyint(1) not null,
  comment mediumtext null,
  constraint fk_cocktaile_feedback_cocktail1
    foreign key (cocktail_id) references cocktail (id),
  constraint fk_user_cocktail_user
    foreign key (user_id) references user (id)
);

create table user_coctails
(
  id smallint(6) auto_increment
    primary key,
  user_id smallint(6) not null,
  cocktail_id smallint(6) not null,
  constraint fk_user_coctails_cocktail1
    foreign key (cocktail_id) references cocktail (id),
  constraint fk_user_coctails_user1
    foreign key (user_id) references user (id)
);

