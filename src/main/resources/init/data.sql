-- ADMIN
INSERT INTO ADMIN (id, password)
VALUES ('super', 'superpassword');

-- CUSTOMER
INSERT INTO CUSTOMER (user_id, email, password, address, post_code)
VALUES ('user01', 'user01@example.com', 'pass01', '서울 강남구 테헤란로 123', '12345'),
       ('user02', 'user02@example.com', 'pass02', '부산 해운대구 A로 456', '67890'),
       ('user03', 'user03@example.com', 'pass03', '대구 달서구 B길 789', '11223');

-- PRODUCT
INSERT INTO PRODUCT (name, image, price, info, brand, count, sales)
VALUES ('에스프레소 블렌드', 'espresso.jpg', '15000', '진한 풍미의 커피', 'CoffeeBrand', 100, 20),
       ('드립백 세트', 'drip.jpg', '18000', '간편한 드립백', 'DripMaster', 80, 15),
       ('콜드브루 원액', 'coldbrew.jpg', '2000    0', '진한 콜드브루', 'CoolBrew', 60, 10);

-- CART
INSERT INTO CART (customer_id, is_activated)
VALUES (1, true),
       (2, true),
       (3, true);

-- CART_PRODUCT
INSERT INTO CART_PRODUCT (cart_id, product_id, count)
VALUES (1, 1, 2),
       (1, 2, 1),
       (2, 3, 1),
       (3, 2, 3);

-- ORDERED
INSERT INTO ORDERED (cart_id, email, is_customer, address, post_code, ordered_at, total_price)
VALUES (1, 'user01@example.com', true, '서울 강남구 테헤란로 123', 12345, '2025-04-20 10:00:00', 48000),
       (2, 'user02@example.com', true, '부산 해운대구 A로 456', 67890, '2025-04-21 11:00:00', 20000),
       (3, 'user03@example.com', true, '대구 달서구 B길 789', 11223, '2025-04-22 12:00:00', 54000),
       (1, 'user01@example.com', true, '서울 강남구 테헤란로 123', 12345, '2025-04-23 09:00:00', 18000),
       (2, 'user02@example.com', true, '부산 해운대구 A로 456', 67890, '2025-04-23 15:30:00', 15000);

-- ORDER_PRODUCT
INSERT INTO ORDER_PRODUCT (order_id, product_id, count)
VALUES (1, 1, '2'),
       (1, 2, '1'),
       (2, 3, '1'),
       (3, 2, '3'),
       (4, 2, '1'),
       (5, 1, '1');

-- DELIVERY
INSERT INTO DELIVERY (order_id, status, delivered_at)
VALUES (1, 'SHIPPED', '2025-04-21 14:00:00'),
       (2, 'DELIVERED', '2025-04-22 17:00:00'),
       (3, 'READY', NULL),
       (4, 'READY', NULL),
       (5, 'SHIPPED', '2025-04-23 18:00:00');
