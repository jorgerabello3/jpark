CREATE TABLE public.address (
	id int8 NOT NULL,
	city varchar(150) NULL,
	neighborhood varchar(100) NULL,
	"number" varchar(10) NULL,
	state varchar(150) NULL,
	street varchar(200) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id)
);

CREATE TABLE public.phone (
	id int8 NOT NULL,
	country_code varchar(2) NULL,
	ddd varchar(2) NULL,
	"number" varchar(10) NULL,
	CONSTRAINT phone_pkey PRIMARY KEY (id)
);


CREATE TABLE public.company (
	car_parking_spot int4 NOT NULL,
	motor_cycle_parking_spot int4 NOT NULL,
	address_id int8 NULL,
	id int8 NOT NULL,
	phone_id int8 NULL,
	cnpj varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT company_address_id_key UNIQUE (address_id),
	CONSTRAINT company_phone_id_key UNIQUE (phone_id),
	CONSTRAINT company_pkey PRIMARY KEY (id),
	CONSTRAINT phone_fk FOREIGN KEY (phone_id) REFERENCES public.phone(id),
	CONSTRAINT address_fk FOREIGN KEY (address_id) REFERENCES public.address(id)
);