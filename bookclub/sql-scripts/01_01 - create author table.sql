USE bookclub_database;

-- Drop Author table if it exists
DROP TABLE IF EXISTS author;

-- Create Author table
CREATE TABLE IF NOT EXISTS author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);
