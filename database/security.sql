/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.5.60 : Database - security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`security` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `security`;

/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `enabled` varchar(3) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `updatedBy` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `moduleVersion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `module` */

insert  into `module`(`id`,`name`,`description`,`enabled`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`,`author`,`moduleVersion`) values (1,'SECURITY','Security Application','Y',NULL,'2017-09-29 11:55:26',NULL,NULL,82,'Ecallac','1.0.0.0'),(34,'INTERNAL','Sistema interno','Y','2017-09-29 12:43:52','2021-10-19 21:05:56',NULL,'ecalla',20,'Efrain','1.0.0.0');

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `enabled` varchar(3) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`module_id`,`name`,`description`,`path`,`enabled`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`,`parent_permission_id`,`type`) values (1,1,'root','Root','/','Y','2017-05-14 22:51:47','2018-05-17 11:08:39',NULL,NULL,4,NULL,'P_COMPONENT'),(2,1,'home','Home','/home','Y','2017-05-14 22:51:52','2021-10-25 10:19:34',NULL,'ecalla',8,1,'P_COMPONENT'),(3,1,'module','Module Management','/module','Y','2017-05-14 22:51:52','2021-10-25 10:19:45',NULL,'ecalla',7,1,'P_COMPONENT'),(4,1,'permission','Permission Management','/permission','Y','2017-05-14 22:51:52','2021-10-25 10:19:51',NULL,'ecalla',7,1,'P_COMPONENT'),(5,34,'ajustes','Ajustes','/ajustes','Y','2017-05-14 22:51:52','2021-10-26 02:41:01',NULL,'ecalla',17,9,'P_COMPONENT'),(6,1,'role','Role Management','/role','Y','2017-05-14 22:51:53','2021-10-25 10:20:20',NULL,'ecalla',7,1,'P_COMPONENT'),(7,1,'user','User Management','/user','Y','2017-05-14 22:51:53','2021-10-25 10:20:09',NULL,'ecalla',7,1,'P_COMPONENT'),(8,34,'compras','Compras','/compras','Y','2017-05-14 22:51:53','2021-10-26 02:41:12',NULL,'ecalla',20,9,'P_COMPONENT'),(9,34,'root','Root','/','Y','2018-07-10 10:53:28','2018-07-10 10:53:28','ecalla',NULL,0,NULL,'P_COMPONENT'),(10,34,'tablero','Tablero','/home','Y','2018-07-10 10:54:02','2021-11-09 09:36:21','ecalla','ecalla',3,9,'P_COMPONENT'),(11,34,'recursos humanos','Recursos Humanos','/recursosHuamnos','Y','2018-07-10 10:55:22','2021-10-26 02:41:27','ecalla','ecalla',3,9,'P_COMPONENT'),(12,34,'ventas','Ventas','/ventas','Y','2021-10-26 02:37:46','2021-10-26 02:43:48','ecalla','ecalla',2,9,'P_COMPONENT'),(13,34,'punto de venta','Punto de venta','/puntoDeVenta','Y','2021-10-26 02:42:24','2021-10-26 02:43:48','ecalla','ecalla',1,9,'P_COMPONENT'),(14,34,'contable','Contable','/contable','Y','2021-10-26 02:42:55','2021-10-26 02:43:49','ecalla','ecalla',1,9,'P_COMPONENT'),(15,34,'Mi Perfil','Perfil de Usuario','/profile','Y','2021-11-09 11:31:22',NULL,'ecalla',NULL,0,NULL,'P_COMPONENT');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` varchar(3) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `updatedBy` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`,`enabled`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`) values (1,'ADMIN','Administrador','Y','2017-05-14 22:38:07','2021-10-19 21:13:59',NULL,'ecalla',16);

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

