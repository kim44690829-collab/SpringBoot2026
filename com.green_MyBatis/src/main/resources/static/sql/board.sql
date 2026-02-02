CREATE TABLE board(
	num INT AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(20),
    subject VARCHAR(30),
    writerPw VARCHAR(20),
    reg_date DATETIME DEFAULT now(),
    readcount INT DEFAULT 0,
    content VARCHAR(1000),
    id VARCHAR(20)
);