CREATE TABLE customer (
	id BIGINT AUTO_INCREMENT PRIMARY KEY ,
	first_name varchar(100) not null,
	last_name varchar(100) not null
);


CREATE TABLE mobile_numbers (
  customer_id INT NOT NULL,
  mobile_number VARCHAR(15) NOT NULL,
  PRIMARY KEY (customer_id, mobile_number),
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);

ALTER TABLE mobile_numbers ADD CONSTRAINT mobile_numbers_uk1 UNIQUE (mobile_number);