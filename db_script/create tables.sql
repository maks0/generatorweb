DROP TABLE IF EXISTS spectrum CASCADE;
DROP TABLE IF EXISTS experiment CASCADE;
DROP TABLE IF EXISTS measurement_device CASCADE;
DROP TABLE IF EXISTS spectrum_site_user CASCADE;

create table measurement_device (
id serial primary key,
model varchar(100) not null,
serial_number varchar(100)
);

create table experiment (
	id serial primary key,
	begin_time timestamp not null,
	comments varchar(250),
	device int,
	constraint exp_fk_dev FOREIGN key (device)
	REFERENCES measurement_device (id),
	constraint unique_exp UNIQUE (begin_time, comments, device)
);

create table spectrum (
	id bigserial primary key,
	frequency double precision not null,
	magnitude double precision not null,
	experiment int,
	constraint spectrum_fk_exp FOREIGN key (experiment)
		REFERENCES experiment (id),
	constraint unique_feq_and_exp UNIQUE (frequency, experiment)
);

create table spectrum_site_user (
	email varchar(100) primary key,
	password varchar(100) not null,
	username varchar(50) not null,
	role integer
);

insert into spectrum_site_user (email, password, username, role) 
	values ('maksbrunarskiy@gmail.com', 'maks', 'maksimus', 1);


