drop table if exists booking_additional_options;
drop table if exists booking;
drop table if exists additional_options;
drop table if exists room;
drop table if exists category_room;
drop table if exists hotel_user;

drop sequence if exists hotel_sequence;
drop sequence if exists booking_sequence;

create sequence if not exists hotel_sequence;
create sequence if not exists booking_sequence;

create table if not exists additional_options
(
	id bigint not null
		constraint additional_options_pkey
			primary key,
	name varchar(255),
	price double precision
);

create table if not exists category_room
(
	id bigint not null
		constraint category_room_pkey
			primary key,
	name varchar(255)
);

create table if not exists hotel_user
(
	id bigint not null default nextval ('hotel_sequence')
		constraint hotel_user_pkey
			primary key,
	username varchar(255)
);

create table if not exists room
(
	id bigint not null
		constraint room_pkey
			primary key,
	number integer,
	price double precision,
	category_id bigint
		constraint fk9clfnqxs6rk35vxgtvj7hgmtw
			references category_room
);

create table if not exists booking
(
	id bigint not null default nextval ('booking_sequence')
		constraint booking_pkey
			primary key,
	end_date date,
	start_date date,
	room_id bigint
		constraint fkq83pan5xy2a6rn0qsl9bckqai
			references room,
	user_id bigint
		constraint fkelw5lin4w8mqw80jv2q7hj03
			references hotel_user
);

create table if not exists booking_additional_options
(
	booking_id bigint not null
		constraint fkj01r8i4ispq32r3v2pwh8y481
			references booking,
	additional_options_id bigint not null
		constraint fk65rcj7bpo1wb3hj9j7llo4lvg
			references additional_options,
	constraint booking_additional_options_pkey
		primary key (booking_id, additional_options_id)
);