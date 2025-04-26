-- CUSTOMER
INSERT INTO CUSTOMER (user_id, email, password, address, post_code, role) VALUES
                                                                 ('user01', 'user01@example.com', '{noop}pass01', '서울 강남구 테헤란로 123', '12345', 'ROLE_USER'),
                                                                 ('user02', 'user02@example.com', '{noop}pass02', '부산 해운대구 A로 456', '67890', 'ROLE_USER'),
                                                                 ('user03', 'user03@example.com', '{noop}pass03', '대구 달서구 B길 789', '11223', 'ROLE_USER'),
                                                                 ('super', 'super@example.com', '{noop}superpassword', '대구 달서구 B길 789', '11223', 'ROLE_ADMIN');

-- PRODUCT
INSERT INTO PRODUCT (name, price, info, brand, count, sales) VALUES
                                                                 ('에스프레소 블렌드', '15000', '진한 풍미의 커피', 'CoffeeBrand', 100, 20),
                                                                 ('드립백 세트', '18000', '간편한 드립백', 'DripMaster', 80, 15),
                                                                 ('콜드브루 원액', '20000', '진한 콜드브루', 'CoolBrew', 60, 10);

-- PRODUCT_IMG
INSERT INTO PRODUCT_IMG (product_id, original_file_name, rename_file_name, save_path, created_at, activated) VALUES
                                                                 (1, 'espresso.jpg', 'espresso_123.jpg', '/images/products', now(), true),
                                                                 (2, 'drip.jpg', 'drip_456.jpg', '/images/products', now(), true),
                                                                 (3, 'coldbrew.jpg', 'coldbrew_789.jpg', '/images/products', now(), true);

-- CART
INSERT INTO CART (customer_id, is_activated) VALUES
                                                                (1, true),
                                                                (2, true),
                                                                (3, true);

-- CART_PRODUCT
INSERT INTO CART_PRODUCT (cart_id, product_id, count) VALUES
                                                                (1, 1, 2),
                                                                (1, 2, 1),
                                                                (2, 3, 1),
                                                                (3, 2, 3);

-- ORDERED
INSERT INTO ORDERED (cart_id, email, is_customer, address, post_code, ordered_at, total_price) VALUES
                                                                (1, 'user01@example.com', true, '서울 강남구 테헤란로 123', 12345, '2025-04-20 10:00:00', 48000),
                                                                (2, 'user02@example.com', true, '부산 해운대구 A로 456', 67890, '2025-04-21 11:00:00', 20000),
                                                                (3, 'user03@example.com', true, '대구 달서구 B길 789', 11223, '2025-04-22 12:00:00', 54000),
                                                                (1, 'user01@example.com', true, '서울 강남구 테헤란로 123', 12345, '2025-04-23 09:00:00', 18000),
                                                                (2, 'user02@example.com', true, '부산 해운대구 A로 456', 67890, '2025-04-23 15:30:00', 15000);

-- ORDER_PRODUCT
INSERT INTO ORDER_PRODUCT (order_id, product_id, count) VALUES
                                                                (1, 1, 2),
                                                                (1, 2, 1),
                                                                (2, 3, 1),
                                                                (3, 2, 3),
                                                                (4, 2, 1),
                                                                (5, 1, 1);

-- DELIVERY
INSERT INTO DELIVERY (order_id, customer_id, status, delivered_at) VALUES
                                                                (1, 1, 'SHIPPED', '2025-04-21 14:00:00'),
                                                                (2, 2, 'DELIVERED', '2025-04-22 17:00:00'),
                                                                (3, 3, 'READY', NULL),
                                                                (4, 1, 'READY', NULL),
                                                                (5, 2, 'SHIPPED', '2025-04-23 18:00:00');
