-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2016 at 10:27 PM
-- Server version: 5.1.58
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `a6794339_health`
--

-- --------------------------------------------------------

--
-- Table structure for table `actividadfisica`
--

CREATE TABLE `actividadfisica` (
  `actividad_id` int(11) NOT NULL,
  `actividad_descr` text COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`actividad_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `actividadfisica`
--

INSERT INTO `actividadfisica` VALUES(200, 'Caminar 10 minutos');
INSERT INTO `actividadfisica` VALUES(201, 'Estiramiento');
INSERT INTO `actividadfisica` VALUES(202, 'Caminar 30 minutos');

-- --------------------------------------------------------

--
-- Table structure for table `actividadinformacion`
--

CREATE ALGORITHM=UNDEFINED DEFINER=`a6794339_yojhan`@`localhost` SQL SECURITY DEFINER VIEW `a6794339_health`.`actividadinformacion` AS select `u`.`usuario_id` AS `usuario_id`,`a`.`actividad_id` AS `actividad_id`,`a`.`actividad_descr` AS `actividad_descr`,`ua`.`fecha_final` AS `fecha_final` from ((`a6794339_health`.`usuario` `u` join `a6794339_health`.`actividadfisica` `a`) join `a6794339_health`.`usuario_actividad` `ua`) where ((`u`.`usuario_id` = `ua`.`usuario_id`) and (`a`.`actividad_id` = `ua`.`actividad_id`));

--
-- Dumping data for table `actividadinformacion`
--

INSERT INTO `actividadinformacion` VALUES(1001, 200, 'Caminar 10 minutos', '2015-09-30 09:00:00');
INSERT INTO `actividadinformacion` VALUES(1001, 201, 'Estiramiento', '2015-09-25 10:00:00');
INSERT INTO `actividadinformacion` VALUES(1002, 202, 'Caminar 30 minutos', '2015-09-30 11:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `cita`
--

CREATE TABLE `cita` (
  `cita_id` int(5) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(15) NOT NULL,
  `cita_fecha` datetime NOT NULL,
  `sala_id` int(5) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`cita_id`),
  KEY `id_usuario` (`usuario_id`),
  KEY `sala_id` (`sala_id`),
  KEY `hospital_id` (`doctor_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `cita`
--

INSERT INTO `cita` VALUES(2, 1001, '2015-06-24 17:00:00', 110, 2001);
INSERT INTO `cita` VALUES(3, 1002, '2015-08-30 11:30:00', 310, 2002);
INSERT INTO `cita` VALUES(4, 1001, '2015-08-30 12:30:00', 510, 2003);
INSERT INTO `cita` VALUES(5, 1001, '2015-09-30 11:30:00', 212, 2001);
INSERT INTO `cita` VALUES(6, 1001, '2015-09-10 09:00:00', 310, 2002);

-- --------------------------------------------------------

--
-- Table structure for table `citainformacion`
--

CREATE ALGORITHM=UNDEFINED DEFINER=`a6794339_yojhan`@`localhost` SQL SECURITY DEFINER VIEW `a6794339_health`.`citainformacion` AS select `u`.`usuario_id` AS `usuario_id`,`c`.`cita_id` AS `cita_id`,`c`.`cita_fecha` AS `cita_fecha`,`h`.`hospital_nombre` AS `hospital_nombre`,`h`.`hospital_direccion` AS `hospital_direccion`,`h`.`hospital_telefono` AS `hospital_telefono`,`s`.`sala_id` AS `sala_id`,`d`.`doctor_nombre` AS `doctor_nombre`,`d`.`doctor_apellido` AS `doctor_apellido` from ((((`a6794339_health`.`usuario` `u` join `a6794339_health`.`cita` `c`) join `a6794339_health`.`hospital` `h`) join `a6794339_health`.`sala` `s`) join `a6794339_health`.`doctor` `d`) where ((`c`.`doctor_id` = `d`.`doctor_id`) and (`c`.`sala_id` = `s`.`sala_id`) and (`u`.`usuario_id` = `c`.`usuario_id`) and (`d`.`hospital_id` = `h`.`hospital_id`));

--
-- Dumping data for table `citainformacion`
--

INSERT INTO `citainformacion` VALUES(1001, 2, '2015-06-24 17:00:00', 'Hospital Tunal', 'Carrera 20 # 47B - 35 SUR', '3383838', 110, 'Leonardo', 'Rodriguez');
INSERT INTO `citainformacion` VALUES(1002, 3, '2015-08-30 11:30:00', 'Clinica Marly', 'Carrera 15 # 50 - 31 ', '4332212', 310, 'Lina', 'Alvarez');
INSERT INTO `citainformacion` VALUES(1001, 4, '2015-08-30 12:30:00', 'Hospital Kennedy', 'Carrera 64 # 1 - 35 SUR', '7113345', 510, 'Pedro', 'Rios');
INSERT INTO `citainformacion` VALUES(1001, 5, '2015-09-30 11:30:00', 'Hospital Tunal', 'Carrera 20 # 47B - 35 SUR', '3383838', 212, 'Leonardo', 'Rodriguez');
INSERT INTO `citainformacion` VALUES(1001, 6, '2015-09-10 09:00:00', 'Clinica Marly', 'Carrera 15 # 50 - 31 ', '4332212', 310, 'Lina', 'Alvarez');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL,
  `doctor_nombre` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `doctor_apellido` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `doctor_especialidad` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `doctor_fechanac` varchar(11) COLLATE latin1_general_ci NOT NULL,
  `hospital_id` int(11) NOT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `id_hospital` (`hospital_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` VALUES(2001, 'Leonardo', 'Rodriguez', 'Geriatria', '1989-09-12', 1);
INSERT INTO `doctor` VALUES(2002, 'Lina', 'Alvarez', 'Neurologia', '1985-02-02', 2);
INSERT INTO `doctor` VALUES(2003, 'Pedro', 'Rios', 'Oftalmologia', '1976-09-10', 3);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `hospital_id` int(11) NOT NULL,
  `hospital_nombre` text COLLATE latin1_general_ci NOT NULL,
  `hospital_direccion` text COLLATE latin1_general_ci NOT NULL,
  `hospital_telefono` text COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`hospital_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` VALUES(1, 'Hospital Tunal', 'Carrera 20 # 47B - 35 SUR', '3383838');
INSERT INTO `hospital` VALUES(2, 'Clinica Marly', 'Carrera 15 # 50 - 31 ', '4332212');
INSERT INTO `hospital` VALUES(3, 'Hospital Kennedy', 'Carrera 64 # 1 - 35 SUR', '7113345');

-- --------------------------------------------------------

--
-- Table structure for table `medicamento`
--

CREATE TABLE `medicamento` (
  `medicamento_codigo` int(11) NOT NULL,
  `medicamento_nombre` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `medicamento_fechaInicio` datetime NOT NULL,
  `medicamento_fechaFinal` datetime NOT NULL,
  `medicamento_cantidadxDia` int(11) NOT NULL,
  `medicamento_intervaloHoras` int(11) NOT NULL,
  PRIMARY KEY (`medicamento_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `medicamento`
--

INSERT INTO `medicamento` VALUES(100, 'Acetaminofen', '2015-08-27 08:00:00', '2015-09-15 16:00:00', 2, 8);
INSERT INTO `medicamento` VALUES(101, 'Piroxicam', '2015-08-15 07:00:00', '2015-09-15 17:00:00', 3, 5);
INSERT INTO `medicamento` VALUES(102, 'Nifedipina', '2015-08-15 15:00:00', '2015-09-30 15:00:00', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `medicamentoinformacion`
--

CREATE ALGORITHM=UNDEFINED DEFINER=`a6794339_yojhan`@`localhost` SQL SECURITY DEFINER VIEW `a6794339_health`.`medicamentoinformacion` AS select `u`.`usuario_id` AS `usuario_id`,`m`.`medicamento_codigo` AS `medicamento_codigo`,`m`.`medicamento_nombre` AS `medicamento_nombre`,`m`.`medicamento_fechaInicio` AS `medicamento_fechaInicio`,`m`.`medicamento_fechaFinal` AS `medicamento_fechaFinal`,`m`.`medicamento_cantidadxDia` AS `medicamento_cantidadxDia`,`m`.`medicamento_intervaloHoras` AS `medicamento_intervaloHoras`,(now() - interval 1 hour) AS `hora_actual` from ((`a6794339_health`.`medicamento` `m` join `a6794339_health`.`usuario` `u`) join `a6794339_health`.`usuario_medicamento` `um`) where ((`u`.`usuario_id` = `um`.`usuario_id`) and (`m`.`medicamento_codigo` = `um`.`medicamento_codigo`));

--
-- Dumping data for table `medicamentoinformacion`
--

INSERT INTO `medicamentoinformacion` VALUES(1001, 100, 'Acetaminofen', '2015-08-27 08:00:00', '2015-09-15 16:00:00', 2, 8, '2016-03-23 21:27:25');
INSERT INTO `medicamentoinformacion` VALUES(1001, 101, 'Piroxicam', '2015-08-15 07:00:00', '2015-09-15 17:00:00', 3, 5, '2016-03-23 21:27:25');
INSERT INTO `medicamentoinformacion` VALUES(1001, 102, 'Nifedipina', '2015-08-15 15:00:00', '2015-09-30 15:00:00', 1, 0, '2016-03-23 21:27:25');
INSERT INTO `medicamentoinformacion` VALUES(1002, 100, 'Acetaminofen', '2015-08-27 08:00:00', '2015-09-15 16:00:00', 2, 8, '2016-03-23 21:27:25');

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` VALUES('2014_10_12_000000_create_users_table', 1);
INSERT INTO `migrations` VALUES('2014_10_12_100000_create_password_resets_table', 1);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `password_resets_email_index` (`email`),
  KEY `password_resets_token_index` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `password_resets`
--


-- --------------------------------------------------------

--
-- Table structure for table `plan_dieta`
--

CREATE TABLE `plan_dieta` (
  `plan_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `lun` text COLLATE latin1_general_ci NOT NULL,
  `mar` text COLLATE latin1_general_ci NOT NULL,
  `mie` text COLLATE latin1_general_ci NOT NULL,
  `jue` text COLLATE latin1_general_ci NOT NULL,
  `vie` text COLLATE latin1_general_ci NOT NULL,
  `sab` text COLLATE latin1_general_ci NOT NULL,
  `dom` text COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `plan_dieta`
--

INSERT INTO `plan_dieta` VALUES(300, 1001, '- 3 tazas de leche o yogurt con poca grasa\r\n- 3 frutas o jugos de frutas naturales    ', '', '- 3 tazas de leche o yogurt con poca grasa\r\n- 3 frutas o jugos de frutas naturales    ', '', '- 3 tazas de leche o yogurt con poca grasa\r\n- 3 frutas o jugos de frutas naturales   ', '- 8 vasos de agua', '');
INSERT INTO `plan_dieta` VALUES(301, 1002, '', '- 2 platos de verduras, crudas o cocidas', '- 2 frutas o jugos de frutas naturales', '- 2 frutas o jugos de frutas naturales', '- 2 platos de verduras, crudas o cocidas', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE `sala` (
  `hospital_id` int(11) NOT NULL,
  `sala_id` int(11) NOT NULL,
  PRIMARY KEY (`hospital_id`,`sala_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `sala`
--

INSERT INTO `sala` VALUES(1, 110);
INSERT INTO `sala` VALUES(1, 212);
INSERT INTO `sala` VALUES(2, 310);
INSERT INTO `sala` VALUES(2, 412);
INSERT INTO `sala` VALUES(3, 510);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` VALUES(1, 'superuser', 'super@localhost.com', '$2y$10$pyVdSwO3FIkwH128BdsoAuHJWxSjNNPw5HvZQKUBDJrpJJQ0Jbzq.', '5roR0sphxQcXmSLpJ4lMAvo1b30quE6LCc14hNZOnbpItEvna7GceJbOWWLp', '2015-08-31 04:09:18', '2015-08-31 04:46:26');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `usuario_id` int(5) NOT NULL,
  `usuario_nombre` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `usuario_apellido` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `usurio_fechanac` varchar(11) COLLATE latin1_general_ci NOT NULL,
  `usuario_username` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `usuario_password` varchar(20) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `userna` (`usuario_username`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` VALUES(1001, 'Leonardo', 'Calle', '1934-08-12', 'leo', 'leo');
INSERT INTO `usuario` VALUES(1002, 'Maria Alejandra', 'Ortiz Gomez', '1942-01-31', 'usuario', 'prueba');

-- --------------------------------------------------------

--
-- Table structure for table `usuario_actividad`
--

CREATE TABLE `usuario_actividad` (
  `usuario_id` int(11) NOT NULL,
  `actividad_id` int(11) NOT NULL,
  `fecha_final` datetime NOT NULL,
  PRIMARY KEY (`usuario_id`,`actividad_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `usuario_actividad`
--

INSERT INTO `usuario_actividad` VALUES(1001, 200, '2015-09-30 09:00:00');
INSERT INTO `usuario_actividad` VALUES(1002, 202, '2015-09-30 11:00:00');
INSERT INTO `usuario_actividad` VALUES(1001, 201, '2015-09-25 10:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `usuario_medicamento`
--

CREATE TABLE `usuario_medicamento` (
  `usuario_id` int(11) NOT NULL,
  `medicamento_codigo` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`,`medicamento_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `usuario_medicamento`
--

INSERT INTO `usuario_medicamento` VALUES(1001, 100);
INSERT INTO `usuario_medicamento` VALUES(1001, 101);
INSERT INTO `usuario_medicamento` VALUES(1001, 102);
INSERT INTO `usuario_medicamento` VALUES(1002, 100);


CREATE VIEW citainformacion AS
SELECT u.usuario_id,c.cita_id,c.cita_fecha,h.hospital_nombre,h.hospital_direccion,h.hospital_telefono,s.sala_id
,d.doctor_nombre,d.doctor_apellido

FROM usuario u,cita c,hospital h, sala s, doctor d

WHERE c.doctor_id = d.doctor_id AND
c.sala_id= s.sala_id AND
u.usuario_id = c.usuario_id AND
d.hospital_id = h.hospital_id;


CREATE VIEW medicamentoinformacion AS

SELECT u.usuario_id,m.medicamento_codigo,m.medicamento_nombre,m.medicamento_fechaInicio,m.medicamento_fechaFinal,m.medicamento_cantidadxDia,m.medicamento_intervaloHoras, 	
DATE_SUB(now(),INTERVAL 1 HOUR) as hora_actual

FROM medicamento m,usuario u,usuario_medicamento um

WHERE u.usuario_id = um.usuario_id
AND   m.medicamento_codigo = um.medicamento_codigo;
