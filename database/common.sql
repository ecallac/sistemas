/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.5.60 : Database - common
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`common` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `common`;

/*Table structure for table `centropoblado` */

DROP TABLE IF EXISTS `centropoblado`;

CREATE TABLE `centropoblado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `ubigeo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_centropoblado_ubigeo1_idx` (`ubigeo_id`),
  CONSTRAINT `fk_centropoblado_ubigeo1` FOREIGN KEY (`ubigeo_id`) REFERENCES `ubigeo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `centropoblado` */

/*Table structure for table `direccion` */

DROP TABLE IF EXISTS `direccion`;

CREATE TABLE `direccion` (
  `id` int(11) NOT NULL,
  `direccionexacta` varchar(225) DEFAULT NULL,
  `codigopostal` varchar(45) DEFAULT NULL,
  `entidad_id` int(11) NOT NULL,
  `ubigeo_id` int(11) NOT NULL,
  `centropoblado_id` int(11) DEFAULT NULL,
  `esprincipal` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_direccion_entidad1_idx` (`entidad_id`),
  KEY `fk_direccion_ubigeo1_idx` (`ubigeo_id`),
  KEY `fk_direccion_centropoblado1_idx` (`centropoblado_id`),
  CONSTRAINT `fk_direccion_centropoblado1` FOREIGN KEY (`centropoblado_id`) REFERENCES `centropoblado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_direccion_entidad1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_direccion_ubigeo1` FOREIGN KEY (`ubigeo_id`) REFERENCES `ubigeo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `direccion` */

/*Table structure for table `entidad` */

DROP TABLE IF EXISTS `entidad`;

CREATE TABLE `entidad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_entidad` varchar(45) DEFAULT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `entidad` */

insert  into `entidad`(`id`,`tipo_entidad`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`) values (1,'E_PERSONA',NULL,NULL,NULL,NULL,0),(5,'E_PERSONA','2018-06-22 15:29:09',NULL,'ecalla',NULL,0),(6,'E_PERSONA','2021-10-21 23:50:19',NULL,'ecalla',NULL,0);

/*Table structure for table `entidadrol` */

DROP TABLE IF EXISTS `entidadrol`;

CREATE TABLE `entidadrol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(45) DEFAULT NULL,
  `tipo_entidadrol` varchar(45) DEFAULT NULL,
  `entidad_id` int(11) NOT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entidadrol_entidad1_idx` (`entidad_id`),
  CONSTRAINT `fk_entidadrol_entidad1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `entidadrol` */

insert  into `entidadrol`(`id`,`estado`,`tipo_entidadrol`,`entidad_id`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`) values (1,NULL,'ER_USUARIO',1,NULL,NULL,NULL,NULL,0),(6,NULL,'ER_USUARIO',6,'2021-10-22 01:21:07',NULL,'ecalla',NULL,0);

/*Table structure for table `organizacion` */

DROP TABLE IF EXISTS `organizacion`;

CREATE TABLE `organizacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_organizacion` varchar(45) DEFAULT NULL,
  `numeroidentificacion` varchar(45) DEFAULT NULL,
  `razonsocial` varchar(225) DEFAULT NULL,
  `entidad_id` int(11) NOT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_organizacion_entidad1_idx` (`entidad_id`),
  CONSTRAINT `fk_organizacion_entidad1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `organizacion` */

/*Table structure for table `persona` */

DROP TABLE IF EXISTS `persona`;

CREATE TABLE `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_documento_identificaion` varchar(45) DEFAULT NULL,
  `numeroidentificacion` varchar(45) DEFAULT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `tipo_estado_civil` varchar(45) DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  `fechanacimiento` datetime DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `entidad_id` int(11) NOT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_persona_entidad1_idx` (`entidad_id`),
  CONSTRAINT `fk_persona_entidad1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `persona` */

insert  into `persona`(`id`,`tipo_documento_identificaion`,`numeroidentificacion`,`nombres`,`apellidos`,`tipo_estado_civil`,`sexo`,`fechanacimiento`,`email`,`entidad_id`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`) values (1,'P_DNI','43929489','Efrain','Calla','P_CASADO','M','2018-05-24 15:17:22','ecallac@gmail.com',1,NULL,NULL,NULL,NULL,0),(3,'P_DNI','43929489','asd','asd','P_CASADO','M','1986-06-14 00:00:00','ecallac@gmail.com',5,'2018-06-22 15:29:09','2018-06-22 15:29:09','ecalla',NULL,0),(4,'P_DNI','00000000','dummy','dummy','','','2021-10-21 00:00:00','',6,'2021-10-21 23:50:18',NULL,'ecalla',NULL,0);

/*Table structure for table `personaorganizacion` */

DROP TABLE IF EXISTS `personaorganizacion`;

CREATE TABLE `personaorganizacion` (
  `persona_id` int(11) NOT NULL,
  `organizacion_id` int(11) NOT NULL,
  PRIMARY KEY (`persona_id`,`organizacion_id`),
  KEY `fk_personaorganizacion_organizacion1_idx` (`organizacion_id`),
  KEY `fk_personaorganizacion_persona1_idx` (`persona_id`),
  CONSTRAINT `fk_personaorganizacion_organizacion1` FOREIGN KEY (`organizacion_id`) REFERENCES `organizacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personaorganizacion_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `personaorganizacion` */

/*Table structure for table `regla` */

DROP TABLE IF EXISTS `regla`;

CREATE TABLE `regla` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipo_valor` varchar(45) DEFAULT NULL,
  `activo` varchar(45) DEFAULT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `regla` */

/*Table structure for table `regladetalle` */

DROP TABLE IF EXISTS `regladetalle`;

CREATE TABLE `regladetalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `condicion` varchar(45) DEFAULT NULL,
  `valornumero` decimal(3,2) DEFAULT NULL,
  `valorcadena` varchar(45) DEFAULT NULL,
  `valorfecha` datetime DEFAULT NULL,
  `activo` varchar(45) DEFAULT NULL,
  `regla_id` int(11) NOT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_regladetalle_regla1_idx` (`regla_id`),
  CONSTRAINT `fk_regladetalle_regla1` FOREIGN KEY (`regla_id`) REFERENCES `regla` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `regladetalle` */

/*Table structure for table `telefono` */

DROP TABLE IF EXISTS `telefono`;

CREATE TABLE `telefono` (
  `id` int(11) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `numero` varchar(45) DEFAULT NULL,
  `codigoarea` varchar(45) DEFAULT NULL,
  `entidad_id` int(11) NOT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_telefono_entidad1_idx` (`entidad_id`),
  CONSTRAINT `fk_telefono_entidad1` FOREIGN KEY (`entidad_id`) REFERENCES `entidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `telefono` */

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
  UNIQUE KEY `UNIQUE` (`categoria`,`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `tipobase` */

insert  into `tipobase`(`id`,`categoria`,`codigo`,`descripcion`,`activo`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`) values (3,'TYPE_STATUS','S_ACTIVE','Active','Y','2017-09-18 22:53:52',NULL,NULL,NULL,0),(4,'TYPE_STATUS','S_INACTIVE','Inactive','Y','2017-09-18 22:54:13',NULL,NULL,NULL,0),(5,'TYPE_STATUS','S_NEW','New','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(6,'TYPE_PERMISSION','P_COMPONENT','Component of module','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(7,'TYPE_PERMISSION','P_ACTION','Action of component','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(8,'TYPE_ENTIDAD','E_PERSONA','Persona','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(9,'TYPE_ENTIDAD','E_ORGANIZACION','Organizacion','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(10,'TYPE_PERSONA_DOCUMENTO','P_DNI','Dni','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(11,'TYPE_PERSONA_ESTADO_CIVIL','P_SOLTERO','Soltero','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(12,'TYPE_PERSONA_ESTADO_CIVIL','P_CASADO','Casado','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(13,'TYPE_PERSONA_ESTADO_CIVIL','P_VIUDO','Viudo','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(14,'TYPE_PERSONA_ESTADO_CIVIL','P_DIVORCIADO','Divorciado','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(15,'TYPE_ORGANIZACION','O_PRIVADO','Privado','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(16,'TYPE_ORGANIZACION','O_PUBLICO','Publico','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(17,'TYPE_ENTIDADROL','ER_CLIENTE','Cliente','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(18,'TYPE_ENTIDADROL','ER_PROVEEDOR','Proveedor','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(19,'TYPE_ENTIDADROL','ER_USUARIO','Usuario','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0);

/*Table structure for table `ubigeo` */

DROP TABLE IF EXISTS `ubigeo`;

CREATE TABLE `ubigeo` (
  `id` int(11) NOT NULL,
  `codigoubigeo` varchar(45) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `abreviatura` varchar(45) DEFAULT NULL,
  `parent_ubigeo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ubigeo_ubigeo1_idx` (`parent_ubigeo_id`),
  CONSTRAINT `fk_ubigeo_ubigeo1` FOREIGN KEY (`parent_ubigeo_id`) REFERENCES `ubigeo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ubigeo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
