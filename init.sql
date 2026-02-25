CREATE DATABASE IF NOT EXISTS employeesdb;

-- usuario para conexiones externas (%)
CREATE USER IF NOT EXISTS 'appuser'@'%' IDENTIFIED WITH caching_sha2_password BY 'app123';
GRANT ALL PRIVILEGES ON employeesdb.* TO 'appuser'@'%';

-- usuario para conexiones internas (localhost)
CREATE USER IF NOT EXISTS 'appuser'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'app123';
GRANT ALL PRIVILEGES ON employeesdb.* TO 'appuser'@'localhost';

FLUSH PRIVILEGES;