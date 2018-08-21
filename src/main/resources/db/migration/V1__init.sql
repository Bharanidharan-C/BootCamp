CREATE TABLE CUSTOMER (
	customer_id bigint NOT NULL,
	customer_name varchar(100),
	contact_number bigint NOT NULL,
	address varchar(100),
	gender varchar(8),
	PRIMARY KEY (customer_id)
);

INSERT INTO CUSTOMER VALUES (0, 'Anu', 1234567890, 'xxx','female');
INSERT INTO CUSTOMER VALUES (1, 'Akash', 1234567891, 'yyy','male');


