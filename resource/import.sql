CREATE DATABASE  IF NOT EXISTS `meuvoto_parlamento` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `meuvoto_parlamento`;
-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: meuvoto_parlamento
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.14.04.1

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
-- Table structure for table `Cargo`
--

DROP TABLE IF EXISTS `Cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cargo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unidade` varchar(100) NOT NULL,
  `tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ip2vbqnvbkky0wlnk1vqgork9` (`tipo_id`),
  CONSTRAINT `FK_ip2vbqnvbkky0wlnk1vqgork9` FOREIGN KEY (`tipo_id`) REFERENCES `TipoCargo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cargo`
--

LOCK TABLES `Cargo` WRITE;
/*!40000 ALTER TABLE `Cargo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mandato`
--

DROP TABLE IF EXISTS `Mandato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mandato` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataFim` date NOT NULL,
  `dataInicio` date NOT NULL,
  `cargo_id` bigint(20) NOT NULL,
  `pessoa_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ccnl6pb4d7emq6dkh5vv6v6ps` (`cargo_id`),
  KEY `FK_sy86500enb1r8jgvx6eem0de0` (`pessoa_id`),
  CONSTRAINT `FK_ccnl6pb4d7emq6dkh5vv6v6ps` FOREIGN KEY (`cargo_id`) REFERENCES `Cargo` (`id`),
  CONSTRAINT `FK_sy86500enb1r8jgvx6eem0de0` FOREIGN KEY (`pessoa_id`) REFERENCES `Pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mandato`
--

LOCK TABLES `Mandato` WRITE;
/*!40000 ALTER TABLE `Mandato` DISABLE KEYS */;
/*!40000 ALTER TABLE `Mandato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoCargo`
--

DROP TABLE IF EXISTS `TipoCargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoCargo` (
  `id` int(11) NOT NULL,
  `duracao` int(11) DEFAULT NULL,
  `nomeCargo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ku4s8oaqtwxxecug9dd652j1d` (`nomeCargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoCargo`
--

LOCK TABLES `TipoCargo` WRITE;
/*!40000 ALTER TABLE `TipoCargo` DISABLE KEYS */;
INSERT INTO `TipoCargo` VALUES (1,48,'VEREADOR'),(2,48,'DEPUTADO ESTADUAL'),(3,48,'DEPUTADO FEDERAL'),(4,96,'SENADOR');
/*!40000 ALTER TABLE `TipoCargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'meuvoto_parlamento'
--

--
-- Dumping routines for database 'meuvoto_parlamento'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-04 12:21:48
