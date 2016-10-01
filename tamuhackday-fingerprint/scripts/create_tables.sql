CREATE TABLE Account
(
uin varchar(30) PRIMARY KEY,
email varchar(255),
pass varchar(255),
token varchar(255),
creation_tome timestamp,
last_updated timestamp Default CURRENT_TIMESTAMP
);