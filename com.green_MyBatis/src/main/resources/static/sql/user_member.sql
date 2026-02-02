-- DB(스키마 이름) : springBootDB

CREATE DATABASE springBootDB;

USE springBootDB;

-- 테이블 이름 : user_member
CREATE TABLE user_member(
	no INT AUTO_INCREMENT PRIMARY KEY,
    id VARCHAR(20) NOT NULL UNIQUE,
    pw VARCHAR(100) NOT NULL,
    mail VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    reg_date DATETIME DEFAULT now(), -- user의 등록일
    mod_date DATETIME DEFAULT now() -- user의 수정일
);