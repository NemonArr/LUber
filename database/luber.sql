-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: luber
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `conductor`
--

DROP TABLE IF EXISTS `conductor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conductor` (
  `usu_cc` int NOT NULL,
  `veh_placa` char(7) DEFAULT NULL,
  PRIMARY KEY (`usu_cc`),
  KEY `veh_placa` (`veh_placa`),
  CONSTRAINT `conductor_ibfk_1` FOREIGN KEY (`usu_cc`) REFERENCES `usuario` (`usu_cc`),
  CONSTRAINT `conductor_ibfk_2` FOREIGN KEY (`veh_placa`) REFERENCES `vehiculo` (`veh_placa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conductor`
--

LOCK TABLES `conductor` WRITE;
/*!40000 ALTER TABLE `conductor` DISABLE KEYS */;
INSERT INTO `conductor` VALUES (55488445,'KGH 211'),(987367363,'KGH 211'),(1031132822,'KGH 211'),(4548844,'KLM 565'),(325555451,'KLM 565'),(55844775,'LCS 364'),(76636633,'LCS 364'),(64588522,'PET 363'),(65544788,'PET 363');
/*!40000 ALTER TABLE `conductor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_name` varchar(15) NOT NULL,
  `hash` varchar(64) NOT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usu_cc` int NOT NULL,
  `usu_nombre` char(20) DEFAULT NULL,
  `usu_apellido` char(20) DEFAULT NULL,
  `usu_email` char(60) DEFAULT NULL,
  PRIMARY KEY (`usu_cc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (123456,'pepew','lota','pelota@mail.com'),(4548844,'Claudia','Mendez','cmendez@mail.com'),(5584669,'Paola','Angulo','pangulo@mail.com'),(35448452,'Pedro','Perez','pperez@mail.com'),(55488445,'Luis','Rojas','lrojas@mail.com'),(55844775,'Andres','Cruz','acruz@mail.com'),(55987784,'Viviana','Henao','vhenao@mail.com'),(64588522,'Ana','Lopez','alopez@mail.com'),(65448733,'Ana','Riascos','ariascos@mail.com'),(65544788,'Carlos','Penagos','cpenagos@mail.com'),(76636633,'Nelson','Ruiz','nruiz@mail.com'),(325555451,'Maria','Lopez','mlopez@mail.com'),(454671556,'Camilo','Garcia','cgarcia@mail.com'),(987367363,'Jorge','Rodriguez','jrodriguez@mail.com'),(987445125,'Fredy','Bustos','fbustos@mail.com'),(1031132822,'Johann','Guayacundo','jjguayacundoa@gmail.com');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `veh_placa` char(7) NOT NULL,
  `veh_marca` char(15) NOT NULL,
  `veh_modelo` char(15) NOT NULL,
  `veh_anio` smallint NOT NULL,
  `veh_capacidad` tinyint NOT NULL,
  `veh_color` char(15) NOT NULL,
  `veh_kilometros` int NOT NULL,
  PRIMARY KEY (`veh_placa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES ('CUL 073','Renault','duster',2006,5,'blanco',220000),('GEM 666','Wolkswagen','Bettle',1990,4,'Azul',0),('KGH 211','Kia','picanto',2015,5,'azul',80000),('KLM 565','Chevrolet','spark',2015,5,'amarillo',120000),('LCS 364','Chevrolet','spark',2016,5,'blanco',125000),('PEL 456','Renault','duster',2015,5,'blanco',130000),('PET 363','Kia','rio',2019,5,'blanco',150000),('VOJ 232','Ferrari','Testarosa',2000,3,'Rojo',0);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaje`
--

DROP TABLE IF EXISTS `viaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viaje` (
  `via_id` int NOT NULL AUTO_INCREMENT,
  `usu_cc` int NOT NULL,
  `con_cc` int NOT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`via_id`),
  KEY `usu_cc` (`usu_cc`),
  KEY `con_cc` (`con_cc`),
  CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`usu_cc`) REFERENCES `usuario` (`usu_cc`),
  CONSTRAINT `viaje_ibfk_2` FOREIGN KEY (`con_cc`) REFERENCES `conductor` (`usu_cc`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES (1,65448733,4548844,'2017-10-25 20:00:00'),(2,65448733,325555451,'2019-03-15 18:30:00'),(3,65448733,55844775,'2019-05-20 20:30:00'),(4,35448452,4548844,'2018-05-20 20:30:00'),(5,35448452,55844775,'2020-01-20 20:30:00'),(6,325555451,64588522,'2019-05-20 20:30:00'),(7,325555451,65544788,'2018-06-22 21:30:00'),(8,325555451,55488445,'2020-03-17 15:30:20'),(9,64588522,55488445,'2020-03-17 15:30:20'),(10,64588522,76636633,'2020-04-10 18:30:20'),(11,55488445,987367363,'2020-02-17 20:30:20'),(12,55488445,55844775,'2020-02-20 16:30:20'),(13,55488445,64588522,'2020-03-27 18:30:20'),(14,55844775,64588522,'2020-03-20 21:30:20'),(15,76636633,64588522,'2020-01-10 17:30:20'),(16,76636633,325555451,'2020-02-15 20:30:20'),(17,76636633,987367363,'2020-03-17 18:30:20'),(18,325555451,55844775,'2021-09-04 11:26:00'),(19,65448733,76636633,'2021-09-06 23:35:45'),(20,325555451,55844775,'2021-09-06 23:38:58');
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-07 18:56:05
