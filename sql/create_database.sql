DROP DATABASE IF EXISTS `beauty_salon`;
CREATE DATABASE IF NOT EXISTS `beauty_salon`;
USE `beauty_salon`;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `reviews`;
DROP TABLE IF EXISTS `appointments`;
DROP TABLE IF EXISTS `working_hours`;


CREATE TABLE `roles` (
                         id   int         NOT NULL UNIQUE AUTO_INCREMENT,
                         name varchar(50) NOT NULL UNIQUE,
                         PRIMARY KEY (id)
);


CREATE TABLE `users` (
                         id       int         NOT NULL UNIQUE AUTO_INCREMENT,
                         name     varchar(50) NOT NULL,
                         email    varchar(50) NOT NULL UNIQUE,
                         password varchar(50) NOT NULL,
                         role_id  int         NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (role_id) REFERENCES `roles` (id)
);

CREATE TABLE `products` (
                            id        int         NOT NULL UNIQUE AUTO_INCREMENT,
                            name      varchar(50) NOT NULL,
                            price     DECIMAL     NOT NULL,
                            duration  int         NOT NULL,
                            master_id int         NOT NULL ,
                            PRIMARY KEY (id),

                            FOREIGN KEY (master_id) REFERENCES users(id)
);

SET SQL_MODE ='ALLOW_INVALID_DATES';
CREATE TABLE `appointments` (
    id int NOT NULL UNIQUE AUTO_INCREMENT,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    appointment_master_status varchar(25) ,
    appointment_admin_status varchar(25) ,
    product_id int NOT NULL,
    client_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (client_id) REFERENCES users(id)
);

CREATE TABLE `reviews` (
    id int NOT NULL UNIQUE AUTO_INCREMENT,
    message text NOT NULL,
    evaluation int NOT NULL,
    master_id int NOT NULL,
    user_name varchar(50),
    PRIMARY KEY (id),
    FOREIGN KEY (master_id) REFERENCES products(master_id)
);

CREATE TABLE `working_hours` (
    id int NOT NULL UNIQUE AUTO_INCREMENT,
    start_working_day TIME NOT NULL,
    ena_working_day TIME NOT NULL,
    PRIMARY KEY (id)
);


INSERT INTO `roles` VALUES (DEFAULT, 'ADMIN');
INSERT INTO `roles` VALUES (DEFAULT, 'MASTER');
INSERT INTO `roles` VALUES (DEFAULT, 'CLIENT');

INSERT INTO `users` VALUES (DEFAULT, 'danylo', 'danylo@gmail.com', 'root', 1);
INSERT INTO `users` VALUES (DEFAULT, 'kate', 'kate@gmail.com', 'root', 2);
INSERT INTO `users` VALUES (DEFAULT, 'ann', 'ann@gmail.com', 'root', 2);
INSERT INTO `users` VALUES (DEFAULT, 'alise', 'alise@gmail.com', 'root', 2);
INSERT INTO `users` VALUES (DEFAULT, 'petro', 'petro@gmail.com', 'root', 3);
INSERT INTO `users` VALUES (DEFAULT, 'ivan', 'ivan@gmail.com', 'root', 3);
INSERT INTO `users` VALUES (DEFAULT, 'john', 'john@gmail.com', 'root', 3);
INSERT INTO `users` VALUES (DEFAULT, 'danya', 'danylo.chursinov@gmail.com', 'root', 3);

INSERT INTO `products` VALUES (DEFAULT, 'Manicure', '250', '60', 2);
INSERT INTO `products` VALUES (DEFAULT, 'Pedicure', '100', '45', 2);
INSERT INTO `products` VALUES (DEFAULT, 'Hairstyle', '150', '90', 3);
INSERT INTO `products` VALUES (DEFAULT, 'Hair painting', '300', '120', 3);
INSERT INTO `products` VALUES (DEFAULT, 'Make up', '120', '30', 4);

