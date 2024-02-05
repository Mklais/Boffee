USE bookclub_database;

-- Insert data into the friendship table
INSERT INTO friendship (`user1_id`, `user2_id`, `status`)
VALUES
(1, 2, 'PENDING'),   -- Laura and Leslie, pending
(1, 3, 'ACCEPTED'),  -- Laura and Mary, accepted
(1, 4, 'PENDING'),   -- Laura and John, pending
(2, 3, 'PENDING'),   -- Leslie and Mary, pending
(2, 4, 'ACCEPTED'),  -- Leslie and John, accepted
(3, 4, 'PENDING'),   -- Mary and John, pending
(4, 5, 'ACCEPTED'),  -- John and Alice, accepted
(5, 6, 'PENDING'),   -- Alice and Bob, pending
(5, 7, 'ACCEPTED'),  -- Alice and Eva, accepted
(6, 7, 'PENDING'),   -- Bob and Eva, pending
(7, 8, 'PENDING'),   -- Eva and Michael, pending
(2, 5, 'PENDING'),   -- Leslie and Alice, pending
(3, 5, 'PENDING'),   -- Mary and Alice, pending
(4, 6, 'PENDING'),   -- John and Bob, pending
(6, 8, 'PENDING');   -- Bob and Michael, pending
