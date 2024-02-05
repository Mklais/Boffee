USE bookclub_database;

-- Create user_read_books table
CREATE TABLE IF NOT EXISTS user_read_books (
    user_profile_id INT,
    book_id INT,
    PRIMARY KEY (user_profile_id, book_id),
    FOREIGN KEY (user_profile_id) REFERENCES user_profile(profile_id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);