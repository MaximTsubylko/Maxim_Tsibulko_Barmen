
INSERT INTO ROLE (NAME) VALUES ('customer');
INSERT INTO ROLE (NAME) VALUES ('barmen');
INSERT INTO ROLE (NAME) VALUES ('administrator');

INSERT INTO user_state (NAME) VALUES ('active');
INSERT INTO user_state (NAME) VALUES ('banned');

INSERT INTO USER (login, password, email, first_name, last_name, registr_date,role_id,state_id)
VALUES ('root','root','test@email.com','testFirstName','testLastName','2000-09-09',3,1);

INSERT INTO USER (login, password, email, first_name, last_name, registr_date,role_id,state_id)
VALUES ('customer','customer','test2@email.com','test2FirstName','test2LastName','2000-09-09',1,1);

INSERT INTO USER (login, password, email, first_name, last_name, registr_date,role_id,state_id)
VALUES ('barmen','barmen','test3@email.com','test3FirstName','test3LastName','2000-09-09',2,1);

INSERT INTO INGREDIENT (NAME, DESCRIPTION) VALUES ('Tomato juice','tomato juice description'),
                                                  ('Vodka','Vodka description'),
                                                  ('Whiskey', 'Whiskey description'),
                                                  ('Cola','Cola description');

INSERT INTO COCKTAIL (NAME, PRICE, DESCRIPTION) VALUES ('Blood Mary',1000, 'Blood Mary discription'),
                                                  ('Whiskey and cola',1200 ,'Whiskey and cola discription');

INSERT INTO COCKTAIL_INGREDIENT (COCKTAIL_ID, INGREDIENT_ID) VALUES (1,1),
                                                                    (1,2);


INSERT INTO COCKTAIL_INGREDIENT (COCKTAIL_ID, INGREDIENT_ID) VALUES (2,3),
                                                                    (2,4);

INSERT INTO USER_COCTAILS (USER_ID, COCKTAIL_ID) VALUES (3,1),
                                                        (3,2);

INSERT INTO BARMEN_FEEDBACK (FROM_USER_ID, TO_USER_ID, TITLE, MARK, COMMENT) VALUES (2,3,'Incredible barmen',5,'Incredible barmen comment');

INSERT INTO COCKTAILE_FEEDBACK (USER_ID, COCKTAIL_ID, TITLE, MARK, COMMENT) VALUES (2,1,'bad cocktile',1,'Bad cocktile');
