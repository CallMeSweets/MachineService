DROP DATABASE IF EXISTS machine-conf;

CREATE DATABASE machine_conf;

DROP TABLE IF EXISTS DICT_MACHINE_TYPE;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS LOCATION;
DROP TABLE IF EXISTS MACHINE;



use machine_conf;

CREATE TABLE DICT_MACHINE_TYPE (
id integer(11) AUTO_INCREMENT PRIMARY KEY,
event_type VARCHAR(20) NOT NULL
);

INSERT INTO DICT_MACHINE_TYPE (event_type) VALUES ("DRINK");
INSERT INTO DICT_MACHINE_TYPE (event_type) VALUES ("FOOD");
INSERT INTO DICT_MACHINE_TYPE (event_type) VALUES ("TOYS");

CREATE TABLE LOCATION (
id integer(11) AUTO_INCREMENT PRIMARY KEY,
city VARCHAR(50) NOT NULL,
street VARCHAR(50) NOT NULL,
street_number integer(11) NOT NULL,
description VARCHAR(250) NOT NULL
);

INSERT INTO LOCATION (city, street, street_number, description) VALUES ("Warszawa", "Polna", 12, "Short description");
INSERT INTO LOCATION (city, street, street_number, description) VALUES ("Poznan", "Warynskiego", 15, "Short description");

CREATE TABLE USER (
id integer(11) AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
email VARCHAR(50),
phone_number VARCHAR(9) UNIQUE
);

INSERT INTO USER (firstname, lastname, email, phone_number) VALUES ("Wiktor", "Lazarski", "xxx@gmail.com", "509281489");
INSERT INTO USER (firstname, lastname, email, phone_number) VALUES ("Kacper", "Kamieniarz", "yyy@gmail.com", "123456789");

CREATE TABLE MACHINE (
id integer(11) AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
owner_id integer(11),
location_id integer(11) NOT NULL,
machine_type_id integer(11) NOT NULL
);


ALTER TABLE MACHINE ADD CONSTRAINT FK_MACHINE_OWNER_ID FOREIGN KEY (owner_id) REFERENCES USER(id);
ALTER TABLE MACHINE ADD CONSTRAINT FK_MACHINE_DICT_MACHINE_TYPE_ID FOREIGN KEY (machine_type_id) REFERENCES DICT_MACHINE_TYPE(id);
ALTER TABLE MACHINE ADD CONSTRAINT FK_MACHINE_LOCATION_ID FOREIGN KEY (location_id) REFERENCES LOCATION(id);

INSERT INTO MACHINE (name, owner_id, location_id, machine_type_id) VALUES ("Frank Machine", 1, 1, 1);
INSERT INTO MACHINE (name, owner_id, location_id, machine_type_id) VALUES ("Donkey Machine", 2, 2, 3);

