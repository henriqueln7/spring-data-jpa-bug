## BUG in Spring Data JPA

This is a repository to show a possible bug in Spring Data JPA. The main purpose here is to share this with the Spring Data JPA maintainers.

### Setup

Please run these commands to put some data locally:

```sql
CREATE TABLE User (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    kind VARCHAR(255) DEFAULT 'REGULAR'
);


DELIMITER $$

CREATE PROCEDURE InsertFakeUsers()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 30 DO
            INSERT INTO User (username, email, name, kind)
            VALUES (CONCAT('user', i), CONCAT('user', i, '@gmail.com', 'SUPER'), CONCAT('User ', i));
            SET i = i + 1;
        END WHILE;
END$$

DELIMITER ;

CALL InsertFakeUsers();

UPDATE User SET kind = 'SUPER' WHERE id % 2 = 0;
UPDATE User SET kind = 'ADMIN' WHERE id % 3 = 0;
```

Now run the application with `mvn spring-boot:run` 
Do a GET method into `http://localhost:8081/users?kind=REGULAR` and see the exception in your console.