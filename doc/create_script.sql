CREATE TABLE `city` (
	`id_city` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45),
	`code_phone` varchar(45),
	`id_country` int(11) NOT NULL,
	PRIMARY KEY (`id_city`)
);

CREATE TABLE `country` (
	`id_country` int(11) NOT NULL AUTO_INCREMENT,
	`name_country` varchar(5) NOT NULL,
	`key_contry` varchar(45) NOT NULL,
	`key_phone` varchar(45) NOT NULL,
	PRIMARY KEY (`id_country`)
);

CREATE TABLE `department` (
	`id_department` int(11) NOT NULL AUTO_INCREMENT,
	`name_department` varchar(60) NOT NULL,
	PRIMARY KEY (`id_department`)
);

CREATE TABLE `has_task_person` (
	`id_has_task_person` int(11) NOT NULL AUTO_INCREMENT,
	`id_task` int(11) NOT NULL,
	`id_person` int(11) NOT NULL,
	PRIMARY KEY (`id_has_task_person`)
);

CREATE TABLE `log_time_task` (
	`id_log` int(11) NOT NULL AUTO_INCREMENT,
	`date_log` DATE NOT NULL,
	`discription` varchar(500) NOT NULL,
	`links_ext_stor` varchar(256),
	`id_type_activity` int(11) NOT NULL,
	`id_has_task_person` int(11) NOT NULL,
	PRIMARY KEY (`id_log`)
);

CREATE TABLE `person` (
	`id_person` int(11) NOT NULL AUTO_INCREMENT,
	`surname` varchar(45) NOT NULL,
	`name` varchar(45) NOT NULL,
	`patronymic` varchar(45),
	`date_of_birth` DATE,
	`sex` varchar(45),
	`e_mail` varchar(60),
	`id_user` int(11) NOT NULL,
	`id_post` int(11) NOT NULL,
	`id_department` int(11) NOT NULL,
	`id_city` int(11) NOT NULL,
	`mobile_phone` varchar(45),
	`home_phone` varchar(45),
	PRIMARY KEY (`id_person`)
);

CREATE TABLE `post` (
	`id_post` int(11) NOT NULL AUTO_INCREMENT,
	`name_post` varchar(45) NOT NULL,
	`rate` FLOAT NOT NULL,
	PRIMARY KEY (`id_post`)
);

CREATE TABLE `task` (
	`id_task` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
	`description` varchar(45),
	`date_begin` DATE NOT NULL,
	`date_end` DATE,
	`done` bit(1),
	`progress` int(11) DEFAULT '0',
	`id_type_task` int(11) NOT NULL,
	`id_person_add` int(11) NOT NULL,
	PRIMARY KEY (`id_task`)
);

CREATE TABLE `type_activity` (
	`id_type_activity` int(11) NOT NULL AUTO_INCREMENT,
	`name_activity` varchar(45),
	PRIMARY KEY (`id_type_activity`)
);

CREATE TABLE `type_task` (
	`id_type_task` int(11) NOT NULL AUTO_INCREMENT,
	`name_type` varchar(45),
	PRIMARY KEY (`id_type_task`)
);

CREATE TABLE `type_user` (
	`id_type` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
	`access_level` int(11) NOT NULL,
	PRIMARY KEY (`id_type`)
);

CREATE TABLE `user` (
	`id_user` int(11) NOT NULL AUTO_INCREMENT,
	`login` varchar(45) NOT NULL,
	`password` varchar(128) NOT NULL,
	`id_type_user` int(11) NOT NULL,
	PRIMARY KEY (`id_user`)
);

ALTER TABLE `city` ADD CONSTRAINT `city_fk0` FOREIGN KEY (`id_country`) REFERENCES `country`(`id_country`);

ALTER TABLE `has_task_person` ADD CONSTRAINT `has_task_person_fk0` FOREIGN KEY (`id_task`) REFERENCES `task`(`id_task`);

ALTER TABLE `has_task_person` ADD CONSTRAINT `has_task_person_fk1` FOREIGN KEY (`id_person`) REFERENCES `person`(`id_person`);

ALTER TABLE `log_time_task` ADD CONSTRAINT `log_time_task_fk0` FOREIGN KEY (`id_type_activity`) REFERENCES `type_activity`(`id_type_activity`);

ALTER TABLE `log_time_task` ADD CONSTRAINT `log_time_task_fk1` FOREIGN KEY (`id_has_task_person`) REFERENCES `has_task_person`(`id_has_task_person`);

ALTER TABLE `person` ADD CONSTRAINT `person_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id_user`);

ALTER TABLE `person` ADD CONSTRAINT `person_fk1` FOREIGN KEY (`id_post`) REFERENCES `post`(`id_post`);

ALTER TABLE `person` ADD CONSTRAINT `person_fk2` FOREIGN KEY (`id_department`) REFERENCES `department`(`id_department`);

ALTER TABLE `person` ADD CONSTRAINT `person_fk3` FOREIGN KEY (`id_city`) REFERENCES `city`(`id_city`);

ALTER TABLE `task` ADD CONSTRAINT `task_fk0` FOREIGN KEY (`id_type_task`) REFERENCES `type_task`(`id_type_task`);

ALTER TABLE `task` ADD CONSTRAINT `task_fk1` FOREIGN KEY (`id_person_add`) REFERENCES `person`(`id_person`);

ALTER TABLE `user` ADD CONSTRAINT `user_fk0` FOREIGN KEY (`id_type_user`) REFERENCES `type_user`(`id_type`);

