DROP DATABASE IF EXISTS `beauty_salon`;
CREATE DATABASE IF NOT EXISTS `beauty_salon`;
USE `beauty_salon`;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `reviews`;
DROP TABLE IF EXISTS `appointments`;


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
    PRIMARY KEY (id),
    FOREIGN KEY (master_id) REFERENCES products(master_id)
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

INSERT INTO `products` VALUES (DEFAULT, 'Manicure', '250', '60', 2);
INSERT INTO `products` VALUES (DEFAULT, 'Pedicure', '100', '45', 2);
INSERT INTO `products` VALUES (DEFAULT, 'Hairstyle', '150', '90', 3);
INSERT INTO `products` VALUES (DEFAULT, 'Hair painting', '300', '120', 3);
INSERT INTO `products` VALUES (DEFAULT, 'Make up', '120', '30', 4);

INSERT INTO `reviews` VALUES (DEFAULT, 'adasdasd dqwdqwd', 5, 2);
INSERT INTO `reviews` VALUES (DEFAULT, 'cbdfbbdb wqdqwd32d', 4, 2);
INSERT INTO `reviews` VALUES (DEFAULT, 'therthdfg qwdqdqwd', 3, 2);
INSERT INTO `reviews` VALUES (DEFAULT, 'egrwevewv qwdqwdqwd', 2, 3);
INSERT INTO `reviews` VALUES (DEFAULT, 'wefwefewfqqwdqwd qwd', 1, 3);
INSERT INTO `reviews` VALUES (DEFAULT, 'wefwefwef qwdqwdqwd', 3, 4);

INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-15 11:00:00', '2020-10-15 11:30:00', 'not done', 'not paid', 1, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-15 12:00:00', '2020-10-15 12:30:00', 'not done', 'not paid', 2, 5);
INSERT INTO `appointments` VALUES (DEFAULT, '2020-10-15 13:00:00', '2020-10-15 13:30:00', 'not done', 'not paid', 2, 6);
