CREATE SEQUENCE vehicle_sequence
  INCREMENT 1
  START 1;

CREATE TABLE public.vehicle (
	id int8 NOT NULL,
	brand varchar(150) NOT NULL,
	model varchar(100) NOT NULL,
	color varchar(70) NOT NULL,
	plate varchar(150) UNIQUE NOT NULL,
	type varchar(200) NOT NULL,
	CONSTRAINT vehicle_pkey PRIMARY KEY (id)
);