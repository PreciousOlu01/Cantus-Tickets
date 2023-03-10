--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables
DROP TABLE tickets IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE event IF EXISTS;


CREATE TABLE users(user_id int PRIMARY KEY auto_increment, username varchar(255) unique,
    password varchar(100));
CREATE TABLE event(event_id int PRIMARY KEY auto_increment, event_name varchar(255), price float, ticket_limit int,
    event_date DATE,
    event_time TIME);
CREATE TABLE tickets(tickets_id int PRIMARY KEY auto_increment, user_fk int NOT NULL, events_fk int NOT NULL,
 FOREIGN KEY (user_fk) references users(user_id), FOREIGN KEY (events_fk) references event(event_id));
