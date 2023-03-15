CREATE TABLE `Customer` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	`mobile_number` INT UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Token` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`status` VARCHAR(255) NOT NULL,
	`service_id` INT NOT NULL,
	`generation_time` TIME NOT NULL,
	`expected_time` TIME NOT NULL,
	`frequency_of_calling` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Counter` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50),
	PRIMARY KEY (`id`)
);

CREATE TABLE `Service` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`service_name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Manager` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Counter_Executive` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`counter_number` INT NOT NULL,
	`username` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Servicetype` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`service_id` INT,
	`service_name` varchar(50),
	`type_of_service` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Token` ADD CONSTRAINT `Token_fk0` FOREIGN KEY (`service_id`) REFERENCES `Service`(`id`);

ALTER TABLE `Counter_Executive` ADD CONSTRAINT `Counter_Executive_fk0` FOREIGN KEY (`counter_number`) REFERENCES `Counter`(`id`);

ALTER TABLE `Servicetype` ADD CONSTRAINT `Servicetype_fk0` FOREIGN KEY (`service_id`) REFERENCES `Service`(`id`);








