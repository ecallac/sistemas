CREATE DATABASE  IF NOT EXISTS `common` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `common`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: common
-- ------------------------------------------------------
-- Server version	5.5.60

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccionexacta` varchar(225) DEFAULT NULL,
  `codigopostal` varchar(45) DEFAULT NULL,
  `entidad_id` int(11) NOT NULL,
  `ubigeo_id` int(11) NOT NULL,
  `esprincipal` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `datecreated` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_direccion_entidad_idx` (`entidad_id`),
  KEY `fk_direccion_ubigeo_idx` (`ubigeo_id`),
  CONSTRAINT `fk_direccion_entidad` FOREIGN KEY (`entidad_id`) REFERENCES `entidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_direccion_ubigeo` FOREIGN KEY (`ubigeo_id`) REFERENCES `ubigeo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidad`
--

DROP TABLE IF EXISTS `entidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidad`
--

LOCK TABLES `entidad` WRITE;
/*!40000 ALTER TABLE `entidad` DISABLE KEYS */;
INSERT INTO `entidad` VALUES (1,'E_PERSONA',NULL,NULL,NULL,NULL,0),(5,'E_PERSONA','2018-06-22 15:29:09',NULL,'ecalla',NULL,0),(6,'E_PERSONA','2021-10-21 23:50:19',NULL,'ecalla',NULL,0);
/*!40000 ALTER TABLE `entidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadrol`
--

DROP TABLE IF EXISTS `entidadrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadrol`
--

LOCK TABLES `entidadrol` WRITE;
/*!40000 ALTER TABLE `entidadrol` DISABLE KEYS */;
INSERT INTO `entidadrol` VALUES (1,NULL,'ER_USUARIO',1,NULL,NULL,NULL,NULL,0),(6,NULL,'ER_USUARIO',6,'2021-10-22 01:21:07',NULL,'ecalla',NULL,0);
/*!40000 ALTER TABLE `entidadrol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizacion`
--

DROP TABLE IF EXISTS `organizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizacion`
--

LOCK TABLES `organizacion` WRITE;
/*!40000 ALTER TABLE `organizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'P_DNI','43929489','Efrain','Calla','P_CASADO','M','2018-05-24 15:17:22','ecallac@gmail.com',1,NULL,NULL,NULL,NULL,0),(3,'P_DNI','43929489','asd','asd','P_CASADO','M','1986-06-14 00:00:00','ecallac@gmail.com',5,'2018-06-22 15:29:09','2018-06-22 15:29:09','ecalla',NULL,0),(4,'P_DNI','00000000','dummy','dummy','','','2021-10-21 00:00:00','',6,'2021-10-21 23:50:18',NULL,'ecalla',NULL,0);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaorganizacion`
--

DROP TABLE IF EXISTS `personaorganizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personaorganizacion` (
  `persona_id` int(11) NOT NULL,
  `organizacion_id` int(11) NOT NULL,
  PRIMARY KEY (`persona_id`,`organizacion_id`),
  KEY `fk_personaorganizacion_organizacion1_idx` (`organizacion_id`),
  KEY `fk_personaorganizacion_persona1_idx` (`persona_id`),
  CONSTRAINT `fk_personaorganizacion_organizacion1` FOREIGN KEY (`organizacion_id`) REFERENCES `organizacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personaorganizacion_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaorganizacion`
--

LOCK TABLES `personaorganizacion` WRITE;
/*!40000 ALTER TABLE `personaorganizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `personaorganizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regla`
--

DROP TABLE IF EXISTS `regla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regla`
--

LOCK TABLES `regla` WRITE;
/*!40000 ALTER TABLE `regla` DISABLE KEYS */;
/*!40000 ALTER TABLE `regla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regladetalle`
--

DROP TABLE IF EXISTS `regladetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regladetalle`
--

LOCK TABLES `regladetalle` WRITE;
/*!40000 ALTER TABLE `regladetalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `regladetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefono` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono`
--

LOCK TABLES `telefono` WRITE;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` VALUES (1,'TEL_MOVIL','976589075','051',1,'2017-09-18 22:54:39',NULL,NULL,NULL,0),(2,'TEL_FIJO','390000','051 01',1,'2017-09-18 22:54:39',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipobase`
--

DROP TABLE IF EXISTS `tipobase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipobase`
--

LOCK TABLES `tipobase` WRITE;
/*!40000 ALTER TABLE `tipobase` DISABLE KEYS */;
INSERT INTO `tipobase` VALUES (3,'TYPE_STATUS','S_ACTIVE','Active','Y','2017-09-18 22:53:52',NULL,NULL,NULL,0),(4,'TYPE_STATUS','S_INACTIVE','Inactive','Y','2017-09-18 22:54:13',NULL,NULL,NULL,0),(5,'TYPE_STATUS','S_NEW','New','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(6,'TYPE_PERMISSION','P_COMPONENT','Component of module','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(7,'TYPE_PERMISSION','P_ACTION','Action of component','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(8,'TYPE_ENTIDAD','E_PERSONA','Persona','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(9,'TYPE_ENTIDAD','E_ORGANIZACION','Organizacion','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(10,'TYPE_PERSONA_DOCUMENTO','P_DNI','Dni','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(11,'TYPE_PERSONA_ESTADO_CIVIL','P_SOLTERO','Soltero','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(12,'TYPE_PERSONA_ESTADO_CIVIL','P_CASADO','Casado','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(13,'TYPE_PERSONA_ESTADO_CIVIL','P_VIUDO','Viudo','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(14,'TYPE_PERSONA_ESTADO_CIVIL','P_DIVORCIADO','Divorciado','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(15,'TYPE_ORGANIZACION','O_PRIVADO','Privado','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(16,'TYPE_ORGANIZACION','O_PUBLICO','Publico','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(17,'TYPE_ENTIDADROL','ER_CLIENTE','Cliente','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(18,'TYPE_ENTIDADROL','ER_PROVEEDOR','Proveedor','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(19,'TYPE_ENTIDADROL','ER_USUARIO','Usuario','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(20,'TYPE_TELEFONO','TEL_FIJO','Fijo','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0),(21,'TYPE_TELEFONO','TEL_MOVIL','Movil','Y','2017-09-18 22:54:39',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `tipobase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubigeo`
--

DROP TABLE IF EXISTS `ubigeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ubigeo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` datetime DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `abreviatura` varchar(45) DEFAULT NULL,
  `tipo_ubigeo` varchar(45) DEFAULT NULL,
  `parent_ubigeo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ubigeo_ubigeo_idx` (`parent_ubigeo_id`),
  CONSTRAINT `fk_ubigeo_ubigeo` FOREIGN KEY (`parent_ubigeo_id`) REFERENCES `ubigeo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubigeo`
--

LOCK TABLES `ubigeo` WRITE;
/*!40000 ALTER TABLE `ubigeo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ubigeo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-23  0:37:10
