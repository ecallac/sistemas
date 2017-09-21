/*
SQLyog Community v12.2.4 (64 bit)
MySQL - 5.7.17-log : Database - common
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`common` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `common`;

/*Table structure for table `tipobase` */

DROP TABLE IF EXISTS `tipobase`;

CREATE TABLE `tipobase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(45) NOT NULL,
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `activo` varchar(3) NOT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`categoria`,`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tipobase` */

insert  into `tipobase`(`id`,`categoria`,`codigo`,`descripcion`,`activo`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`) values 
(1,'MODULE_STATUS','Active','Active','Y','2017-09-17 20:42:59',NULL,NULL,NULL,0),
(2,'MODULE_STATUS','Inactive','Inactive','Y','2017-09-17 20:43:38',NULL,NULL,NULL,0),
(3,'USER_STATUS','Active','Active','Y','2017-09-18 22:53:52',NULL,NULL,NULL,0),
(4,'USER_STATUS','Inactive','Inactive','Y','2017-09-18 22:54:13',NULL,NULL,NULL,0),
(5,'USER_STATUS','New','New','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
