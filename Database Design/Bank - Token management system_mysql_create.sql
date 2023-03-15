CREATE TABLE `Customer` (
	`customer_id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	`service_id` INT,
	`mobile_number` INT UNIQUE,
	PRIMARY KEY (`customer_id`)
);

CREATE TABLE `Token` (
	`token_id` INT NOT NULL AUTO_INCREMENT,
	`customer_id` INT NOT NULL,
	`status` VARCHAR(255) NOT NULL,
	`generation_time` TIME NOT NULL,
	`expected_time` TIME NOT NULL,
	`service_id` INT NOT NULL,
	`frequency_of_calling` INT NOT NULL,
	PRIMARY KEY (`token_id`)
);

CREATE TABLE `Counter` (
	`counter_id` INT NOT NULL AUTO_INCREMENT,
	`counter_number` INT NOT NULL UNIQUE,
	PRIMARY KEY (`counter_id`)
);

CREATE TABLE `Service` (
	`service_id` INT NOT NULL AUTO_INCREMENT,
	`counter_id` INT NOT NULL,
	`type_of_service` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`service_id`)
);

CREATE TABLE `Manager` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Counter_Executive` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL UNIQUE,
	`counter_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Customer` ADD CONSTRAINT `Customer_fk0` FOREIGN KEY (`service_id`) REFERENCES `Service`(`service_id`);

ALTER TABLE `Token` ADD CONSTRAINT `Token_fk0` FOREIGN KEY (`customer_id`) REFERENCES `Customer`(`customer_id`);

ALTER TABLE `Token` ADD CONSTRAINT `Token_fk1` FOREIGN KEY (`service_id`) REFERENCES `Service`(`service_id`);

ALTER TABLE `Service` ADD CONSTRAINT `Service_fk0` FOREIGN KEY (`counter_id`) REFERENCES `Counter`(`counter_id`);

ALTER TABLE `Counter_Executive` ADD CONSTRAINT `Counter_Executive_fk0` FOREIGN KEY (`counter_id`) REFERENCES `Counter`(`counter_number`);







