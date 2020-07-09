CREATE TABLE public.profiles (
	id int8 NOT NULL,
	user_id int8 NOT NULL,
	full_name varchar NOT NULL,
	dob date NOT NULL,
	gender varchar(1) NOT NULL,
	city varchar NULL,
	about_me varchar(200) NULL,
	interest varchar(200) NULL,
	photo_profile_url varchar(200) NULL,
	created_by varchar NOT NULL,
	created_date timestamp NOT NULL,
	last_modified_by varchar NOT NULL,
	last_modified_date timestamp NOT NULL,
	deleted bool NOT NULL DEFAULT false,
	CONSTRAINT profile_pkey PRIMARY KEY (id)
);