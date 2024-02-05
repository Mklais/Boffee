USE bookclub_database;

-- Insert data into Reviewer table
INSERT INTO Reviewer (first_name, last_name)
VALUES
('Alice', 'Anderson'),
('Bob', 'Brown'),
('Charlie', 'Clark'),
('David', 'Davis'),
('Eva', 'Evans'),
('Frank', 'Fisher'),
('Grace', 'Gibson'),
('Henry', 'Hill'),
('Ivy', 'Irwin'),
('Jack', 'Jones');

-- Insert data into Review table
INSERT INTO Review (comment, reviewer_id, book_id)
VALUES
('Great book, loved the plot!', 1, 1),
('Interesting characters, couldn''t put it down.', 2, 2),
('Not my favorite, but still a good read.', 3, 3),
('Highly recommend, especially for sci-fi fans.', 4, 4),
('Well-written and thought-provoking.', 5, 5),
('A thrilling page-turner!', 6, 6),
('Captivating story with unexpected twists.', 7, 7),
('The characters felt so real, couldn''t stop reading.', 8, 8),
('Amazing journey through time and space.', 9, 9),
('Beautifully written, left me wanting more.', 10, 10);