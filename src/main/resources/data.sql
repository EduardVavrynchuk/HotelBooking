INSERT INTO hotel_user (username) VALUES
('John'),
('Michael'),
('Ivan'),
('Kate'),
('Lina'),
('Arthur'),
('Vlad'),
('Michael');

INSERT INTO category_room (id, name) VALUES
(1, 'Single'),
(2, 'Double'),
(3, 'Triple'),
(4, 'Quad'),
(5, 'Queen'),
(6, 'King'),
(7, 'Twin');

INSERT INTO additional_options (id, name, price) VALUES
(1, 'Breakfast', 90),
(2, 'Cleaning', 50);

INSERT INTO room (id, number, price, category_id) VALUES
(1, 101, 1000, 1),
(2, 102, 1300, 2),
(3, 103, 1600, 3),
(4, 104, 2000, 4),
(5, 105, 2500, 5),
(6, 106, 2500, 6),
(7, 107, 900, 7),
(8, 108, 1000, 1),
(9, 109, 1300, 2);

INSERT INTO booking (id, end_date, start_date, room_id, user_id) VALUES
(1, '2019-04-03', '2019-04-01', 2, 1),
(2, '2019-04-05', '2019-04-03', 3, 2),
(3, '2019-04-07', '2019-04-05', 4, 3);

INSERT INTO booking_additional_options (booking_id, additional_options_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 1);