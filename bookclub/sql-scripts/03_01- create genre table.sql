USE bookclub_database;

-- Drop Genre table if it exists
DROP TABLE IF EXISTS genre;

-- Create table for Genre with ON DELETE CASCADE
CREATE TABLE Genre (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);