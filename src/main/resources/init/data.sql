INSERT INTO ADMIN (id, password)
VALUES ('super', '12345678');

INSERT INTO CUSTOMER (user_id, email, password, address, post_code)
VALUES ('user01', 'user01@example.com', 'pass123', '서울 강남구 테헤란로 123', '06234');

INSERT INTO CUSTOMER (user_id, email, password, address, post_code)
VALUES ('user02', 'user02@example.com', 'pass456', '부산 해운대구 우동 456', '48095');


INSERT INTO CART (user_id, is_activated)
VALUES ('user01', 'Y');

INSERT INTO CART (user_id, is_activated)
VALUES ('user02', 'Y');

INSERT INTO CART_PRODUCT (cart_id, product_id, count)
VALUES (1, 1, 2);  -- user01, 에스프레소 블렌드 2개

INSERT INTO CART_PRODUCT (cart_id, product_id, count)
VALUES (1, 3, 1);  -- user01, 드립백 세트 1개

INSERT INTO CART_PRODUCT (cart_id, product_id, count)
VALUES (2, 2, 1);  -- user02, 콜드브루 원액 1개

INSERT INTO ORDERED (cart_id, email, is_customer, address, post_code)
VALUES (1, 'user01@example.com', TRUE, '서울 강남구 테헤란로 123', 6234);

INSERT INTO ORDERED (cart_id, email, is_customer, address, post_code)
VALUES (2, 'guest@example.com', FALSE, '대구 중구 중앙대로 112', 41933);

INSERT INTO ORDER_PRODUCT (order_id, product_id, count)
VALUES (1, 1, 2);  -- 에스프레소 블렌드 2개

INSERT INTO ORDER_PRODUCT (order_id, product_id, count)
VALUES (1, 3, 1);  -- 드립백 세트 1개

INSERT INTO ORDER_PRODUCT (order_id, product_id, count)
VALUES (2, 2, 1);  -- 콜드브루 원액 1개

INSERT INTO DELIVERY (order_id, status)
VALUES (1, 'SHIPPED');

INSERT INTO DELIVERY (order_id, status)
VALUES (2, 'READY');
-- PRODUCT
INSERT INTO PRODUCT (name, price, info, brand, count, sales) VALUES
                                                                        ('에스프레소 블렌드', '15000', '진한 풍미의 커피', 'CoffeeBrand', 100, 20),
                                                                        ('드립백 세트', '18000', '간편한 드립백', 'DripMaster', 80, 15),
                                                                        ('콜드브루 원액', '20000', '진한 콜드브루', 'CoolBrew', 60, 10);

