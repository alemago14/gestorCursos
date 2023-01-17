-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-01-2023 a las 21:15:23
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `quinto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `id` varchar(255) NOT NULL,
  `alta` bit(1) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `historia` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`id`, `alta`, `clave`, `edad`, `fecha_nacimiento`, `historia`, `mail`, `nombre`) VALUES
('3c5a8506-0bda-4bad-a84a-4d8e8595f9bd', b'1', '', 15, '2015-02-15', 'despues', NULL, 'Quico Chavez'),
('846741d7-a0bd-4174-aaa8-c989b15d059e', b'1', '987645', 28, '1994-07-14', 'despues', NULL, 'Alejandro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `id` varchar(255) NOT NULL,
  `alta` bit(1) NOT NULL,
  `horario_fin` time DEFAULT NULL,
  `horario_inicio` time DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `turno` smallint(6) DEFAULT NULL,
  `profesor_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`id`, `alta`, `horario_fin`, `horario_inicio`, `nombre`, `turno`, `profesor_id`) VALUES
('1fabec64-eee6-425d-9c99-6d862ae56231', b'0', '09:30:00', '08:00:00', 'Por definir 3', 0, NULL),
('ad3deeea-bc6b-48e8-a21f-a6100e0313c5', b'0', '17:00:00', '15:30:00', 'Por definir 2', 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_alumno`
--

CREATE TABLE `curso_alumno` (
  `curso_id` varchar(255) NOT NULL,
  `alumno_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `id` varchar(255) NOT NULL,
  `alta` bit(1) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`id`, `alta`, `apellido`, `mail`, `nombre`) VALUES
('38fd3263-44fb-445b-8680-9ef575a997f3', b'1', 'Maugouber', 'a@a.com', 'Alejandro');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `profesor_id` (`profesor_id`);

--
-- Indices de la tabla `curso_alumno`
--
ALTER TABLE `curso_alumno`
  ADD KEY `FKkfvl4s9krhlfujxcynhd1qegn` (`alumno_id`),
  ADD KEY `FK5vv7tujgwxjxwjlmr7m59lny6` (`curso_id`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `profesor_id` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`);

--
-- Filtros para la tabla `curso_alumno`
--
ALTER TABLE `curso_alumno`
  ADD CONSTRAINT `FK5vv7tujgwxjxwjlmr7m59lny6` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  ADD CONSTRAINT `FKkfvl4s9krhlfujxcynhd1qegn` FOREIGN KEY (`alumno_id`) REFERENCES `alumno` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
