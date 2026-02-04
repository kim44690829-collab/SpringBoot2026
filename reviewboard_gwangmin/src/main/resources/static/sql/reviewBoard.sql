CREATE TABLE reviewBoard(
	num INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    writer VARCHAR(10) NOT NULL, 
    rating TINYINT NOT NULL,
    content VARCHAR(1000) NOT NULL,
    reg_date DATETIME DEFAULT now(), 
    readCount INT DEFAULT 0
);
