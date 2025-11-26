-- docker-entrypoint-initdb.d/init.sql
CREATE DATABASE IF NOT EXISTS `hibernate` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'hibernate_user'@'%' IDENTIFIED BY 'hibernate_pass_here';
GRANT ALL PRIVILEGES ON `hibernate`.* TO 'hibernate_user'@'%';
FLUSH PRIVILEGES;
