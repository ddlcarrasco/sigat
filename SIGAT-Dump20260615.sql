-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: sigat
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `bitacora`
--

DROP TABLE IF EXISTS `bitacora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bitacora` (
  `idbitacora` bigint unsigned NOT NULL AUTO_INCREMENT,
  `fecha_hora` datetime DEFAULT NULL,
  `accion` varchar(50) DEFAULT NULL,
  `entidad_afectada` varchar(50) DEFAULT NULL,
  `detalle` json DEFAULT NULL,
  `ip_origen` varchar(45) DEFAULT NULL,
  `usuario_idusuario` bigint unsigned NOT NULL,
  PRIMARY KEY (`idbitacora`),
  KEY `fk_bitacora_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_bitacora_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bitacora`
--

LOCK TABLES `bitacora` WRITE;
/*!40000 ALTER TABLE `bitacora` DISABLE KEYS */;
INSERT INTO `bitacora` VALUES (1,'2026-05-25 23:44:19','CREATE','contrato','{\"idcontrato\": 1, \"numero_contrato\": \"TLX-2024-0001\"}','192.168.1.10',1),(2,'2026-05-25 23:44:19','UPDATE','contrato','{\"campo\": \"estado_contrato\", \"nuevo\": \"Suspendido\", \"anterior\": \"Activo\", \"idcontrato\": 1}','192.168.1.10',1);
/*!40000 ALTER TABLE `bitacora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `activo` tinyint DEFAULT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Domestico','Uso residencial del servicio de agua potable',1),(2,'Comercial','Uso comercial del servicio de agua potable',1);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contrato` (
  `idcontrato` bigint unsigned NOT NULL AUTO_INCREMENT,
  `numero_contrato` varchar(45) DEFAULT NULL,
  `numero_catastro` varchar(50) DEFAULT NULL,
  `domicilio_toma` varchar(300) NOT NULL,
  `fecha_instalacion` date DEFAULT NULL,
  `observaciones` varchar(1000) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `titular_idtitular` bigint NOT NULL,
  `sector_idsector` bigint unsigned NOT NULL,
  `categoria_idcategoria` bigint unsigned NOT NULL,
  `estado_contrato_idestado_contrato` bigint NOT NULL,
  `usuarioInstalador_idusuario` bigint unsigned NOT NULL,
  PRIMARY KEY (`idcontrato`),
  UNIQUE KEY `numero_contrato_UNIQUE` (`numero_contrato`),
  KEY `fk_contrato_titular1_idx` (`titular_idtitular`),
  KEY `fk_contrato_sector1_idx` (`sector_idsector`),
  KEY `fk_contrato_categoria1_idx` (`categoria_idcategoria`),
  KEY `fk_contrato_estado_contrato1_idx` (`estado_contrato_idestado_contrato`),
  KEY `fk_contrato_usuario1_idx` (`usuarioInstalador_idusuario`),
  CONSTRAINT `fk_contrato_categoria1` FOREIGN KEY (`categoria_idcategoria`) REFERENCES `categoria` (`idcategoria`),
  CONSTRAINT `fk_contrato_estado_contrato1` FOREIGN KEY (`estado_contrato_idestado_contrato`) REFERENCES `estado_contrato` (`idestado_contrato`),
  CONSTRAINT `fk_contrato_sector1` FOREIGN KEY (`sector_idsector`) REFERENCES `sector` (`idsector`),
  CONSTRAINT `fk_contrato_titular1` FOREIGN KEY (`titular_idtitular`) REFERENCES `titular` (`idtitular`),
  CONSTRAINT `fk_contrato_usuario1` FOREIGN KEY (`usuarioInstalador_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (1,'TLX-2024-0001','CAT-001','Calle Hidalgo 12, Centro, Tlaxco','2024-03-15',NULL,'2026-05-25 23:17:04',1,1,1,1,1),(2,'TLX-2024-0002','CAT-002','Av. Juarez 45, Colonia Norte, Tlaxco','2024-06-20','Toma en esquina de lote','2026-05-25 23:17:04',2,2,2,1,2),(3,'ADP-00000003','CAT-003-003','Ferrocarril 65','2026-06-08','Nuevo','2026-06-09 03:30:41',3,2,1,1,1),(4,'ADP-00000004','CTR1234688','Progreso 128','2026-06-12','Nueva toma ejemplo','2026-06-12 18:03:53',4,1,1,3,3),(5,'ADP-00000005','CAT-123456789','Calle V. Carranza, Centro','2026-06-15','Prueba','2026-06-16 05:18:21',5,1,1,1,3);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato_descuento`
--

DROP TABLE IF EXISTS `contrato_descuento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contrato_descuento` (
  `idcontrato_descuento` bigint unsigned NOT NULL AUTO_INCREMENT,
  `contrato_idcontrato` bigint unsigned NOT NULL,
  `tipo_descuento_idtipo_descuento` bigint unsigned NOT NULL,
  PRIMARY KEY (`idcontrato_descuento`),
  KEY `fk_contrato_descuento_tipo_descuento1_idx` (`tipo_descuento_idtipo_descuento`),
  KEY `fk_contrato_descuento_contrato1_idx` (`contrato_idcontrato`),
  CONSTRAINT `fk_contrato_descuento_contrato1` FOREIGN KEY (`contrato_idcontrato`) REFERENCES `contrato` (`idcontrato`),
  CONSTRAINT `fk_contrato_descuento_tipo_descuento1` FOREIGN KEY (`tipo_descuento_idtipo_descuento`) REFERENCES `tipo_descuento` (`idtipo_descuento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato_descuento`
--

LOCK TABLES `contrato_descuento` WRITE;
/*!40000 ALTER TABLE `contrato_descuento` DISABLE KEYS */;
INSERT INTO `contrato_descuento` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `contrato_descuento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_contrato`
--

DROP TABLE IF EXISTS `estado_contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_contrato` (
  `idestado_contrato` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idestado_contrato`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_contrato`
--

LOCK TABLES `estado_contrato` WRITE;
/*!40000 ALTER TABLE `estado_contrato` DISABLE KEYS */;
INSERT INTO `estado_contrato` VALUES (1,'Activo','El contrato esta vigente y el servicio se presta con normalidad'),(2,'Suspendido','El servicio esta suspendido temporalmente por falta de pago u otro motivo'),(3,'Nuevo Contrato','El servicio se dio de alta en el sistema. Aun no instalado fisicamente.');
/*!40000 ALTER TABLE `estado_contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_recibo`
--

DROP TABLE IF EXISTS `estado_recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_recibo` (
  `idestado_recibo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idestado_recibo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_recibo`
--

LOCK TABLES `estado_recibo` WRITE;
/*!40000 ALTER TABLE `estado_recibo` DISABLE KEYS */;
INSERT INTO `estado_recibo` VALUES (1,'Pendiente','El recibo fue emitido y esta esperando ser pagado'),(2,'Pagado','El recibo fue liquidado correctamente');
/*!40000 ALTER TABLE `estado_recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_tramite`
--

DROP TABLE IF EXISTS `estado_tramite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_tramite` (
  `idestado_tramite` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idestado_tramite`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_tramite`
--

LOCK TABLES `estado_tramite` WRITE;
/*!40000 ALTER TABLE `estado_tramite` DISABLE KEYS */;
INSERT INTO `estado_tramite` VALUES (1,'Pendiente','El tramite fue recibido y esta en espera de ser revisado'),(2,'VoBo Aprobado','El Director autorizó el trámite'),(3,'Rechazado','El trámite fue rechazado'),(4,'Resuelto','El trámite fue ejecutado y cerrado');
/*!40000 ALTER TABLE `estado_tramite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial_estado_contrato`
--

DROP TABLE IF EXISTS `historial_estado_contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial_estado_contrato` (
  `idhistorial_estado_contrato` bigint unsigned NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `observaciones` text,
  `contrato_idcontrato` bigint unsigned NOT NULL,
  `estado_contratoAnterior_idestado_contrato` bigint NOT NULL,
  `estado_contratoNuevo_idestado_contrato` bigint NOT NULL,
  `usuario_idusuario` bigint unsigned NOT NULL,
  PRIMARY KEY (`idhistorial_estado_contrato`),
  KEY `fk_historial_estado_contrato_contrato1_idx` (`contrato_idcontrato`),
  KEY `fk_historial_estado_contrato_estado_contrato1_idx` (`estado_contratoAnterior_idestado_contrato`),
  KEY `fk_historial_estado_contrato_estado_contrato2_idx` (`estado_contratoNuevo_idestado_contrato`),
  KEY `fk_historial_estado_contrato_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_historial_estado_contrato_contrato1` FOREIGN KEY (`contrato_idcontrato`) REFERENCES `contrato` (`idcontrato`),
  CONSTRAINT `fk_historial_estado_contrato_estado_contrato1` FOREIGN KEY (`estado_contratoAnterior_idestado_contrato`) REFERENCES `estado_contrato` (`idestado_contrato`),
  CONSTRAINT `fk_historial_estado_contrato_estado_contrato2` FOREIGN KEY (`estado_contratoNuevo_idestado_contrato`) REFERENCES `estado_contrato` (`idestado_contrato`),
  CONSTRAINT `fk_historial_estado_contrato_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_estado_contrato`
--

LOCK TABLES `historial_estado_contrato` WRITE;
/*!40000 ALTER TABLE `historial_estado_contrato` DISABLE KEYS */;
INSERT INTO `historial_estado_contrato` VALUES (1,'2026-05-25 23:41:25','Suspension por falta de pago acumulado de 3 meses',1,1,2,1),(2,'2026-05-25 23:41:25','Reactivacion tras liquidar adeudo completo',1,2,1,1),(3,'2026-06-12 17:58:12','...',3,3,1,1),(4,'2026-06-16 05:19:53','Prueba',5,3,1,4),(5,'2026-06-16 05:27:49','Prueba',5,1,3,4);
/*!40000 ALTER TABLE `historial_estado_contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motivo_recibo`
--

DROP TABLE IF EXISTS `motivo_recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `motivo_recibo` (
  `idmotivo_recibo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idmotivo_recibo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motivo_recibo`
--

LOCK TABLES `motivo_recibo` WRITE;
/*!40000 ALTER TABLE `motivo_recibo` DISABLE KEYS */;
INSERT INTO `motivo_recibo` VALUES (1,'Cobro mensual','Cargo mensual ordinario por el servicio de agua potable'),(2,'Cargo por reconexion','Cargo generado al reactivar el servicio tras una suspension');
/*!40000 ALTER TABLE `motivo_recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `idpago` bigint unsigned NOT NULL AUTO_INCREMENT,
  `folio` varchar(45) NOT NULL,
  `monto_recibido` decimal(10,2) NOT NULL,
  `fecha_pago` datetime NOT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `tipo_pago_idtipo_pago` bigint unsigned NOT NULL,
  `usuario_idusuario` bigint unsigned NOT NULL,
  PRIMARY KEY (`idpago`),
  UNIQUE KEY `folio_UNIQUE` (`folio`),
  KEY `fk_pago_tipo_pago1_idx` (`tipo_pago_idtipo_pago`),
  KEY `fk_pago_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_pago_tipo_pago1` FOREIGN KEY (`tipo_pago_idtipo_pago`) REFERENCES `tipo_pago` (`idtipo_pago`),
  CONSTRAINT `fk_pago_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES (3,'TLX-PAG-000001',120.00,'2026-05-25 23:34:07',NULL,1,2),(4,'TLX-PAG-000002',500.00,'2026-05-25 23:34:07','Pago de 2 meses pendientes',2,2),(5,'AP-00000007',120.00,'2026-06-12 19:46:05','Pago prueba',1,1),(6,'F-ASDA',120.00,'2026-06-16 05:28:56','',1,4);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo` (
  `idrecibo` bigint unsigned NOT NULL AUTO_INCREMENT,
  `mes` tinyint DEFAULT NULL,
  `anio` smallint DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `contrato_idcontrato` bigint unsigned NOT NULL,
  `tarifa_idtarifa` int NOT NULL,
  `motivo_recibo_idmotivo_recibo` int NOT NULL,
  `estado_recibo_idestado_recibo` int NOT NULL,
  `pago_idpago` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`idrecibo`),
  KEY `fk_recibo_contrato1_idx` (`contrato_idcontrato`),
  KEY `fk_recibo_tarifa1_idx` (`tarifa_idtarifa`),
  KEY `fk_recibo_motivo_recibo1_idx` (`motivo_recibo_idmotivo_recibo`),
  KEY `fk_recibo_estado_recibo1_idx` (`estado_recibo_idestado_recibo`),
  KEY `fk_recibo_pago1_idx` (`pago_idpago`),
  CONSTRAINT `fk_recibo_contrato1` FOREIGN KEY (`contrato_idcontrato`) REFERENCES `contrato` (`idcontrato`),
  CONSTRAINT `fk_recibo_estado_recibo1` FOREIGN KEY (`estado_recibo_idestado_recibo`) REFERENCES `estado_recibo` (`idestado_recibo`),
  CONSTRAINT `fk_recibo_motivo_recibo1` FOREIGN KEY (`motivo_recibo_idmotivo_recibo`) REFERENCES `motivo_recibo` (`idmotivo_recibo`),
  CONSTRAINT `fk_recibo_pago1` FOREIGN KEY (`pago_idpago`) REFERENCES `pago` (`idpago`),
  CONSTRAINT `fk_recibo_tarifa1` FOREIGN KEY (`tarifa_idtarifa`) REFERENCES `tarifa` (`idtarifa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
INSERT INTO `recibo` VALUES (3,5,2026,120.00,'2026-05-01','2026-05-15',1,1,1,2,3),(4,5,2026,250.00,'2026-05-01','2026-05-15',2,2,1,1,NULL),(5,6,2026,120.00,'2026-06-12','2026-06-12',4,1,1,2,5),(6,6,2026,120.00,'2026-06-15','2026-06-15',5,1,1,2,6);
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idrol` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMIN','Administrador del sistema con acceso total'),(2,'DIRECTOR','Director de la dependencia'),(3,'OPERADOR','Personal que realiza los pagos y tramites'),(4,'TECNICO','Resuelve los problemas de las tomas'),(5,'CONSULTA','Consulta');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sector` (
  `idsector` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `activo` tinyint DEFAULT NULL,
  PRIMARY KEY (`idsector`),
  UNIQUE KEY `idsector_UNIQUE` (`idsector`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (1,'Centro','Zona centro del municipio de Tlaxco',1),(2,'Colonia Norte','Colonia ubicada al norte del municipio',1);
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarifa` (
  `idtarifa` int NOT NULL AUTO_INCREMENT,
  `monto_mensual` decimal(10,2) NOT NULL,
  `fechaDesde` date DEFAULT NULL,
  `fechaHasta` date DEFAULT NULL,
  `activa` tinyint DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `sector_idsector` bigint unsigned NOT NULL,
  `categoria_idcategoria` bigint unsigned NOT NULL,
  PRIMARY KEY (`idtarifa`),
  KEY `fk_tarifa_sector1_idx` (`sector_idsector`),
  KEY `fk_tarifa_categoria1_idx` (`categoria_idcategoria`),
  CONSTRAINT `fk_tarifa_categoria1` FOREIGN KEY (`categoria_idcategoria`) REFERENCES `categoria` (`idcategoria`),
  CONSTRAINT `fk_tarifa_sector1` FOREIGN KEY (`sector_idsector`) REFERENCES `sector` (`idsector`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES (1,120.00,'2024-01-01',NULL,1,'Tarifa vigente para uso domestico en zona Centro',1,1),(2,250.00,'2024-01-01',NULL,1,'Tarifa vigente para uso comercial en Colonia Norte',2,2);
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_descuento`
--

DROP TABLE IF EXISTS `tipo_descuento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_descuento` (
  `idtipo_descuento` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) DEFAULT NULL,
  `porcentaje` decimal(5,2) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `activo` tinyint DEFAULT NULL,
  PRIMARY KEY (`idtipo_descuento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_descuento`
--

LOCK TABLES `tipo_descuento` WRITE;
/*!40000 ALTER TABLE `tipo_descuento` DISABLE KEYS */;
INSERT INTO `tipo_descuento` VALUES (1,'Adulto Mayor',50.00,'Descuento para titulares mayores de 60 anios',1),(2,'Persona con Discapacidad',25.00,'Descuento para titulares con algun tipo de discapacidad',1);
/*!40000 ALTER TABLE `tipo_descuento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_pago` (
  `idtipo_pago` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtipo_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pago`
--

LOCK TABLES `tipo_pago` WRITE;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` VALUES (1,'Efectivo','Pago realizado en efectivo en caja'),(2,'Transferencia bancaria','Pago realizado mediante transferencia electronica');
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_tramite`
--

DROP TABLE IF EXISTS `tipo_tramite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_tramite` (
  `idtipo_tramite` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `requiere_vobo` tinyint NOT NULL,
  PRIMARY KEY (`idtipo_tramite`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_tramite`
--

LOCK TABLES `tipo_tramite` WRITE;
/*!40000 ALTER TABLE `tipo_tramite` DISABLE KEYS */;
INSERT INTO `tipo_tramite` VALUES (1,'Cambio de titular','Solicitud para transferir el contrato a un nuevo titular',1),(2,'Reconexion','Solicitud para reactivar el servicio tras una suspension',0),(3,'Nuevo Contrato','Solicitud para activar un nuevo contrato',1);
/*!40000 ALTER TABLE `tipo_tramite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titular`
--

DROP TABLE IF EXISTS `titular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `titular` (
  `idtitular` bigint NOT NULL AUTO_INCREMENT,
  `curp` varchar(18) DEFAULT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) DEFAULT NULL,
  `tipo_identificacion` varchar(50) DEFAULT NULL,
  `numero_identificacion` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(150) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  PRIMARY KEY (`idtitular`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titular`
--

LOCK TABLES `titular` WRITE;
/*!40000 ALTER TABLE `titular` DISABLE KEYS */;
INSERT INTO `titular` VALUES (1,'GARZ850312HTLRML09','Roberto','Garza','Ramirez','INE','TLX8503120001','2461001122','roberto.garza@email.com','2026-05-25 23:14:34'),(2,'LOPF920715MDFPZR04','Fernanda','Lopez','Fuentes','INE','TLX9207150002','2461003344','fernanda.lopez@email.com','2026-05-25 23:14:34'),(3,'LOCD931120HSRPRR09','Dario Daniel','Lopez','Carrasco','INE','112452412254','8120226725','dariolopez@correo.com','2026-06-09 03:18:57'),(4,'lxca971120hsrprr07','Jose Antonio','Lopez','Carrasco','INE','5545245542','2411587741','pp@correo.com','2026-06-12 17:46:12'),(5,'SAEM700515MNLNSR08','Mirna','De Los Santos','Escareño','INE','123456789','8112457885','mirna@correo.com','2026-06-16 04:30:38');
/*!40000 ALTER TABLE `titular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tramite`
--

DROP TABLE IF EXISTS `tramite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tramite` (
  `idtramite` bigint unsigned NOT NULL AUTO_INCREMENT,
  `datos_propuestos` json DEFAULT NULL,
  `fecha_solicitud` datetime DEFAULT NULL,
  `fecha_resolucion` datetime DEFAULT NULL,
  `observaciones_solicitud` varchar(500) DEFAULT NULL,
  `observaciones_resolucion` varchar(500) DEFAULT NULL,
  `tipo_tramite_idtipo_tramite` bigint unsigned NOT NULL,
  `contrato_idcontrato` bigint unsigned NOT NULL,
  `estado_tramite_idestado_tramite` bigint unsigned NOT NULL,
  `usuarioSolicitante_idusuario` bigint unsigned NOT NULL,
  `usuarioResolutor_idusuario` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`idtramite`),
  KEY `fk_tramite_tipo_tramite1_idx` (`tipo_tramite_idtipo_tramite`),
  KEY `fk_tramite_contrato1_idx` (`contrato_idcontrato`),
  KEY `fk_tramite_estado_tramite1_idx` (`estado_tramite_idestado_tramite`),
  KEY `fk_tramite_usuario1_idx` (`usuarioSolicitante_idusuario`),
  KEY `fk_tramite_usuario2_idx` (`usuarioResolutor_idusuario`),
  CONSTRAINT `fk_tramite_contrato1` FOREIGN KEY (`contrato_idcontrato`) REFERENCES `contrato` (`idcontrato`),
  CONSTRAINT `fk_tramite_estado_tramite1` FOREIGN KEY (`estado_tramite_idestado_tramite`) REFERENCES `estado_tramite` (`idestado_tramite`),
  CONSTRAINT `fk_tramite_tipo_tramite1` FOREIGN KEY (`tipo_tramite_idtipo_tramite`) REFERENCES `tipo_tramite` (`idtipo_tramite`),
  CONSTRAINT `fk_tramite_usuario1` FOREIGN KEY (`usuarioSolicitante_idusuario`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `fk_tramite_usuario2` FOREIGN KEY (`usuarioResolutor_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tramite`
--

LOCK TABLES `tramite` WRITE;
/*!40000 ALTER TABLE `tramite` DISABLE KEYS */;
INSERT INTO `tramite` VALUES (1,'{\"motivo\": \"venta del predio\", \"nuevoTitularId\": 2}','2026-05-25 23:39:39',NULL,'Solicitud de cambio por venta',NULL,1,1,1,2,NULL),(2,NULL,'2026-05-25 23:39:39','2026-06-12 20:03:35',NULL,'',2,2,4,2,3),(3,'{\"titular\": \"Mirna de los santos\", \"tramite\": \"nuevo contrato\"}','2026-06-16 05:37:21','2026-06-16 05:40:14',' ','',3,5,4,4,3),(4,'{}','2026-06-16 05:41:02',NULL,'',NULL,3,4,1,3,NULL);
/*!40000 ALTER TABLE `tramite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `nombres` varchar(150) NOT NULL,
  `apellidoPaterno` varchar(150) NOT NULL,
  `apellidoMaterno` varchar(150) DEFAULT NULL,
  `correo` varchar(150) DEFAULT NULL,
  `activo` tinyint DEFAULT NULL,
  `fechaAlta` datetime DEFAULT NULL,
  `rol_idrol` bigint unsigned NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuario_rol_idx` (`rol_idrol`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','$2b$10$7bcVfzEv/V8JwlJpo54.FeV75o4taHRpBjbJKsLgCp0a4eZOfivXG','Carlos','Mendoza','Rios','admin@sigat.com',1,'2026-05-25 22:17:46',1),(2,'cajero1','$2a$10$abcdefghijklmnopqrstuuVfjkl1234567890abcdefghijklmnopq','Patricia','Lopez','Vargas','cajero@sigat.com',1,'2026-05-25 22:17:46',2),(3,'Fontanero','$2a$10$cNe.G18md1.QT00tRkFhEuGuHiTV9SnKBaghDEn.Q0kkLXfbTs8Rm','Pedro','Perez','Nuñes','pedro@sigat.com',1,'2026-06-12 12:20:00',4),(4,'Operador','$2a$10$1kw8M4EBfehb8r1segJO7u3tnY62Fc9HEjrUO7xRXUFrhM275Ek1a','Maria Sonia','Carrasco','Espino','msce@sigat.com',1,'2026-06-13 16:39:00',3),(5,'Presidente','$2a$10$XtQ5wXMMAG0z72HyJykS1ukUvNMHtJhmgh3jCEWLu5uiRI6tcCwGm','Victor Manuel','De Los Santos','Escareño','victor@sigat.com',1,'2026-06-16 04:06:18',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-15 23:46:17
