-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: mrcore
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id_profile` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(128) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `first_name` varchar(128) NOT NULL,
  `last_name` varchar(128) CHARACTER SET big5 NOT NULL,
  `email` varchar(128) NOT NULL,
  `id_city` int(11) NOT NULL,
  `id_state` int(11) NOT NULL,
  `id_country` int(11) NOT NULL,
  `zip_code` varchar(45) NOT NULL,
  `id_sex` int(11) NOT NULL,
  `birthday_date` datetime NOT NULL,
  `last_modification_date` datetime NOT NULL,
  `creation_date` datetime NOT NULL,
  PRIMARY KEY (`id_profile`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (6,'PRIVILEGED_MEMBER','username2','password2','first_name1','last_name','email',10737,46,2,'zip_code',1,'1991-04-15 17:26:43','2016-09-12 20:03:46','2016-09-12 20:03:46'),(7,'MEMBER','cool','$2a$10$7Kc/4d9X6UEvgkczawVDW.znOkJu5Off4LQ9fmLLjjXS99VwGeBU6','first_name1','last_name','email',10736,673,38,'zip_code',1,'1991-04-15 17:26:43','2016-09-15 12:18:12','2016-09-15 12:18:12'),(8,'MEMBER','cool2','$2a$10$5HkRqbrkUglQ1.0QkhHEeuigpWDLAiNSiX9joMv8duhPsIlfuVhDy','first_name1','last_name','email',10736,673,38,'zip_code',1,'1991-04-15 17:26:43','2016-09-15 12:18:31','2016-09-15 12:18:31'),(9,'MEMBER','cool3','$2a$10$13bLb/pwLwu0ZGnnqGpcbeUEB.mOLM79SYqbJs0s4HSfh/nEDfXdC','vraiment','vraiment','etibou99@gmail.com',10736,673,38,'zip_cod2e',1,'1991-04-15 17:26:43','2016-09-15 12:58:24','2016-09-15 12:58:24'),(10,'MEMBER','test123','$2a$10$YMocRSlVr2LEEz6Isf9ohODUb3KC4k7oUFMJZ8Jfux0rGcV3txDDC','DD','DD','F@GMAIL.COM',6180,123,3,'J2W 1K5',1,'2016-11-10 00:00:00','2016-11-02 14:29:34','2016-11-02 14:29:34'),(11,'MEMBER','salut','$2a$10$IMesx2HuDJDMbRBzLIp4yOEPZ6X0Pk7KNJiLJQTLmdXhTP0m0GysO','moron234','moron2346','toto@ll.com',48172,3957,231,'jambon',2,'1983-06-16 00:00:00','2016-11-02 14:49:57','2016-11-02 14:49:57'),(12,'MEMBER','d','$2a$10$wuW6aN10NOQQGbOYd/fPVOdGSdLKjKBne86IgYyV.2tIpAFgGF4vi','d','d','d@d.com',7252,317,16,'d',2,'2016-11-09 00:00:00','2016-11-02 15:06:23','2016-11-02 15:06:23'),(13,'MEMBER','salut2','$2a$10$EpWDGHQec7srQs8St9dfwehlzp8MUJeII23b1LVcBfMmPj7Cnwe.2','qwwqfqf','qfqwf','etibou99@weg.com',7316,359,18,'qqwf',2,'1983-04-15 00:00:00','2016-11-04 18:51:54','2016-11-04 18:51:54'),(14,'MEMBER','allo','$2a$10$s75b.iS1db2nXfFUrO9nB.tBkPHTSG6.uL.2T1hdhKtgcKyETQ/6W','allo','allo','allo@gmail.com',6460,178,6,'allo',3,'1962-07-18 00:00:00','2016-11-05 14:57:56','2016-11-05 14:57:56'),(15,'MEMBER','b','$2a$10$7LXuKLk9CL3sTby3yI1OW.WtneRyHckwn2Y30QP4mvqp2kcwwaUdW','bl','b','b@b.com',6110,116,3,'b',3,'1977-11-25 00:00:00','2016-11-05 15:00:01','2016-11-05 15:00:01'),(16,'MEMBER','mariecool903','$2a$10$Zn//.Tl.KOnKf9dSZaY0Ze4MF6HKQRknpJ3imkQWyG3hQEvFE5HI.','marie-soleil','bouffard','toto@gmail.com',10565,673,38,'J2W 1K4',2,'1994-07-14 00:00:00','2016-11-05 15:39:16','2016-11-05 15:39:16'),(17,'MEMBER','mariecool904','$2a$10$be450hJl9e4SppioL.a6LubUchRt59hcNy3sdkW9OeKCYYLMickIa','ALLO','ALLO','mariecool903@G.COM',6438,167,4,'RQ',2,'1976-07-15 00:00:00','2016-11-05 15:40:22','2016-11-05 15:40:22');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-06 17:17:38
