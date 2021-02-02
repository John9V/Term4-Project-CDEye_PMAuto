CREATE DATABASE nwhacks;

CREATE USER IF NOT EXISTS 'test'@'localhost' IDENTIFIED BY 'test';
CREATE USER IF NOT EXISTS 'test'@'%' IDENTIFIED BY 'test';
GRANT ALL ON nwhacks.* TO 'test'@'localhost';
GRANT ALL ON nwhacks.* TO 'test'@'%';

USE nwhacks;

DROP TABLE IF EXISTS employees;
CREATE TABLE employees(id TINYTEXT, salary DECIMAL(10,2), benefits DECIMAL(10,2), netrate SMALLINT);

INSERT INTO employees VALUES("ivaganov", 90000, 12000, 35);
INSERT INTO employees VALUES("sreid", 90001, 12001, 35);

DROP TABLE IF EXISTS paygrades;
CREATE TABLE paygrades(id TINYTEXT, salary DECIMAL(10,2), name TINYTEXT);

INSERT INTO paygrades VALUES("123e4567-e89b-12d3-a456-556642440000", 10, "P1");
INSERT INTO paygrades VALUES("123e4567-e89b-12d3-a456-556642440100", 90001, "P2");

DROP TABLE IF EXISTS packagecostestimate;
CREATE TABLE packagecostestimate(id TINYTEXT, workpackage TINYTEXT, employee TINYTEXT, 
	paygrade TINYTEXT, persondaysestimate DECIMAL(10,2), dollarcostestimate DECIMAL(10,2));

INSERT INTO packagecostestimate VALUES("123e4567-e89b-12d3-a456-556642430000", "123e4567-e89b-12d3-a456-556342440300", NULL, 
	"123e4567-e89b-12d3-a456-556642440000", 10, 100);
INSERT INTO packagecostestimate VALUES("123e4537-e89b-12d4-a456-556642430000", "123e4567-e89b-12d3-a456-556342440300", NULL, 
	"123e4567-e89b-12d3-a456-556642440000", 5, 50);

DROP TABLE IF EXISTS workpackages;
CREATE TABLE workpackages(id TINYTEXT, workpackagenumber TINYTEXT, parentwp TINYTEXT, 
	budgetestimate DECIMAL(10,2), budgetactual DECIMAL(10,2), persondayestimate DECIMAL(10,2), 
	persondayactual DECIMAL(10,2));
	
INSERT INTO workpackages VALUES("123e4567-e89b-12d3-a456-556342440300", "1100", NULL, 150, 150, 15, 15);
