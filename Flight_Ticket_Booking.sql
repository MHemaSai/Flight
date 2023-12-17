CREATE DATABASE ticket_1;
USE ticket_1;

CREATE TABLE IF NOT EXISTS sign_up(
    user_Id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255),
    user_name VARCHAR(255),
    user_password VARCHAR(255),
    phone_number VARCHAR(255),
    is_admin BOOLEAN
);



CREATE TABLE IF NOT EXISTS admin_1(
    admin_Id INT AUTO_INCREMENT PRIMARY KEY,
    admin_name VARCHAR(255) NOT NULL,
    admin_password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS flight_1 (
    flight_Id INT AUTO_INCREMENT PRIMARY KEY,
    flight_Name VARCHAR(255),
    flight_Number VARCHAR(255),
    date DATE NOT NULL,
    seats INT DEFAULT 60,
    available_seats INT DEFAULT 60
);


CREATE TABLE IF NOT EXISTS user_1
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255),
    user_password VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS booking_1(
    booking_Id INT AUTO_INCREMENT PRIMARY KEY,
    flight_Id INT,
    flight_Name VARCHAR(255),
    available_seats INT
);


INSERT INTO admin_1 (admin_name, admin_password) VALUES ('admin', 'admin');

INSERT INTO user_1 (user_name, user_password)
SELECT user_name, user_password FROM sign_up;

SELECT * FROM flight_1;

SELECT Id, user_name,REPEAT('*', LENGTH(user_password)) user_password FROM user_1;

SELECT user_Id, user_email, user_name, REPEAT('*', LENGTH(user_password)) AS user_password, phone_number, is_admin FROM sign_up;

SELECT * from booking_1;

drop table user_1;

truncate sign_up;

truncate flight_1;