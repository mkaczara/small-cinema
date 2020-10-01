-- Movies
INSERT INTO movie (id, title, imdb_id) values (1, 'The Fast and the Furious', 'tt0232500');
INSERT INTO movie (id, title, imdb_id) values (2, '2 Fast 2 Furious', 'tt0322259');
INSERT INTO movie (id, title, imdb_id) values (3, 'The Fast and the Furious: Tokyo Drift', 'tt0463985');
INSERT INTO movie (id, title, imdb_id) values (4, 'Fast & Furious', 'tt1013752');
INSERT INTO movie (id, title, imdb_id) values (5, 'Fast Five', 'tt1596343');
INSERT INTO movie (id, title, imdb_id) values (6, 'Fast & Furious 6', 'tt1905041');
INSERT INTO movie (id, title, imdb_id) values (7, 'Furious 7', 'tt2820852');
INSERT INTO movie (id, title, imdb_id) values (8, 'The Fate of the Furious', 'tt4630562');

-- Reviews
INSERT INTO review (id, movie_id, rating) values (1, 1, 5);
INSERT INTO review (id, movie_id, rating) values (2, 1, 4);
INSERT INTO review (id, movie_id, rating) values (3, 1, 5);
INSERT INTO review (id, movie_id, rating) values (4, 2, 5);
INSERT INTO review (id, movie_id, rating) values (5, 2, 3);
INSERT INTO review (id, movie_id, rating) values (6, 3, 4);
INSERT INTO review (id, movie_id, rating) values (7, 5, 3);
INSERT INTO review (id, movie_id, rating) values (8, 7, 4);

-- Schedules
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (1, 1, 1, '12:00', 25.5, 15.0);
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (2, 1, 2, '12:00', 25.5, 15.0);
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (3, 1, 5, '12:00', 30.0, 20.0);
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (4, 2, 3, '12:00', 25.5, 15.0);
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (5, 2, 4, '12:00', 25.5, 15.0);
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (6, 3, 6, '12:00', 30.0, 20.0);
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (7, 5, 7, '12:00', 30.0, 20.0);
INSERT INTO schedule (id, movie_id, day_of_week, time, normal_price, discount_price) values (8, 8, 1, '16:00', 25.5, 15.0);

-- Adjust sequences
ALTER SEQUENCE movie_id_seq RESTART WITH 9;
ALTER SEQUENCE review_id_seq RESTART WITH 9;
ALTER SEQUENCE schedule_id_seq RESTART WITH 9;