INSERT INTO `reviews` VALUES (DEFAULT, 'We wrote recently about how Google reviews help your Google rankings, and how to automate the ask. All you really need to know is that a staggering 9 out of 10 customers read reviews before deciding to purchase from you. There is a ton of research that goes a step further and attempts to quantify reviews actual dollar value. One such study by the Harvard Business Review found, “a one-star increase in Yelp rating leads to a 5-9% increase in revenue.” On the other hand, only 2.5 percent of consumers trust businesses with overall ratings of 2.0 stars or less. If 97% of potential customers skip over your business, it’ll be hard to keep the lights on.', 5, 2, 'Ivan');
INSERT INTO `reviews` VALUES (DEFAULT, 'We wrote recently about how Google reviews help your Google rankings, and how to automate the ask. All you really need to know is that a staggering 9 out of 10 customers read reviews before deciding to purchase from you. There is a ton of research that goes a step further and attempts to quantify reviews actual dollar value. One such study by the Harvard Business Review found, “a one-star increase in Yelp rating leads to a 5-9% increase in revenue.” On the other hand, only 2.5 percent of consumers trust businesses with overall ratings of 2.0 stars or less. If 97% of potential customers skip over your business, it’ll be hard to keep the lights on.', 4, 2, 'Anru');
INSERT INTO `reviews` VALUES (DEFAULT, 'We wrote recently about how Google reviews help your Google rankings, and how to automate the ask. All you really need to know is that a staggering 9 out of 10 customers read reviews before deciding to purchase from you. There is a ton of research that goes a step further and attempts to quantify reviews actual dollar value. One such study by the Harvard Business Review found, “a one-star increase in Yelp rating leads to a 5-9% increase in revenue.” On the other hand, only 2.5 percent of consumers trust businesses with overall ratings of 2.0 stars or less. If 97% of potential customers skip over your business, it’ll be hard to keep the lights on.', 3, 2, 'Andy');
INSERT INTO `reviews` VALUES (DEFAULT, 'We wrote recently about how Google reviews help your Google rankings, and how to automate the ask. All you really need to know is that a staggering 9 out of 10 customers read reviews before deciding to purchase from you. There is a ton of research that goes a step further and attempts to quantify reviews actual dollar value. One such study by the Harvard Business Review found, “a one-star increase in Yelp rating leads to a 5-9% increase in revenue.” On the other hand, only 2.5 percent of consumers trust businesses with overall ratings of 2.0 stars or less. If 97% of potential customers skip over your business, it’ll be hard to keep the lights on.', 2, 3, 'Valeriy');
INSERT INTO `reviews` VALUES (DEFAULT, 'We wrote recently about how Google reviews help your Google rankings, and how to automate the ask. All you really need to know is that a staggering 9 out of 10 customers read reviews before deciding to purchase from you. There is a ton of research that goes a step further and attempts to quantify reviews actual dollar value. One such study by the Harvard Business Review found, “a one-star increase in Yelp rating leads to a 5-9% increase in revenue.” On the other hand, only 2.5 percent of consumers trust businesses with overall ratings of 2.0 stars or less. If 97% of potential customers skip over your business, it’ll be hard to keep the lights on.', 1, 3, 'Bill');
INSERT INTO `reviews` VALUES (DEFAULT, 'We wrote recently about how Google reviews help your Google rankings, and how to automate the ask. All you really need to know is that a staggering 9 out of 10 customers read reviews before deciding to purchase from you. There is a ton of research that goes a step further and attempts to quantify reviews actual dollar value. One such study by the Harvard Business Review found, “a one-star increase in Yelp rating leads to a 5-9% increase in revenue.” On the other hand, only 2.5 percent of consumers trust businesses with overall ratings of 2.0 stars or less. If 97% of potential customers skip over your business, it’ll be hard to keep the lights on.', 3, 4, 'Petro');

INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-19 11:00:00', '2020-10-19 11:30:00', 'not_done', 'not_paid', 1, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-19 11:00:00', '2020-10-19 11:30:00', 'done', 'paid', 1, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-19 12:00:00', '2020-10-19 12:30:00', 'not_done', 'not_paid', 2, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-19 13:00:00', '2020-10-19 13:30:00', 'not_done', 'not_paid', 2, 6);

INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-22 15:00:00', '2020-10-22 15:30:00', 'not_done', 'not_paid', 2, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-22 16:00:00', '2020-10-22 16:30:00', 'not_done', 'not_paid', 2, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-22 17:00:00', '2020-10-22 17:30:00', 'not_done', 'not_paid', 2, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-22 18:00:20', '2020-10-22 18:30:00', 'not_done', 'not_paid', 2, 6);

INSERT INTO `working_hours` VALUES (DEFAULT, '12:00', '19:00');