insert  into `rolepermission`(`role_id`,`permission_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15);

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

insert  into `roleuser`(`role_id`,`user_id`) values (1,1);

/*Table structure for table `session` */

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `loginDate` datetime DEFAULT NULL,
  `logoutDate` datetime DEFAULT NULL,
  `sessionKey` varchar(255) DEFAULT NULL,
  `hostAddress` varchar(45) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_session_user1_idx` (`user_id`),
  CONSTRAINT `fk_session_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

/*Data for the table `session` */

insert  into `session`(`id`,`user_id`,`loginDate`,`logoutDate`,`sessionKey`,`hostAddress`,`module_id`) values (1,1,'2021-10-25 16:04:59',NULL,'C4EB890F90C57D22B1C437F22333B06F','181.65.35.194',1),(2,1,'2021-10-26 01:59:03',NULL,'D7ED0E7659CBD68D5EC704FF520AF543','181.65.35.194',34),(3,1,'2021-10-26 02:02:16',NULL,'C94F07B6A87AB3DE714C19933D30E820','181.65.35.194',1),(4,1,'2021-10-26 02:02:26',NULL,'A9AE6A7230C83B29228471A2E85BEA62','181.65.35.194',34),(5,1,'2021-10-26 02:59:26',NULL,'7D0D0169A36F73C182E79980024B1939','181.65.35.194',1),(6,1,'2021-10-26 02:59:44',NULL,'C7668365171983D78DB209193D71032A','181.65.35.194',34),(7,1,'2021-10-26 03:01:10',NULL,'51C9A3C841C561DCB0924C68B5C9129A','181.65.35.194',34),(8,1,'2021-10-27 23:48:37',NULL,'B1A7084D033D2AD2F90BD4538EE806A3','181.65.35.194',1),(9,1,'2021-10-28 00:52:03',NULL,'53185CCAA1C78C564CF223C4A50B228A','181.65.35.194',1),(10,1,'2021-10-28 13:20:28',NULL,'F5CB1718A7A3ACDF03DEE0FC69A6DEA5','181.65.35.194',1),(11,1,'2021-10-28 13:22:59',NULL,'F0312871FB9D0934D8B2BCBF66D501D4','181.65.35.194',1),(12,1,'2021-11-03 02:55:45',NULL,'0814CF883772722BFF3F7E5C1C5D6F68','181.65.35.194',1),(13,1,'2021-11-03 03:31:37',NULL,'3CC142275BCE3DE51E5A19C23EF749A7','181.65.35.194',1),(14,1,'2021-11-03 03:41:15',NULL,'280621CF9221C957B057ED7473EC654C','181.65.35.194',1),(15,1,'2021-11-03 05:40:00',NULL,'4BEB7EF4B9323880EBC8AC9553FC9396','181.65.35.194',1),(16,1,'2021-11-04 20:25:07',NULL,'9BA8EB0BC2133F9EEEECAC291AFDCB6D','181.65.35.194',34),(17,1,'2021-11-04 20:59:29',NULL,'03156D601A3D7C89DED12BF2B0945492','181.65.35.194',34),(18,1,'2021-11-04 21:06:42',NULL,'05CDB3FFC18BEAB8A017EE3A990E7EBF','181.65.35.194',34),(19,1,'2021-11-04 21:29:02',NULL,'2A9C5AD49C3CE0FB5B19D16B929E0AEE','181.65.35.194',34),(20,1,'2021-11-04 21:34:39',NULL,'9EFC6144C5DA4D64F0496B2B282CA9FE','181.65.35.194',34),(21,1,'2021-11-04 21:35:08',NULL,'8D446749F767AA3A75CC82837884F685','181.65.35.194',34),(22,1,'2021-11-08 09:57:44',NULL,'B0111F3B6D7167A03E8857C183F467CD','181.65.35.194',34),(23,1,'2021-11-08 10:01:00',NULL,'29C50040CC7DAF12E54A78C0545F30A2','181.65.35.194',34),(24,1,'2021-11-08 10:27:49',NULL,'A0D52A87A98C64D4788FCE79FA4FA9CA','181.65.35.194',34),(25,1,'2021-11-08 11:05:19',NULL,'502D8E7DECC3618F42F9C17257FD80A8','181.65.35.194',34),(26,1,'2021-11-08 11:51:34',NULL,'7A068D02011B91BACC42CE171364F1C7','181.65.35.194',34),(27,1,'2021-11-08 11:52:47',NULL,'2799A4B53A294E305BE34834255C4262','181.65.35.194',34),(28,1,'2021-11-08 12:13:35',NULL,'20710CB75CB41054FFE54DAE143415CB','181.65.35.194',34),(29,1,'2021-11-08 12:22:16',NULL,'0ED7F4648DC16FE0EA3AF262F7A415A1','181.65.35.194',34),(30,1,'2021-11-08 12:22:27',NULL,'B719DA7FA9EEFD691CD96641FDB24991','181.65.35.194',34),(31,1,'2021-11-08 12:26:26',NULL,'AEB7672D3032DC0B2CF8F2282F17A96A','181.65.35.194',34),(32,1,'2021-11-08 12:37:51',NULL,'30E135008CBA5ACA099C90F6EEC2C46F','181.65.35.194',34),(33,1,'2021-11-08 12:39:52',NULL,'5810126CABFF8B8940AAA7E2D3D3E406','181.65.35.194',34),(34,1,'2021-11-08 12:49:34',NULL,'7F9402F9004EA413BCF6EE08E16C4CC0','181.65.35.194',34),(35,1,'2021-11-08 13:13:10',NULL,'BACFEBF73579B22BBAF52DA4EA5F3E1E','181.65.35.194',34),(36,1,'2021-11-08 13:15:41',NULL,'5581DBE609396FB03796C17A8DE37CDF','181.65.35.194',34),(37,1,'2021-11-08 13:26:57',NULL,'85BA66F8E9C4B91D3F91E0E029A805F1','181.65.35.194',34),(38,1,'2021-11-08 13:39:42',NULL,'049C2179DB8A259A4311E0F5EA024AA5','181.65.35.194',34),(39,1,'2021-11-08 13:41:52',NULL,'D23C91D3AFC24CC4BF556D751EFF0921','181.65.35.194',34),(40,1,'2021-11-08 13:52:24',NULL,'B3D194D7E48A1050BA6E3C3EC6C4702F','181.65.35.194',34),(41,1,'2021-11-08 13:54:59',NULL,'9C94AFAAFEE1DD089646C812ADFCFF7C','181.65.35.194',34),(42,1,'2021-11-08 14:44:03',NULL,'576C31A6D592B9B672D3ED1DB8D821EF','181.65.35.194',34),(43,1,'2021-11-08 14:52:05',NULL,'F1C25D5A1129E74158932213FCFBA1B2','181.65.35.194',34),(44,1,'2021-11-08 15:01:38',NULL,'BDD31064C2A8D1D2C33C5313033213FA','181.65.35.194',34),(45,1,'2021-11-08 16:11:06',NULL,'9081CF6D93C0C4AA5DAA37E103507C50','181.65.35.194',34),(46,1,'2021-11-08 17:37:39',NULL,'B41A46C99DFFE0362C8F4DE93D95BC23','181.65.35.194',34),(47,1,'2021-11-08 18:48:16',NULL,'BB84FC3948B8CE02212112F41BDA2D9B','181.65.35.194',34),(48,1,'2021-11-08 19:08:12',NULL,'82D95DD665FE1996BC91CDEDAA11F55C','181.65.35.194',34),(49,1,'2021-11-08 19:15:03',NULL,'63E5D4C3B44EBA765D065D44DD1DFB23','181.65.35.194',34),(50,1,'2021-11-08 19:32:29',NULL,'C0BAD8FF2115696799ECDAEF5330B42C','181.65.35.194',34),(51,1,'2021-11-08 20:00:30',NULL,'998CB827E530E9F24813E2E6F7CC7011','181.65.35.194',34),(52,1,'2021-11-08 20:16:09',NULL,'18CCB544B6239392AFB61FEEA0B5F62E','181.65.35.194',34),(53,1,'2021-11-08 20:40:03',NULL,'97EF5AC2C7E4395B0E056ED7FE4B9984','181.65.35.194',34),(54,1,'2021-11-08 20:48:29',NULL,'86C07972CAC988C0292974EB0A9476CB','181.65.35.194',34),(55,1,'2021-11-09 09:27:01',NULL,'89BED63C6F4F616DC6665E7B26D3932C','181.65.35.194',34),(56,1,'2021-11-09 09:29:28',NULL,'6B78CBD5597E018BEE7537614E475354','181.65.35.194',1),(57,1,'2021-11-09 09:36:42',NULL,'DE4467735109FD01F5CEE9ACC3638842','181.65.35.194',34),(58,1,'2021-11-09 11:05:47',NULL,'349C3A929DBE186EEED9A8C5872C2AF0','181.65.35.194',34),(59,1,'2021-11-09 11:28:33',NULL,'DE07FE79B597DF347AD8962F2DA9FABD','181.65.35.194',1),(60,1,'2021-11-09 11:33:24',NULL,'6C8957D06DE3572146D77F8FAC0A5968','181.65.35.194',34),(61,1,'2021-11-09 11:39:47',NULL,'A461F4E0EB0CBC248E207C33C5858AB6','181.65.35.194',1),(62,1,'2021-11-09 12:02:38',NULL,'9D85F69D6FD3A7B688CE8FCB97519C0A','181.65.35.194',34),(63,1,'2021-11-09 12:05:37',NULL,'60847C1201790688F6E3841C60EB7396','181.65.35.194',34),(64,1,'2021-11-09 12:09:53',NULL,'168002AC3D6B1650C64851E2FC5CCF89','181.65.35.194',34),(65,1,'2021-11-09 12:40:09',NULL,'370DC0E37F4BE69B51F19E7489F8676B','181.65.35.194',34),(66,1,'2021-11-09 14:09:38',NULL,'DDE1F6766F2D3F1B829436043B3D05C0','181.65.35.194',34),(67,1,'2021-11-09 15:06:30',NULL,'66661DEDF60B5E140550BAFDDDEFF49E','181.65.35.194',34),(68,1,'2021-11-09 16:39:02',NULL,'B3BAA31B0BCDC386B49E3BF157DF4732','181.65.35.194',34),(69,1,'2021-11-09 16:49:38',NULL,'6464AEE1BC56E29643F49F431E38190C','181.65.35.194',34),(70,1,'2021-11-09 18:55:17',NULL,'CEADFB572C3F12866C5E7E63B8D4D672','181.65.35.194',34),(71,1,'2021-11-09 19:50:58',NULL,'655A43AC9BB5D2D85322DDA886343738','181.65.35.194',1),(72,1,'2021-11-09 20:28:22',NULL,'BC28B72B9F81FB11E53C3F5ECD89FDF2','181.65.35.194',34),(73,1,'2021-11-09 20:50:47',NULL,'2D81FF8C1657E327430F1DBA438472DF','181.65.35.194',34),(74,1,'2021-11-09 21:11:03',NULL,'7562A55E3B34D0373B8A731699236A1C','181.65.35.194',34),(75,1,'2021-11-09 21:13:57',NULL,'AC17F451E978486AC5E2F58E0D0B7A27','181.65.35.194',34),(76,1,'2021-11-09 22:06:09',NULL,'AF5D5EA1ECC056D978118C0D3631D2DE','181.65.35.194',34),(77,1,'2021-11-09 23:20:58',NULL,'2F22B58DC335FD59B0FF9E4FA5E11638','181.65.35.194',34),(78,1,'2021-11-09 23:46:41',NULL,'13297D8AC9DF467428F274EAA68CFDA5','181.65.35.194',34),(79,1,'2021-11-10 00:41:49',NULL,'B484949BC5D3BE521392634D4F92EFC2','181.65.35.194',34),(80,1,'2021-11-10 01:05:15',NULL,'EE2F65CDECA31372E4A65408498C5CB2','181.65.35.194',34),(81,1,'2021-11-10 01:29:31',NULL,'C67F3E3CF433DA5CD079E4CD645EF7A6','181.65.35.194',34),(82,1,'2021-11-10 01:31:09',NULL,'59E98E43FE792B6FFAF5FB45F2096BF9','181.65.35.194',34),(83,1,'2021-11-10 19:18:33',NULL,'EDF80281074854D534AE1573A01E7634','181.65.35.194',34),(84,1,'2021-11-10 19:22:13',NULL,'A8107DA6977BEC727BBBB7E777DA5E75','181.65.35.194',1),(85,1,'2021-11-10 19:56:39',NULL,'950DB63776C6F2237E3B5F945DD3B3A0','181.65.35.194',34),(86,1,'2021-11-11 10:02:58',NULL,'CD0CB73113B616FE0BC77B81583407F9','181.65.35.194',34),(87,1,'2021-11-11 11:01:05',NULL,'5BE1799AD28CA380555F0F46D3882527','181.65.35.194',34),(88,1,'2021-11-11 11:08:30',NULL,'9BFF7AFBCD9D83011929D64941CEB6AC','181.65.35.194',34),(89,1,'2021-11-11 11:24:57',NULL,'E9E8AD16295AEE259E5511818FE28D6A','181.65.35.194',34),(90,1,'2021-11-11 12:48:09',NULL,'64AE1C578A3D9FB10CE65EE6635EAC90','181.65.35.194',34),(91,1,'2021-11-11 13:29:57',NULL,'72B4FB907C7D04C2F1CBE15E7F728BF4','181.65.35.194',34),(92,1,'2021-11-11 15:18:26',NULL,'F2F4E0C026F6E6E1E602EFE22B45A8AB','181.65.35.194',34),(93,1,'2021-11-11 15:24:28',NULL,'51DFAFE026C95BAAC1A569407C2BB48C','181.65.35.194',34),(94,1,'2021-11-11 23:05:00',NULL,'B20626506D9E041EFE76F0EF010F7BAE','181.65.35.194',34),(95,1,'2021-11-11 23:10:50',NULL,'15E7600C347073A7E7BB9A01B6642FF6','181.65.35.194',34),(96,1,'2021-11-11 23:21:54',NULL,'5A61AF4669FA7C908D739DEB3BC6297C','181.65.35.194',34),(97,1,'2021-11-11 23:48:32',NULL,'E51DC6AD3C759E8069C388D549CAE32E','181.65.35.194',34),(98,1,'2021-11-12 00:50:29',NULL,'DFD219A37B860710F294B99F4A244638','181.65.35.194',34),(99,1,'2021-11-12 00:59:12',NULL,'EAB757227FBF522B105821731B3AAF6D','181.65.35.194',34),(100,1,'2021-11-12 01:03:47',NULL,'BCD1C2B3486CFA8C3751608AAF830566','181.65.35.194',34),(101,1,'2021-11-12 01:13:30',NULL,'07AD7BF450C00B1AEB57C6465007A3A3','181.65.35.194',34),(102,1,'2021-12-16 20:28:57',NULL,'79041E201713E486FEFFF78609A51C88','181.66.176.196',34);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`status`,`entidadrole_id`,`question`,`answer`,`dateCreated`,`dateUpdated`,`createdBy`,`updatedBy`,`version`,`activationDate`,`inactivationDate`) values (1,'ecalla','$2a$10$kHYeZT1mALmgExCEwhK63OdoePUSKSKQDLkin8CmegrLwCe4gPiSu','S_ACTIVE',1,'animal favorito','perro','2017-05-14 22:34:13','2021-11-12 01:15:20',NULL,'ecalla',9,'2017-05-14 22:34:13',NULL),(6,'asdasd','$2a$10$7DbUuKmnH7FfOX/TZFKnTu6hQ5WKVP4Cd4e.uIlHo687pIOrlKAFK','S_NEW',1,'asdasd','asdasd','2018-05-28 10:29:27','2021-10-21 10:06:36','ecalla','ecalla',6,NULL,NULL),(7,'dummy','$2a$10$U1gCYcLmhjtCTsThp35IAei9mp8KuCfWxv3JW35Byv3VMqGTGuG5C','S_NEW',6,'dummy','dummy','2021-10-22 01:23:05',NULL,'ecalla',NULL,0,NULL,NULL);

/*Table structure for table `rolepermissionview` */

DROP TABLE IF EXISTS `rolepermissionview`;

/*!50001 DROP VIEW IF EXISTS `rolepermissionview` */;
/*!50001 DROP TABLE IF EXISTS `rolepermissionview` */;

/*!50001 CREATE TABLE  `rolepermissionview`(
 `module_id` int(11) ,
 `permission_name` varchar(100) ,
 `permission_description` varchar(255) ,
 `path` varchar(100) ,
 `permission_enabled` varchar(3) ,
 `parent_permission_id` int(11) ,
 `permission_id` int(11) ,
 `role_id` int(11) ,
 `role_name` varchar(100) ,
 `role_description` varchar(255) ,
 `role_enabled` varchar(3) 
)*/;

/*Table structure for table `roleuserview` */

DROP TABLE IF EXISTS `roleuserview`;

/*!50001 DROP VIEW IF EXISTS `roleuserview` */;
/*!50001 DROP TABLE IF EXISTS `roleuserview` */;

/*!50001 CREATE TABLE  `roleuserview`(
 `name` varchar(100) ,
 `description` varchar(255) ,
 `role_enabled` varchar(3) ,
 `role_id` int(11) ,
 `user_id` int(11) ,
 `username` varchar(50) ,
 `password` varchar(255) ,
 `user_status` varchar(20) ,
 `entidadrole_id` int(11) ,
 `question` varchar(255) ,
 `answer` varchar(255) ,
 `activationdate` datetime ,
 `inactivationdate` datetime 
)*/;

/*View structure for view rolepermissionview */

/*!50001 DROP TABLE IF EXISTS `rolepermissionview` */;
/*!50001 DROP VIEW IF EXISTS `rolepermissionview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rolepermissionview` AS select `p`.`module_id` AS `module_id`,`p`.`name` AS `permission_name`,`p`.`description` AS `permission_description`,`p`.`path` AS `path`,`p`.`enabled` AS `permission_enabled`,`p`.`parent_permission_id` AS `parent_permission_id`,`rp`.`permission_id` AS `permission_id`,`rp`.`role_id` AS `role_id`,`r`.`name` AS `role_name`,`r`.`description` AS `role_description`,`r`.`enabled` AS `role_enabled` from ((`role` `r` join `rolepermission` `rp` on((`r`.`id` = `rp`.`role_id`))) join `permission` `p` on((`rp`.`permission_id` = `p`.`id`))) */;

/*View structure for view roleuserview */

/*!50001 DROP TABLE IF EXISTS `roleuserview` */;
/*!50001 DROP VIEW IF EXISTS `roleuserview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `roleuserview` AS select `r`.`name` AS `name`,`r`.`description` AS `description`,`r`.`enabled` AS `role_enabled`,`ru`.`role_id` AS `role_id`,`ru`.`user_id` AS `user_id`,`u`.`username` AS `username`,`u`.`password` AS `password`,`u`.`status` AS `user_status`,`u`.`entidadrole_id` AS `entidadrole_id`,`u`.`question` AS `question`,`u`.`answer` AS `answer`,`u`.`activationDate` AS `activationdate`,`u`.`inactivationDate` AS `inactivationdate` from ((`role` `r` join `roleuser` `ru` on((`r`.`id` = `ru`.`role_id`))) join `user` `u` on((`ru`.`user_id` = `u`.`id`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
