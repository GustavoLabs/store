CREATE DATABASE IF NOT EXISTS spring_project;

CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `discount` decimal(2,2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) UNIQUE NOT NULL,
  `name` varchar(255)  NOT NULL,
  `login` varchar(30) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `cart_id` bigint(20) unsigned NOT null,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`cart_id`) REFERENCES `cart`(`id`),
  CONSTRAINT UNIQUE_CART_ID UNIQUE (cart_id)
);

CREATE TABLE IF NOT EXISTS `cart_products` (
  `cart_id` bigint(20) unsigned NOT null,
  `product_id` bigint(20) unsigned NOT null,
  `quantity` int(4) NOT NULL,
  FOREIGN KEY (`cart_id`) REFERENCES `cart`(`id`),
  FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
  PRIMARY KEY (cart_id, product_id )
);

CREATE TABLE IF NOT EXISTS `order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `total_price` decimal(10,2) NOT NULL,
  `date` datetime(6) NOT NULL,
  `user_id` bigint(20) unsigned NOT null,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);

CREATE TABLE IF NOT EXISTS `order_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);