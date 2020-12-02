CREATE DATABASE eshop1;
USE eshop1;

CREATE TABLE `products` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
`name` VARCHAR (40), 
`price` DECIMAL(10,3) NOT NULL, 
`quantity` INT NOT NULL);
INSERT INTO `eshop1`.`products` (`name`, `price`, `quantity`) VALUES ('Smart Watch', '100', '1');


CREATE TABLE `customers` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
`first_name` VARCHAR (40) NOT NULL, 
`last_name` VARCHAR (40) NOT NULL,  
`tel` VARCHAR (40) NOT NULL, 
`email` VARCHAR (40) NOT NULL);
INSERT INTO `eshop1`.`customers` (`first_name`, `last_name`, `tel`, `email`) VALUES ('George', 'Pasparakis', '210111', 'paspa@hotmail.com');

-- id, date, products_id, customers_id, quantity, total_price
CREATE TABLE `orders` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
`date` datetime NOT NULL,
`products_id` INT,
`customers_id` INT,
`quantity` INT(3),
`total_price` DECIMAL(10,3) NOT NULL,
CONSTRAINT `fk_orders_products_id__products_id` FOREIGN KEY(`products_id`) REFERENCES `products`(`id`),
CONSTRAINT `fk_orders_customers_id__customers_id` FOREIGN KEY(`customers_id`) REFERENCES `customers`(`id`)
);
INSERT INTO `eshop1`.`orders` (`date`, `products_id`, `customers_id`, `quantity`, `total_price`) VALUES ('2020-2-1', '1', '1', '1', '100');


