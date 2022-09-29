use db_investec;

-- CREATE SEQUENCE address_sequence START WITH 1 INCREMENT BY 1;
--
-- CREATE SEQUENCE client_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE address (
  id BIGINT NOT NULL,
   address_type VARCHAR(31),
   line_one VARCHAR(32),
   line_two VARCHAR(32),
   suburb VARCHAR(20),
   city VARCHAR(20),
   area_code INT(4),
   CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE client (
  id BIGINT NOT NULL,
   first_name varchar(30) NOT NULL,
   last_name varchar(30) NOT NULL,
   mobile_number VARCHAR(12) NOT NULL,
   id_number VARCHAR(13) NOT NULL,
   address_id BIGINT,
   CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE physical_address (
   id BIGINT NOT NULL,
   street_address VARCHAR(32),
   CONSTRAINT pk_physical_address PRIMARY KEY (id)
);

ALTER TABLE client ADD CONSTRAINT FK_CLIENT_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES address (id);

ALTER TABLE physical_address ADD CONSTRAINT FK_PHYSICAL_ADDRESS_ON_ID FOREIGN KEY (id) REFERENCES address (id);