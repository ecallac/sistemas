CREATE DATABASE  IF NOT EXISTS `security` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `security`;
-- MySQL dump 10.13  Distrib 5.5.24, for osx10.5 (i386)
--
-- Host: localhost    Database: security
-- ------------------------------------------------------
-- Server version	5.5.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'SECURITY','Security Application','Y',NULL,'2018-01-22 18:42:58',NULL,NULL,54,'1','1.0.0.0');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,1,'root','root','/','Y','2017-05-14 22:51:47','2018-02-06 22:09:28',NULL,NULL,14,NULL,'Component'),(2,1,'home','home','/home','Y','2017-05-14 22:51:52','2018-01-08 23:02:28',NULL,NULL,2,NULL,'Component'),(3,1,'module','module management','/module','Y','2017-05-14 22:51:52','2018-01-08 23:02:27',NULL,NULL,2,NULL,'Component'),(4,1,'permission','permission management','/permission','Y','2017-05-14 22:51:52',NULL,NULL,NULL,0,NULL,'Component'),(5,1,'assigneRolePermission','assigneRolePermission','/assigneRolePermission','Y','2017-05-14 22:51:52','2017-10-09 20:05:40',NULL,NULL,6,4,'Component'),(6,1,'role','role management','/role','Y','2017-05-14 22:51:53',NULL,NULL,NULL,0,NULL,'Component'),(7,1,'user','user Management','/userList','Y','2017-05-14 22:51:53',NULL,NULL,NULL,0,NULL,'Component'),(8,1,'assigneRoleUser','assigneRoleUser','/assigneRoleUser','Y','2017-05-14 22:51:53','2017-10-09 20:07:53',NULL,NULL,2,7,'Component');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','Administrador','Y','2017-05-14 22:38:07','2018-01-08 22:52:27',NULL,NULL,3);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolepermission`
--

DROP TABLE IF EXISTS `rolepermission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolepermission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `fk_role_has_permission_permission1_idx` (`permission_id`),
  KEY `fk_role_has_permission_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_has_permission_permission1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_permission_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolepermission`
--

LOCK TABLES `rolepermission` WRITE;
/*!40000 ALTER TABLE `rolepermission` DISABLE KEYS */;
INSERT INTO `rolepermission` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8);
/*!40000 ALTER TABLE `rolepermission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `rolepermissionview`
--

DROP TABLE IF EXISTS `rolepermissionview`;
/*!50001 DROP VIEW IF EXISTS `rolepermissionview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `rolepermissionview` (
  `module_id` int(11),
  `permission_name` varchar(100),
  `permission_description` varchar(255),
  `path` varchar(100),
  `permission_enabled` varchar(3),
  `parent_permission_id` int(11),
  `permission_id` int(11),
  `role_id` int(11),
  `role_name` varchar(100),
  `role_description` varchar(255),
  `role_enabled` varchar(3)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `roleuser`
--

DROP TABLE IF EXISTS `roleuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleuser` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_role_has_user_user1_idx` (`user_id`),
  KEY `fk_role_has_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_has_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleuser`
--

LOCK TABLES `roleuser` WRITE;
/*!40000 ALTER TABLE `roleuser` DISABLE KEYS */;
INSERT INTO `roleuser` VALUES (1,1);
/*!40000 ALTER TABLE `roleuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `roleuserview`
--

DROP TABLE IF EXISTS `roleuserview`;
/*!50001 DROP VIEW IF EXISTS `roleuserview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `roleuserview` (
  `name` varchar(100),
  `description` varchar(255),
  `role_enabled` varchar(3),
  `role_id` int(11),
  `user_id` int(11),
  `username` varchar(50),
  `password` varchar(255),
  `user_status` varchar(45),
  `entidadrole_id` int(11),
  `question` varchar(255),
  `answer` varchar(255),
  `activationdate` datetime,
  `inactivationdate` datetime
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ecalla','$2a$10$gAzsxsY8QZyt2O0rfEoC2eUBHuJ0W0bgUIhyFTrMG7b/mjbQ3fdL6','Activo',NULL,'animal favorito','perro','2017-05-14 22:34:13',NULL,NULL,NULL,0,'2017-05-14 22:34:13','2017-05-31 22:34:06');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `rolepermissionview`
--

/*!50001 DROP TABLE IF EXISTS `rolepermissionview`*/;
/*!50001 DROP VIEW IF EXISTS `rolepermissionview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rolepermissionview` AS select `p`.`module_id` AS `module_id`,`p`.`name` AS `permission_name`,`p`.`description` AS `permission_description`,`p`.`path` AS `path`,`p`.`enabled` AS `permission_enabled`,`p`.`parent_permission_id` AS `parent_permission_id`,`rp`.`permission_id` AS `permission_id`,`rp`.`role_id` AS `role_id`,`r`.`name` AS `role_name`,`r`.`description` AS `role_description`,`r`.`enabled` AS `role_enabled` from ((`role` `r` join `rolepermission` `rp` on((`r`.`id` = `rp`.`role_id`))) join `permission` `p` on((`rp`.`permission_id` = `p`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `roleuserview`
--

/*!50001 DROP TABLE IF EXISTS `roleuserview`*/;
/*!50001 DROP VIEW IF EXISTS `roleuserview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `roleuserview` AS select `r`.`name` AS `name`,`r`.`description` AS `description`,`r`.`enabled` AS `role_enabled`,`ru`.`role_id` AS `role_id`,`ru`.`user_id` AS `user_id`,`u`.`username` AS `username`,`u`.`password` AS `password`,`u`.`status` AS `user_status`,`u`.`entidadrole_id` AS `entidadrole_id`,`u`.`question` AS `question`,`u`.`answer` AS `answer`,`u`.`activationDate` AS `activationdate`,`u`.`inactivationDate` AS `inactivationdate` from ((`role` `r` join `roleuser` `ru` on((`r`.`id` = `ru`.`role_id`))) join `user` `u` on((`ru`.`user_id` = `u`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-19 19:48:25
