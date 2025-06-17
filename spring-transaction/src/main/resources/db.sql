CREATE TABLE account (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(50) NOT NULL,
                         balance DECIMAL(10, 2) NOT NULL
);

INSERT INTO account(name, balance) VALUES ('Alice', 1000.00), ('Bob', 1000.00);
