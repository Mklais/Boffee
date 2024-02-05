USE bookclub_database;

-- Insert data into user_profile table
INSERT INTO user_profile (hobbies, interests, birthdate, favorite_genre_id, favorite_author_id)
VALUES
('Reading', 'Technology', '1990-01-15', 1, 1),
('Traveling', 'Music', '1985-05-20', 2, 2),
('Cooking', 'Sports', '1992-11-10', 3, 3),
('Gaming', 'Movies', '1988-07-25', 4, 4),
('Photography', 'Art', '1980-03-12', 5, 5),
('Hiking', 'Science', '1995-09-18', 6, 6),
('Painting', 'Nature', '1982-04-05', 1, 1),
('Cycling', 'Reading', '1998-12-30', 2, 2);