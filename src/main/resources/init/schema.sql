CREATE TABLE IF NOT EXISTS CUSTOMER (
    `id`        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '회원번호',
    `user_id`   VARCHAR(70)  NOT NULL UNIQUE COMMENT '회원아이디',
    `email`     VARCHAR(255) NOT NULL COMMENT '이메일',
    `password`  VARCHAR(255) NOT NULL COMMENT '비밀번호',
    `address`   VARCHAR(255) NOT NULL COMMENT '주소',
    `post_code` VARCHAR(255) NOT NULL COMMENT '우편번호',
    `role` ENUM('USER', 'ADMIN') NOT NULL COMMENT '역할'
);

CREATE TABLE IF NOT EXISTS `PRODUCT`
(
    `product_id` INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '상품번호',
    `name`       VARCHAR(255) NOT NULL COMMENT '상품이름',
    `price`      VARCHAR(255) NOT NULL COMMENT '상품가격',
    `info`       VARCHAR(255) NOT NULL COMMENT '상품정보',
    `brand`      VARCHAR(255) NOT NULL COMMENT '상품브랜드',
    `count`      INT               DEFAULT 10 COMMENT '상품재고',
    `sales`      INT               DEFAULT 0 COMMENT '상품판매량',
    `deleted_at` TIMESTAMP    NULL DEFAULT NULL COMMENT '삭제일시'
);

CREATE TABLE IF NOT EXISTS `PRODUCT_IMG`
(
    `image_id`           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '파일번호',
    `product_id`         INT          NOT NULL COMMENT '상품번호',
    `original_file_name` VARCHAR(255) NOT NULL COMMENT '원본파일명',
    `rename_file_name`   VARCHAR(255) NOT NULL COMMENT '저장파일명',
    `save_path`          VARCHAR(255) NOT NULL COMMENT '저장경로',
    `created_at`         TIMESTAMP    NULL     DEFAULT now() COMMENT '파일등록일자',
    `activated`          BOOLEAN      NOT NULL DEFAULT true COMMENT '활성여부',
    FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`product_id`)
);

CREATE TABLE IF NOT EXISTS `CART`
(
    `cart_id`      INT                  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '카트번호',
    `customer_id`  INT                  NOT NULL COMMENT '회원번호',
    `is_activated` BOOLEAN DEFAULT TRUE NOT NULL COMMENT '활성여부',
    FOREIGN KEY (`customer_id`) REFERENCES `CUSTOMER` (`id`)
);

CREATE TABLE IF NOT EXISTS `CART_PRODUCT`
(
    `cart_product_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '카트상품번호',
    `cart_id`         INT NOT NULL COMMENT '카트번호',
    `product_id`      INT NOT NULL COMMENT '상품번호',
    `count`           INT NOT NULL COMMENT '주문수량',
    FOREIGN KEY (`cart_id`) REFERENCES `CART` (`cart_id`),
    FOREIGN KEY (`product_id`) REFERENCES `PRODUCT` (`product_id`)
);

CREATE TABLE IF NOT EXISTS `ORDERED`
(
    `order_id`    INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '주문번호',
    `cart_id`     INT          NULL COMMENT '카트번호',
    `email`       VARCHAR(255) NOT NULL COMMENT '이메일',
    `is_customer` BOOLEAN      NOT NULL COMMENT '회원여부',
    `address`     VARCHAR(255) NOT NULL COMMENT '주소',
    `post_code`   VARCHAR(255) NOT NULL COMMENT '우편번호',
    `ordered_at`  TIMESTAMP    NULL DEFAULT now() COMMENT '주문일시',
    `total_price` INT          NOT NULL COMMENT '총가격',
    FOREIGN KEY (`cart_id`) REFERENCES CART (`cart_id`)
);

CREATE TABLE IF NOT EXISTS `ORDER_PRODUCT`
(
    `order_product_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '주문상품번호',
    `order_id`         INT NOT NULL COMMENT '주문번호',
    `product_id`       INT NOT NULL COMMENT '상품번호',
    `count`            INT NOT NULL COMMENT '주문수량',
    FOREIGN KEY (`order_id`) REFERENCES ORDERED (`order_id`),
    FOREIGN KEY (`product_id`) REFERENCES PRODUCT (`product_id`)
);

CREATE TABLE IF NOT EXISTS `DELIVERY`
(
    `delivery_id`  INT                                    NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '배송번호',
    `order_id`     INT                                    NOT NULL COMMENT '주문번호',
    `customer_id`  INT                                    NOT NULL COMMENT '회원번호',
    `status`       ENUM ('READY', 'SHIPPED', 'DELIVERED') NOT NULL COMMENT '주문상태',
    `delivered_at` TIMESTAMP                              NULL DEFAULT now() COMMENT '배송일시',
    FOREIGN KEY (`order_id`) REFERENCES ORDERED (`order_id`),
    FOREIGN KEY (`customer_id`) REFERENCES CUSTOMER(`id`)
);