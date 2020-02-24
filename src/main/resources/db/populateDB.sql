DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
(100000, '2020-02-22 08:00','breakfast', 1000),
(100000, '2020-02-22 10:30','brunch', 600),
(100000, '2020-02-23 13:00','lunch', 700),
(100000, '2020-02-23 20:20','dinner', 350),
(100000, '2020-02-24 23:10','evening meal', 200),
(100001, '2020-02-22 09:10','breakfast', 1000),
(100001, '2020-02-23 11:30','brunch', 800),
(100001, '2020-02-24 13:20','lunch', 500),
(100001, '2020-02-24 23:30','evening meal', 100);