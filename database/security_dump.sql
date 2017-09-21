/*
SQLyog Community v12.2.4 (64 bit)
MySQL - 5.7.17-log : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security`;

/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `updatedBy` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `moduleVersion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `module` */

insert  into `module`(`id`,`name`,`description`,`status`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`,`author`,`moduleVersion`) values 
(1,'SECURITY','Security App',1,'2017-05-14 22:26:15',NULL,NULL,NULL,0,NULL,'1.0.0.0');

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `updatedBy` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `parent_permission_id` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_permission_module_idx` (`module_id`),
  KEY `fk_permission_permission1_idx` (`parent_permission_id`),
  CONSTRAINT `fk_permission_module` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_permission_permission1` FOREIGN KEY (`parent_permission_id`) REFERENCES `permission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`module_id`,`name`,`description`,`path`,`status`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`,`parent_permission_id`,`type`) values 
(1,1,'root','root','/',1,'2017-05-14 22:51:47',NULL,NULL,NULL,0,NULL,NULL),
(2,1,'home','home','/home',1,'2017-05-14 22:51:52',NULL,NULL,NULL,0,NULL,NULL),
(3,1,'module','module management','/module/',1,'2017-05-14 22:51:52',NULL,NULL,NULL,0,NULL,NULL),
(4,1,'permission','permission management','/permission',1,'2017-05-14 22:51:52',NULL,NULL,NULL,0,NULL,NULL),
(5,1,'assigneRolePermission','assigneRolePermission','/assigneRolePermission',1,'2017-05-14 22:51:52',NULL,NULL,NULL,0,NULL,NULL),
(6,1,'role','role management','/role',1,'2017-05-14 22:51:53',NULL,NULL,NULL,0,NULL,NULL),
(7,1,'user','user Management','/userList',1,'2017-05-14 22:51:53',NULL,NULL,NULL,0,NULL,NULL),
(8,1,'assigneRoleUser','assigneRoleUser','/assigneRoleUser',1,'2017-05-14 22:51:53',NULL,NULL,NULL,0,NULL,NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `updatedBy` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`,`status`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`) values 
(1,'ADMIN','Administrador',1,'2017-05-14 22:38:07',NULL,NULL,NULL,0);

/*Table structure for table `rolepermission` */

DROP TABLE IF EXISTS `rolepermission`;

CREATE TABLE `rolepermission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `fk_role_has_permission_permission1_idx` (`permission_id`),
  KEY `fk_role_has_permission_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_has_permission_permission1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_permission_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rolepermission` */

insert  into `rolepermission`(`role_id`,`permission_id`) values 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8);

/*Table structure for table `roleuser` */

DROP TABLE IF EXISTS `roleuser`;

CREATE TABLE `roleuser` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_role_has_user_user1_idx` (`user_id`),
  KEY `fk_role_has_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_has_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roleuser` */

insert  into `roleuser`(`role_id`,`user_id`) values 
(1,1);

/*Table structure for table `session` */

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `loginDate` datetime DEFAULT NULL,
  `logoutDate` datetime DEFAULT NULL,
  `sessionKey` varchar(255) DEFAULT NULL,
  `hostAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_session_user1_idx` (`user_id`),
  CONSTRAINT `fk_session_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `session` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `entidadrole_id` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `updatedBy` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `activationDate` datetime DEFAULT NULL,
  `inactivationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`status`,`entidadrole_id`,`question`,`answer`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`,`activationDate`,`inactivationDate`) values 
(1,'ecalla','$2a$10$gAzsxsY8QZyt2O0rfEoC2eUBHuJ0W0bgUIhyFTrMG7b/mjbQ3fdL6',1,NULL,'animal favorito','perro','2017-05-14 22:34:13',NULL,NULL,NULL,0,'2017-05-14 22:34:13','2017-05-31 22:34:06');

/*Table structure for table `rolepermissionview` */

DROP TABLE IF EXISTS `rolepermissionview`;

/*!50001 DROP VIEW IF EXISTS `rolepermissionview` */;
/*!50001 DROP TABLE IF EXISTS `rolepermissionview` */;

/*!50001 CREATE TABLE  `rolepermissionview`(
 `module_id` int(11) ,
 `permission_name` varchar(100) ,
 `permission_description` varchar(255) ,
 `path` varchar(100) ,
 `permission_status` int(11) ,
 `parent_permission_id` int(11) ,
 `permission_id` int(11) ,
 `role_id` int(11) ,
 `role_name` varchar(100) ,
 `role_description` varchar(255) ,
 `role_status` int(11) 
)*/;

/*Table structure for table `roleuserview` */

DROP TABLE IF EXISTS `roleuserview`;

/*!50001 DROP VIEW IF EXISTS `roleuserview` */;
/*!50001 DROP TABLE IF EXISTS `roleuserview` */;

/*!50001 CREATE TABLE  `roleuserview`(
 `name` varchar(100) ,
 `description` varchar(255) ,
 `role_status` int(11) ,
 `role_id` int(11) ,
 `user_id` int(11) ,
 `username` varchar(50) ,
 `password` varchar(255) ,
 `user_status` int(11) ,
 `entidadrole_id` int(11) ,
 `question` varchar(255) ,
 `answer` varchar(255) ,
 `activationdate` datetime ,
 `inactivationdate` datetime 
)*/;

/*View structure for view rolepermissionview */

/*!50001 DROP TABLE IF EXISTS `rolepermissionview` */;
/*!50001 DROP VIEW IF EXISTS `rolepermissionview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rolepermissionview` AS select `p`.`module_id` AS `module_id`,`p`.`name` AS `permission_name`,`p`.`description` AS `permission_description`,`p`.`path` AS `path`,`p`.`status` AS `permission_status`,`p`.`parent_permission_id` AS `parent_permission_id`,`rp`.`permission_id` AS `permission_id`,`rp`.`role_id` AS `role_id`,`r`.`name` AS `role_name`,`r`.`description` AS `role_description`,`r`.`status` AS `role_status` from ((`role` `r` join `rolepermission` `rp` on((`r`.`id` = `rp`.`role_id`))) join `permission` `p` on((`rp`.`permission_id` = `p`.`id`))) */;

/*View structure for view roleuserview */

/*!50001 DROP TABLE IF EXISTS `roleuserview` */;
/*!50001 DROP VIEW IF EXISTS `roleuserview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `roleuserview` AS select `r`.`name` AS `name`,`r`.`description` AS `description`,`r`.`status` AS `role_status`,`ru`.`role_id` AS `role_id`,`ru`.`user_id` AS `user_id`,`u`.`username` AS `username`,`u`.`password` AS `password`,`u`.`status` AS `user_status`,`u`.`entidadrole_id` AS `entidadrole_id`,`u`.`question` AS `question`,`u`.`answer` AS `answer`,`u`.`activationDate` AS `activationdate`,`u`.`inactivationDate` AS `inactivationdate` from ((`role` `r` join `roleuser` `ru` on((`r`.`id` = `ru`.`role_id`))) join `user` `u` on((`ru`.`user_id` = `u`.`id`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
