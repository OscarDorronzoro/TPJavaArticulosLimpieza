CREATE USER 'csadmin'@localhost IDENTIFIED BY 'fake_password';
SOURCE ./DB/backup;
GRANT insert, update, delete, select ON cleaning_supplies.* TO csadmin@localhost;
