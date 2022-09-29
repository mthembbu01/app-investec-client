drop table address_sequence;

drop table client_sequence;

alter table address drop COLUMN area_code;

alter table address add area_code INT NULL;

alter table client drop COLUMN idnumber;

alter table client add idnumber VARCHAR(13) NOT NULL;


alter table client add CONSTRAINT uc_client_idnumber UNIQUE (idnumber);