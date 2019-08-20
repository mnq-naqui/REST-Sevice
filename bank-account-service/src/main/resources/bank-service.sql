-- State Table

CREATE TABLE state

( state_id INT(11) NOT NULL AUTO_INCREMENT,
  state_name VARCHAR(50) NOT NULL,
  country_code VARCHAR(3),
  
  CONSTRAINT state_pk PRIMARY KEY (state_id)
);

------------------------------------------------------------------------------------------------

-- City Table

CREATE TABLE city

( city_id INT(11) NOT NULL AUTO_INCREMENT,
  city_name VARCHAR(50) NOT NULL,
  city_code VARCHAR(3) NOT NULL,
  state_ref INT(11) NOT NULL,

  CONSTRAINT city_pk PRIMARY KEY (city_id)
);

----------------------------------------------------------------------------------------------

-- Bank Table

create table bank (

  bank_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bank_name VARCHAR(50) NOT NULL,
  bank_code VARCHAR(12) NOT NULL,
  ifsc_code VARCHAR(12) NOT NULL,
  address VARCHAR(50) NOT NULL,
  pincode VARCHAR(12) NOT NULL,
  city_ref INT(11) NOT NULL,
  state_ref INT(11) NOT NULL,
  
  foreign key (city_ref) references city(city_id),
  foreign key (state_ref) references state(state_id)
);

--------------------------------------------------------------------------------------------

-- Branch Table

create table branch (

  branch_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  branch_name VARCHAR(50) NOT NULL,
  branch_code VARCHAR(12) NOT NULL,
  ifsc_code VARCHAR(12) NOT NULL,
  address VARCHAR(50) NOT NULL,
  pincode VARCHAR(12) NOT NULL,
  city_ref INT(11) NOT NULL,
  state_ref INT(11) NOT NULL,
  bank_ref INT(11) NOT NULL,
  
  foreign key (city_ref) references city(city_id),
  foreign key (state_ref) references state(state_id),
  foreign key (bank_ref) references bank(bank_id)

);

---------------------------------------------------------------------------------------------

-- UserDetail Table

create table user_detail (

  user_detail_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(30) NOT NULL,
  mobile VARCHAR(12) NOT NULL,
  current_address VARCHAR(150) NOT NULL,
  permanent_address VARCHAR(150) NOT NULL,
  pincode VARCHAR(12) NOT NULL,
  city_ref INT(11) NOT NULL,
  state_ref INT(11) NOT NULL,
   
  foreign key (city_ref) references city(city_id),
  foreign key (state_ref) references state(state_id)

);

-----------------------------------------------------------------------------------------------
-- AccountDetail Table

create table account_detail (

  account_detail_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_number VARCHAR(15) NOT NULL,
  account_type VARCHAR(30) NOT NULL,
  balance double NOT NULL  

);

---------------------------------------------------------------------------------------------

-- AccountMapping Table

create table account_mapping(

  account_detail_ref INT(11) NOT NULL ,
  bank_ref INT(11) NOT NULL ,
  branch_ref VARCHAR(50) NOT NULL ,
  user_ref INT(11) NOT NULL ,  
   
  foreign key (bank_ref) references bank(bank_id),
  foreign key (user_ref) references user_detail(user_detail_id)

);

-------------------------------------------------------------------------------------------

-- State Insert

INSERT INTO state VALUES (1, 'Karnataka' ,'IN');
INSERT INTO state VALUES (2, 'Madhya Pradesh' ,'IN');
INSERT INTO state VALUES (3, 'Maharastra' ,'IN');
INSERT INTO state VALUES (4, 'Telangana' ,'IN');

------------------------------------------------------------------------------------------

-- insert city;

INSERT INTO city VALUES (1, 'Bangalore' ,'101', 1);
INSERT INTO city VALUES (2, 'Tumkur' ,'102', 1);
INSERT INTO city VALUES (3, 'Hassan' ,'103', 1);

INSERT INTO city VALUES (4, 'Bhopal' ,'201', 2);
INSERT INTO city VALUES (5, 'Indore' ,'202', 2);
INSERT INTO city VALUES (6, 'Chhindwara' ,'203', 2);

INSERT INTO city VALUES (7, 'Mumbai' ,'301', 3);
INSERT INTO city VALUES (8, 'Nagpur' ,'302', 3);
INSERT INTO city VALUES (9, 'Pune' ,'303', 3);

INSERT INTO city VALUES (10, 'Hyderabad' ,'401', 4);

---------------------------------------------------------------------------------------

-- insert bank

INSERT INTO bank VALUES (2, 'AXIS' ,'AXIS-IN' , 'IFSC-AXIS', 'AXIS India' , '560172', 2 , 2 );
INSERT INTO bank VALUES (3, 'ICICI' ,'ICICI-IN' , 'IFSC-ICICI', 'ICICI India' , '564572', 3 , 2);

----------------------------------------------------------------------------------------
-- insert branch

INSERT INTO branch VALUES (1, 'SBI-Branch One' ,'BR1-SBI' , 'IFSC-SBI-BR1', 'SBI-Branch One India' , '560102', 1 , 2 ,1);
INSERT INTO branch VALUES (2, 'SBI-Branch Two' ,'BR2-SBI' , 'IFSC-SBI-BR2', 'SBI-Branch Two India' , '560102', 2, 1 ,1);
INSERT INTO branch VALUES (3, 'SBI-Branch Three' ,'BR3-SBI' , 'IFSC-SBI-BR3', 'SBI-Branch Three India' , '560102', 3 , 2 ,1);

INSERT INTO branch VALUES (4, 'AXIS-Branch One' ,'BR1-SBI' , 'IFSC-SBI-BR1', 'AXIS-Branch One India' , '560102', 3 , 2 ,2);
INSERT INTO branch VALUES (5, 'AXIS-Branch Two' ,'BR2-SBI' , 'IFSC-SBI-BR2', 'AXIS-Branch Two India' , '560102', 2, 3 ,2);
INSERT INTO branch VALUES (6, 'AXIS-Branch Three' ,'BR3-SBI' , 'IFSC-SBI-BR3', 'AXIS-Branch Three India' , '560102', 4 , 1,2);

INSERT INTO branch VALUES (7, 'ICICI-Branch One' ,'BR1-ICICI' , 'IFSC-ICI-BR1', 'ICICI-Branch One India' , '560102', 4 , 1 ,3);
INSERT INTO branch VALUES (8, 'ICICI-Branch Two' ,'BR2-ICICI' , 'IFSC-ICI-BR2', 'ICICI-Branch Two India' , '560102', 4, 1 ,3);




