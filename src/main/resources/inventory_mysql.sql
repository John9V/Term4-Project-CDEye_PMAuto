CREATE DATABASE nwhacks;

CREATE USER IF NOT EXISTS 'test'@'localhost' IDENTIFIED BY 'test';
CREATE USER IF NOT EXISTS 'test'@'%' IDENTIFIED BY 'test';
GRANT ALL ON nwhacks.* TO 'test'@'localhost';
GRANT ALL ON nwhacks.* TO 'test'@'%';

USE nwhacks;

DROP TABLE IF EXISTS employees;
CREATE TABLE employees(id TINYTEXT, empnumber VARCHAR(30), firstname VARCHAR(30), lastname VARCHAR(30), salary DECIMAL(10,2), benefits DECIMAL(10,2), netrate SMALLINT, paygrades TINYTEXT, active BOOLEAN);

INSERT INTO employees VALUES("623e4567-e89b-12d3-a456-556642440710", "SW-0012", "john", "ivaganov", 90000, 12000, 35, "123e4567-e89b-12d3-a456-556642440000", false);
INSERT INTO employees VALUES("223e4567-e89b-12d3-a456-556642446010", "HR-0016", "scott", "reid", 90001, 12001, 35, "123e4567-e89b-12d3-a456-556642440100", true);

DROP TABLE IF EXISTS paygrades;
CREATE TABLE paygrades(id TINYTEXT, salary DECIMAL(10,2), name TINYTEXT);

INSERT INTO paygrades VALUES("123e4567-e89b-12d3-a456-556642440000", 90000, "P1");
INSERT INTO paygrades VALUES("123e4567-e89b-12d3-a456-556642440100", 90001, "P2");
