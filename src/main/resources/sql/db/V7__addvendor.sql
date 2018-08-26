CREATE TABLE VENDOR(
	vendor_id bigint NOT NULL,
	vendor_name varchar(50),
	vendor_contactno bigint,
	vendor_email varchar(50),
	vendor_username varchar(50),
	vendor_address varchar(50),
	PRIMMARY KEY (vendor_id)
);

CREATE TABLE INVENTORY (
	sku_id bigint NOT NULL,
	product_name varchar(100),
	poduct_label varchar(100),
	inventory_onhand int,
	reqminqty int,
	price double,
	PRIMARY KEY (skuid);
);


INSERT INTO VENDOR VALUES(0,'vendor1',1234512345,xx@yy.com,'vendor1','chennai');
INSERT INTO VENDOR VALUES(1,'vendor2',1234512345,xx@yy.com,'vendor2','chennai');

INSERT INTO INVENTORY VALUES(0,'REDMI NOTE','REDMI NOTE4',10,2,10000.00);
INSERT INTO INVENTORY VALUES(1,'MI A1','MI ANDROID ONE',10,2,14000.00);

