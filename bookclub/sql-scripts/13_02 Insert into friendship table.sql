USE bookclub_database;

-- Insert data into the Friendship table
INSERT INTO friendship (`sender_id`, `receiver_id`, `status`, `sent_at`, `responded_at`)
VALUES
(1, 2, 'ACCEPTED', '2024-02-01 08:00:00', '2024-02-02 09:00:00'),
(1, 3, 'PENDING', '2024-02-03 10:00:00', NULL),
(2, 4, 'ACCEPTED', '2024-02-04 11:00:00', '2024-02-05 12:00:00'),
(4, 5, 'ACCEPTED', '2024-02-06 13:00:00', '2024-02-07 14:00:00'),
(5, 1, 'PENDING', '2024-02-08 15:00:00', NULL),
(3, 6, 'DECLINED', '2024-02-09 16:00:00', '2024-02-10 17:00:00'),
(7, 8, 'ACCEPTED', '2024-02-11 18:00:00', '2024-02-12 19:00:00'),
(8, 2, 'PENDING', '2024-02-13 20:00:00', NULL);
