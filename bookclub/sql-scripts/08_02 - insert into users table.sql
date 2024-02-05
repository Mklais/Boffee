USE bookclub_database;

-- Insert data into the Users table
INSERT INTO users (`profile_id`, `username`, `password`, `active`, `activity_points`, `first_name`, `last_name`, `email`, `emoji`)
VALUES
(1, 'Laura', '{noop}test123', 1, 150, 'Laura', 'Klais', 'laura@boffee.com', '1'),
(2, 'Leslie', '{noop}test123', 1, 120, 'Leslie', 'Jones', 'leslie@boffee.com', '2'),
(3, 'Mary', '{noop}test123', 1, 100, 'Mary', 'Johnson', 'mary@boffee.com', '3'),
(4, 'John', '{noop}test123', 1, 180, 'John', 'Doe', 'john@boffee.com', '4'),
(5, 'Alice', '{noop}test123', 1, 200, 'Alice', 'Smith', 'alice@boffee.com', '5'),
(6, 'Bob', '{noop}test123', 1, 90, 'Bob', 'Johnson', 'bob@boffee.com', '6'),
(7, 'Eva', '{noop}test123', 1, 130, 'Eva', 'Brown', 'eva@boffee.com', '7'),
(8, 'Michael', '{noop}test123', 1, 160, 'Michael', 'Miller', 'michael@boffee.com', '8');