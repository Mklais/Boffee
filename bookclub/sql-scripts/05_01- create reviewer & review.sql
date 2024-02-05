USE bookclub_database;

-- Drop tables if they exist
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS reviewer;

-- Create Reviewer table
CREATE TABLE Reviewer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

-- Create Review table
CREATE TABLE Review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    comment TEXT,
    reviewer_id INT,
    book_id INT,
    FOREIGN KEY (reviewer_id) REFERENCES reviewer(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);
