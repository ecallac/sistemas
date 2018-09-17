/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.5.60 : Database - car
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `alquiler` */

DROP TABLE IF EXISTS `alquiler`;

CREATE TABLE `alquiler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `vehiculo_id` int(11) NOT NULL,
  `conductor_id` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `cuentapactada` decimal(10,0) DEFAULT NULL,
  `fechainicio` datetime DEFAULT NULL,
  `fechafin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vehiculochofer_vehiculo1_idx` (`vehiculo_id`),
  KEY `fk_vehiculochofer_conductor1_idx` (`conductor_id`),
  CONSTRAINT `fk_vehiculochofer_vehiculo1` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vehiculochofer_conductor1` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `alquiler` */

/*Table structure for table `categoria` */

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `descripsion` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `categoria` */

/*Table structure for table `cierre` */

DROP TABLE IF EXISTS `cierre`;

CREATE TABLE `cierre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `vehiculo_id` int(11) NOT NULL,
  `fechainicio` date DEFAULT NULL,
  `fechafin` date DEFAULT NULL,
  `totalentrada` decimal(10,0) DEFAULT NULL,
  `totalsalida` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cierre_vehiculo1_idx` (`vehiculo_id`),
  CONSTRAINT `fk_cierre_vehiculo1` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cierre` */

/*Table structure for table `clase` */

DROP TABLE IF EXISTS `clase`;

CREATE TABLE `clase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  `descripsion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `clase` */

/*Table structure for table `conductor` */

DROP TABLE IF EXISTS `conductor`;

CREATE TABLE `conductor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `numerodocumento` varchar(45) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `conductor` */

/*Table structure for table `descripcion` */

DROP TABLE IF EXISTS `descripcion`;

CREATE TABLE `descripcion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `categoria_id` int(11) NOT NULL,
  `descripsion` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_descripsion_categoria1_idx` (`categoria_id`),
  CONSTRAINT `fk_descripsion_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `descripcion` */

/*Table structure for table `dia` */

DROP TABLE IF EXISTS `dia`;

CREATE TABLE `dia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dia` */

/*Table structure for table `entrada` */

DROP TABLE IF EXISTS `entrada`;

CREATE TABLE `entrada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `descripsion_id` int(11) NOT NULL,
  `dia_id` int(11) NOT NULL,
  `vehiculo_id` int(11) NOT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entrada_descripsion1_idx` (`descripsion_id`),
  KEY `fk_entrada_vehiculo1_idx` (`vehiculo_id`),
  KEY `fk_entrada_dia1_idx` (`dia_id`),
  CONSTRAINT `fk_entrada_descripsion1` FOREIGN KEY (`descripsion_id`) REFERENCES `descripcion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_vehiculo1` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrada_dia1` FOREIGN KEY (`dia_id`) REFERENCES `dia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `entrada` */

/*Table structure for table `marca` */

DROP TABLE IF EXISTS `marca`;

CREATE TABLE `marca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `descripsion` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `marca` */

insert  into `marca`(`id`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`,`descripsion`,`status`) values (4,'2018-09-13 09:07:27','2018-09-13 09:12:38','ecalla','ecalla',1,'Hyundai','Y'),(6,'2018-09-13 09:13:51','2018-09-13 10:11:05','ecalla','ecalla',1,'Toyota','Y');

/*Table structure for table `modelo` */

DROP TABLE IF EXISTS `modelo`;

CREATE TABLE `modelo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `marca_id` int(11) NOT NULL,
  `descripsion` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_modelo_marca1_idx` (`marca_id`),
  CONSTRAINT `fk_modelo_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `modelo` */

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `rol` */

insert  into `rol`(`id`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`,`status`,`descripcion`) values (1,'2018-09-04 19:16:26',NULL,'ecalla','ecalla',0,'Y','ADMIN');

/*Table structure for table `salida` */

DROP TABLE IF EXISTS `salida`;

CREATE TABLE `salida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `descripsion_id` int(11) NOT NULL,
  `dia_id` int(11) NOT NULL,
  `vehiculo_id` int(11) NOT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_salida_descripsion1_idx` (`descripsion_id`),
  KEY `fk_salida_dia1_idx` (`dia_id`),
  KEY `fk_salida_vehiculo1_idx` (`vehiculo_id`),
  CONSTRAINT `fk_salida_descripsion1` FOREIGN KEY (`descripsion_id`) REFERENCES `descripcion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_salida_dia1` FOREIGN KEY (`dia_id`) REFERENCES `dia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_salida_vehiculo1` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `salida` */

/*Table structure for table `trabajo` */

DROP TABLE IF EXISTS `trabajo`;

CREATE TABLE `trabajo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `vehiculo_id` int(11) NOT NULL,
  `dia_id` int(11) NOT NULL,
  `conductor_id` int(11) NOT NULL,
  `nvueltas` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trabajo_vehiculo1_idx` (`vehiculo_id`),
  KEY `fk_trabajo_dia1_idx` (`dia_id`),
  KEY `fk_trabajo_conductor1_idx` (`conductor_id`),
  CONSTRAINT `fk_trabajo_vehiculo1` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajo_dia1` FOREIGN KEY (`dia_id`) REFERENCES `dia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajo_conductor1` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `trabajo` */

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  `rol_id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_rol1_idx` (`rol_id`),
  CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`id`,`datecreated`,`dateupdated`,`createdby`,`updatedby`,`version`,`status`,`rol_id`,`username`,`password`) values (1,'2018-09-05 15:59:08',NULL,'ecalla','ecalla',0,'Y',1,'ecalla','$2a$10$zJwW4fYokCVfjGNb8yAKN.GgrRSP/VQ69MChbStoTPPSFeK8gEbGi');

/*Table structure for table `vehiculo` */

DROP TABLE IF EXISTS `vehiculo`;

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `modelo_id` int(11) NOT NULL,
  `clase_id` int(11) NOT NULL,
  `matricula` varchar(45) DEFAULT NULL,
  `foto` blob,
  `status` varchar(45) DEFAULT NULL,
  `nasientos` varchar(45) DEFAULT NULL,
  `transmision` varchar(45) DEFAULT NULL,
  `cuentaxdia` decimal(10,0) DEFAULT NULL,
  `cilindrada` int(11) DEFAULT NULL,
  `anio` int(11) DEFAULT NULL,
  `descripsion` varchar(255) DEFAULT NULL,
  `combustible` varchar(45) DEFAULT NULL,
  `color` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `placa_UNIQUE` (`matricula`),
  KEY `fk_vehiculo_modelo1_idx` (`modelo_id`),
  KEY `fk_vehiculo_clase1_idx` (`clase_id`),
  CONSTRAINT `fk_vehiculo_modelo1` FOREIGN KEY (`modelo_id`) REFERENCES `modelo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vehiculo_clase1` FOREIGN KEY (`clase_id`) REFERENCES `clase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vehiculo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
