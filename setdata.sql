use cocktailebar;
insert into role (name) value ("customer");
insert into role (name) value ("barmen");
insert into role (name) value ("administrator");

insert into user_state (name) value ("active");
insert into user_state (name) value ("banned");

insert into user (login, password, email, first_name, last_name, registr_date,role,state_id)
VALUES ("root","root","test@email.com","testFirstName","testLastName","2000-09-09",3,1);

insert into user (login, password, email, first_name, last_name, registr_date,role,state_id)
VALUES ("customer","customer","test2@email.com","test2FirstName","test2LastName","2000-09-09",1,1);

insert into user (login, password, email, first_name, last_name, registr_date,role,state_id)
VALUES ("barmen","barmen","test3@email.com","test3FirstName","test3LastName","2000-09-09",2,1);

insert into ingredient (name, description) VALUES ("Tomato juice","tomato juice description"),
                                                  ("Vodka","Vodka description"),
                                                  ("Whiskey", "Whiskey description"),
                                                  ("Cola","Cola description");

insert into cocktail (name, price, description) VALUES ("Blood Mary",1000, "Blood Mary discription"),
                                                  ("Whiskey and cola",1200 ,"Whiskey and cola discription");

insert into cocktail_ingredient (cocktail_id, ingredient_id) VALUES (1,1),
                                                                    (1,2);


insert into cocktail_ingredient (cocktail_id, ingredient_id) VALUES (2,3),
                                                                    (2,4);

insert into user_coctails (user_id, cocktail_id) values (3,1),
                                                        (3,2);

insert into barmen_feedback (from_user_id, to_user_id, title, mark, comment) values (2,3,"Incredible barmen",5,"Incredible barmen comment");

insert into cocktaile_feedback (user_id, cocktail_id, title, mark, comment) values (2,1,"bad cocktile",1,"Bad cocktile");
