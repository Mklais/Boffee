USE bookclub_database;

DROP TABLE IF EXISTS book;

-- Create the book table
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INT,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
        REFERENCES author(id)
        ON DELETE CASCADE -- Cascade delete to delete associated reviews
);

-- Create an index on author_id
CREATE INDEX idx_author ON book (author_id);
