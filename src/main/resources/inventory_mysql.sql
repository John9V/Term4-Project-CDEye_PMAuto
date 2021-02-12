CREATE DATABASE nwhacks;

CREATE USER IF NOT EXISTS 'test'@'localhost' IDENTIFIED BY 'test';
CREATE USER IF NOT EXISTS 'test'@'%' IDENTIFIED BY 'test';
GRANT ALL ON nwhacks.* TO 'test'@'localhost';
GRANT ALL ON nwhacks.* TO 'test'@'%';

USE nwhacks;

DROP TABLE IF EXISTS employees;
CREATE TABLE employees(id TINYTEXT, empnumber VARCHAR(30), firstname VARCHAR(30), lastname VARCHAR(30), username VARCHAR(30), paygrades TINYTEXT, active BOOLEAN);

INSERT INTO employees VALUES("623e4567-e89b-12d3-a456-556642440710", "SW-0012", "john", "ivaganov", "sreid", "123e4567-e89b-12d3-a456-556642440000", false);
INSERT INTO employees VALUES("223e4567-e89b-12d3-a456-556642446010", "HR-0016", "scott", "reid", "ivaganov", "123e4567-e89b-12d3-a456-556642440100", true);

DROP TABLE IF EXISTS paygrades;
CREATE TABLE paygrades(id TINYTEXT, salary DECIMAL(10,2), name TINYTEXT);

INSERT INTO paygrades VALUES("123e4567-e89b-12d3-a456-556642440000", 10, "P1");
INSERT INTO paygrades VALUES("123e4567-e89b-12d3-a456-556642440100", 90001, "P2");


DROP TABLE IF EXISTS credentials;
CREATE TABLE credentials(id TINYTEXT, userName TINYTEXT, password TINYTEXT);

INSERT INTO credentials VALUES("123e4567-e89b-12d3-9999-556642440000", "sreid", "password");
INSERT INTO credentials VALUES("123e4567-e89b-12d3-8888-556642440100", "ivaganov", "password1");


DROP TABLE IF EXISTS packagecostestimate;
CREATE TABLE packagecostestimate(id TINYTEXT, workpackage TINYTEXT, employee TINYTEXT, 
	paygrade TINYTEXT, persondaysestimate DECIMAL(10,2), dollarcostestimate DECIMAL(10,2));

INSERT INTO packagecostestimate VALUES("123e4567-e89b-12d3-a456-556642430000", "123e4567-e89b-12d3-a456-556342440300", NULL, 
	"123e4567-e89b-12d3-a456-556642440000", 10, 100);
INSERT INTO packagecostestimate VALUES("123e4537-e89b-12d4-a456-556642430000", "123e4567-e89b-12d3-a456-556342440300", NULL, 
	"123e4567-e89b-12d3-a456-556642440000", 5, 50);

--DROP TABLE IF EXISTS workpackages;
--CREATE TABLE workpackages(id TINYTEXT, workpackagenumber TINYTEXT, parentwp TINYTEXT, 
--	budgetestimate DECIMAL(10,2), budgetactual DECIMAL(10,2), persondayestimate DECIMAL(10,2), 
--	persondayactual DECIMAL(10,2));
--	
--INSERT INTO workpackages VALUES("123e4567-e89b-12d3-a456-556342440300", "1100", NULL, 150, 150, 15, 15);


-- 'workpackages' column is the parent workpackage uuid.
DROP TABLE IF EXISTS workpackages;
CREATE TABLE workpackages(
	id TINYTEXT PRIMARY KEY NOT NULL,
	workpackagenumber TINYTEXT,
	workpackages TINYTEXT, 
	unallocatedbudget DECIMAL(10,2),
	allocatedbudget DECIMAL(10,2),
	allocatedpersondays DECIMAL(10,2), 
	respengpersondayestimate DECIMAL(10,2),
	respengbudgetestimate DECIMAL(10,2), 
	completedbudget DECIMAL(10,2),
	completedpersondays DECIMAL(10,2),
	completedvarianceprojectpd DECIMAL(10,2),
	completedvarianceprojectbudget DECIMAL(10,2),
	respengestvarianceprojectpd DECIMAL(10,2),
	respengestvarianceprojectbudget DECIMAL(10,2),
	startdate DATE,
	enddate DATE,
	isleaf BOOLEAN,
	FOREIGN KEY (workpackages) REFERENCES workpackages(id)
);
	
INSERT INTO workpackages VALUES(
	"123e4567-e89b-12d3-a456-599342400001",
	"11000",
	NULL,
	1500, 900, 15, 15, 800, 310, 6, 0, 0, 0, 0,
	TO_DATE('2009-03-30', 'YYYY-MM-DD'),
	TO_DATE('2025-03-30', 'YYYY-MM-DD'),
	FALSE
);
INSERT INTO workpackages VALUES(
	"123e4567-e89b-12d3-a456-599342400002",
	"11100",
	"123e4567-e89b-12d3-a456-599342400001",
	1800, 800, 22, 25, 400, 320, 10, 0, 0, 0, 0,
	TO_DATE('2011-03-30', 'YYYY-MM-DD'),
	TO_DATE('2026-03-30', 'YYYY-MM-DD'),
	TRUE
);
INSERT INTO workpackages VALUES(
	"123e4567-e89b-12d3-a456-599342400003",
	"11200",
	"123e4567-e89b-12d3-a456-599342400001",
	1802, 804, 23, 22, 403, 321, 11, 0, 0, 0, 0,
	TO_DATE('2015-03-30', 'YYYY-MM-DD'),
	TO_DATE('2022-03-30', 'YYYY-MM-DD'),
	FALSE
);
INSERT INTO workpackages VALUES(
	"123e4567-e89b-12d3-a456-599342400004",
	"11210",
	"123e4567-e89b-12d3-a456-599342400003",
	1807, 801, 21, 25, 402, 322, 14, 0, 0, 0, 0,
	TO_DATE('2016-03-30', 'YYYY-MM-DD'),
	TO_DATE('2022-03-30', 'YYYY-MM-DD'),
	TRUE
);
