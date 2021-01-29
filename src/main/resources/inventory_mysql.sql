CREATE DATABASE nwhacks;

CREATE USER IF NOT EXISTS 'test'@'localhost' IDENTIFIED BY 'test';
CREATE USER IF NOT EXISTS 'test'@'%' IDENTIFIED BY 'test';
GRANT ALL ON records.* TO 'test'@'localhost';
GRANT ALL ON records.* TO 'test'@'%';

USE nwhacks;

DROP TABLE IF EXISTS employees;
CREATE TABLE employees(id TINYTEXT, salary DECIMAL(10,2), benefits DECIMAL(10,2), netrate SMALLINT);

INSERT INTO employees VALUES("ivaganov", 90000, 12000, 35);
INSERT INTO employees VALUES("sreid", 90001, 12001, 35);