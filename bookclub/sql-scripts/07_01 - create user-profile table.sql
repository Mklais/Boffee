USE bookclub_database;

-- Create user_profile table
CREATE TABLE IF NOT EXISTS user_profile (
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    hobbies VARCHAR(255),
    interests VARCHAR(255),
    birthdate DATE,
    favorite_genre_id INT,
    favorite_author_id INT,
    FOREIGN KEY (favorite_genre_id) REFERENCES genre(id) ON DELETE SET NULL,
    FOREIGN KEY (favorite_author_id) REFERENCES author(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
