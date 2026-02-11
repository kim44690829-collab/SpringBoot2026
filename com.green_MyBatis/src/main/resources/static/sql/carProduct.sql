create table carProduct(
no int auto_increment primary key, -- 자동차 식별자
carName varchar(20), -- 자동차 이름
price int, -- 자동차 가격
company varchar(50), -- 자동차 회사
img varchar(50), -- 자동차 이미지
info varchar(500) -- 자동차 설명
);

INSERT INTO carProduct (carName, price, company, img, info) VALUES
('Veyron', 2000000000, 'Bugatti', '1.jpg', '["피트니스","레스토랑","편의점","라운지","엘리베이터","비즈니스센터"]'),
('NSX', 180000000, 'Acura', '2.jpg', '["피트니스1","레스토랑1","편의점1","라운지1","엘리베이터1","비즈니스센터1"]'),
('Fortwo', 25000000, 'Smart', '3.jpg', '초소형 도심용 차량입니다.'),
('Pony', 15000000, 'Hyundai', '4.jpg', '현대자동차의 첫 모델입니다.'),
('Concept X', 500000000, 'Concept', '5.jpg', '미래형 콘셉트카입니다.'),
('Mini', 42000000, 'MINI', '6.jpg', '경쾌한 주행의 소형차입니다.'),
('Sonata', 35000000, 'Hyundai', '7.jpg', '편안한 중형 세단입니다.'),
('Avante', 28000000, 'Hyundai', '8.jpg', '인기 준중형 세단입니다.'),
('Ioniq5', 55000000, 'Hyundai', '9.jpg', '전기차 전용 모델입니다.'),
('Concept EV', 600000000, 'Future', '10.jpg', '미래형 전기차입니다.'),
('A4', 60000000, 'Audi', '11.jpg', '프리미엄 중형 세단입니다.'),
('K3', 26000000, 'Kia', '12.jpg', '가성비 좋은 세단입니다.'),
('Corvette', 150000000, 'Chevrolet', '13.jpg', '미국 스포츠카입니다.'),
('Mustang', 120000000, 'Ford', '14.jpg', '머슬카의 상징입니다.'),
('Seltos', 32000000, 'Kia', '15.jpg', '소형 SUV 모델입니다.'),
('Grandeur', 45000000, 'Hyundai', '16.jpg', '준대형 세단입니다.'),
('GHybrid', 50000000, 'Hyundai', '17.jpg', '하이브리드 세단입니다.'),
('G80', 90000000, 'Genesis', '18.jpg', '프리미엄 세단입니다.'),
('Muscle', 80000000, 'Classic', '19.jpg', '클래식 머슬카입니다.'),
('Coupe', 70000000, 'Retro', '20.jpg', '레트로 감성 차량입니다.');
