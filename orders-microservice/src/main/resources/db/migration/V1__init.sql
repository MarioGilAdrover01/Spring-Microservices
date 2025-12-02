CREATE TABLE t_orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_number VARCHAR(255) DEFAULT NULL,
    sku_code VARCHAR(255),
    price DECIMAL(10, 2),
    quantity INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);