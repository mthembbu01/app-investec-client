ALTER TABLE client ADD idnumber BIGINT NULL;

ALTER TABLE client MODIFY idnumber BIGINT NOT NULL;

ALTER TABLE client ADD CONSTRAINT uc_client_idnumber UNIQUE (idnumber);

ALTER TABLE client ADD CONSTRAINT uc_client_mobile_number UNIQUE (mobile_number);

DROP TABLE address_sequence;

DROP TABLE client_sequence;

ALTER TABLE client DROP COLUMN id_number;

ALTER TABLE address DROP COLUMN area_code;

ALTER TABLE address ADD area_code INT NULL;