USE bookclub_database;

-- Create the book_genre table with ON DELETE CASCADE
CREATE TABLE IF NOT EXISTS book_genre (
    book_id INT,
    genre_id INT,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre (id) ON DELETE CASCADE
);