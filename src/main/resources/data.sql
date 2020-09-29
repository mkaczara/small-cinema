-- Movies
INSERT INTO movie (id, title, imdb_id) values (1, 'The Fast and the Furious', 'tt0232500');
INSERT INTO movie (id, title, imdb_id) values (2, '2 Fast 2 Furious', 'tt0322259');
INSERT INTO movie (id, title, imdb_id) values (3, 'The Fast and the Furious: Tokyo Drift', 'tt0463985');
INSERT INTO movie (id, title, imdb_id) values (4, 'Fast & Furious', 'tt1013752');
INSERT INTO movie (id, title, imdb_id) values (5, 'Fast Five', 'tt1596343');
INSERT INTO movie (id, title, imdb_id) values (6, 'Fast & Furious 6', 'tt1905041');
INSERT INTO movie (id, title, imdb_id) values (7, 'Furious 7', 'tt2820852');
INSERT INTO movie (id, title, imdb_id) values (8, 'The Fate of the Furious', 'tt4630562');

-- Adjust sequences
ALTER SEQUENCE movie_id_seq RESTART WITH 9;
