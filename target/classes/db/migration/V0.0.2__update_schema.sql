ALTER TABLE client DROP FOREIGN KEY FK_CLIENT_ON_ADDRESS;

-- DROP TABLE address_sequence;

-- DROP TABLE client_sequence;

ALTER TABLE address DROP COLUMN area_code;

ALTER TABLE address ADD area_code INT NULL;

ALTER TABLE client DROP COLUMN id_number;

ALTER TABLE client ADD id_number BIGINT NOT NULL;