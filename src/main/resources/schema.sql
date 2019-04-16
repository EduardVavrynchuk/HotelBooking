create table additional_options
(
	id bigint not null
		constraint additional_options_pkey
			primary key,
	name varchar(255),
	price double precision
);
create table category_room
(
	id bigint not null
		constraint category_room_pkey
			primary key,
	name varchar(255)
);

create table hotel_user
(
	id bigint not null
		constraint hotel_user_pkey
			primary key,
	username varchar(255)
);

create table room
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

create table booking
(
	id bigint not null
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

create table booking_additional_options
(
	booking_id bigint not null
		constraint fkj01r8i4ispq32r3v2pwh8y481
			references booking,
	additional_options_id bigint not null
		constraint uk_4b8u2h1rfmlnx2svc8kikr9vy
			unique
		constraint fk65rcj7bpo1wb3hj9j7llo4lvg
			references additional_options
);

