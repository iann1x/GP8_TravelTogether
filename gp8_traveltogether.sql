-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2024 a las 17:06:29
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gp8_traveltogether`
--
CREATE DATABASE IF NOT EXISTS `gp8_traveltogether` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gp8_traveltogether`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento`
--

CREATE TABLE `alojamiento` (
  `codAlojam` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `codCiudad` int(11) NOT NULL,
  `tipoAlojam` varchar(20) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `precioNoche` double NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alojamiento`
--

INSERT INTO `alojamiento` (`codAlojam`, `nombre`, `codCiudad`, `tipoAlojam`, `capacidad`, `precioNoche`, `estado`) VALUES
(1, 'El Regidor', 1, 'Hotel', 25, 25000, 1),
(2, 'Las maravillas', 4, 'Hostel', 20, 15000, 1),
(3, 'Sol y Luna', 1, 'Departamento', 5, 20000, 1),
(4, 'El Mirador', 2, 'Hotel', 100, 25000, 1),
(5, 'Las Flores', 2, 'Departamento', 5, 30000, 1),
(6, 'La Finca', 2, 'Hotel', 35, 50000, 1),
(7, 'Dina Huapi Hostel', 2, 'Hostel', 40, 27000, 1),
(8, 'El Mapache', 7, 'Departamento', 4, 25000, 1),
(9, 'El Solar', 2, 'Hostel', 25, 25000, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `codCiudad` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`codCiudad`, `nombre`, `estado`) VALUES
(1, 'Mendoza', 1),
(2, 'Bariloche', 1),
(4, 'Puerto Madryn', 1),
(5, 'Puerto Iguazú', 1),
(6, 'San Luis', 1),
(7, 'Buenos Aires', 1),
(8, 'Salta', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquete`
--

CREATE TABLE `paquete` (
  `codigoPaquete` int(11) NOT NULL,
  `origen` int(11) NOT NULL,
  `destino` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `temporada` varchar(20) NOT NULL,
  `codAlojam` int(11) NOT NULL,
  `codPasaje` int(11) NOT NULL,
  `codAdicional` int(11) NOT NULL,
  `traslado` tinyint(1) NOT NULL,
  `montoFinal` double DEFAULT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paquete`
--

INSERT INTO `paquete` (`codigoPaquete`, `origen`, `destino`, `fechaInicio`, `fechaFin`, `temporada`, `codAlojam`, `codPasaje`, `codAdicional`, `traslado`, `montoFinal`, `estado`) VALUES
(12, 1, 2, '2024-11-17', '2024-11-25', 'media', 7, 3, 4, 1, 696908.74, 1),
(13, 1, 2, '2024-11-29', '2024-11-30', 'media', 4, 3, 8, 1, 70211.81, 0),
(14, 1, 2, '2024-11-23', '2024-11-27', 'media', 4, 3, 4, 1, 394569.32, 1),
(15, 1, 7, '2024-11-24', '2024-11-29', 'media', 8, 8, 4, 1, 563565.42, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasaje`
--

CREATE TABLE `pasaje` (
  `codPasaje` int(11) NOT NULL,
  `origen` int(11) NOT NULL,
  `destino` int(11) DEFAULT NULL,
  `precioPasaje` double NOT NULL,
  `tipoViaje` varchar(20) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pasaje`
--

INSERT INTO `pasaje` (`codPasaje`, `origen`, `destino`, `precioPasaje`, `tipoViaje`, `estado`) VALUES
(1, 1, 4, 60000, 'Colectivo', 1),
(2, 1, 5, 27000, 'Colectivo', 1),
(3, 1, 2, 70000, 'Avion', 1),
(4, 1, 5, 100000, 'Avion', 1),
(5, 1, 6, 15000, 'Colectivo', 1),
(6, 1, 2, 230000, 'Avion', 1),
(7, 1, 2, 80000, 'Colectivo', 1),
(8, 1, 7, 30000, 'Colectivo', 1),
(9, 1, 7, 57000, 'Avion', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pension`
--

CREATE TABLE `pension` (
  `codAdicional` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `porcentaje` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pension`
--

INSERT INTO `pension` (`codAdicional`, `nombre`, `porcentaje`) VALUES
(1, 'Sin pension', 0),
(4, 'Desayuno', 3),
(7, 'Media pension', 5),
(8, 'Completa', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turista`
--

CREATE TABLE `turista` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `edad` int(11) NOT NULL,
  `codigoPaquete` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `turista`
--

INSERT INTO `turista` (`dni`, `nombre`, `edad`, `codigoPaquete`, `estado`) VALUES
(123468, 'Ian', 7, 15, 1),
(1984110, 'Angel Cuello', 56, 14, 1),
(11789456, 'Ximena', 7, 15, 1),
(12148759, 'Milton Hernan', 35, 15, 1),
(33451236, 'Mimi', 8, 13, 1),
(33757395, 'Ximena', 5, 12, 1),
(35474790, 'german', 35, 12, 1),
(92759256, 'Rosa', 9, 14, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD PRIMARY KEY (`codAlojam`),
  ADD KEY `codCiudad` (`codCiudad`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`codCiudad`);

--
-- Indices de la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`codigoPaquete`),
  ADD KEY `codPasaje` (`codPasaje`,`codAlojam`),
  ADD KEY `codAdicional` (`codAdicional`),
  ADD KEY `codAlojam` (`codAlojam`),
  ADD KEY `origen` (`origen`,`destino`);

--
-- Indices de la tabla `pasaje`
--
ALTER TABLE `pasaje`
  ADD PRIMARY KEY (`codPasaje`),
  ADD KEY `origen` (`origen`,`destino`),
  ADD KEY `destino` (`destino`);

--
-- Indices de la tabla `pension`
--
ALTER TABLE `pension`
  ADD PRIMARY KEY (`codAdicional`);

--
-- Indices de la tabla `turista`
--
ALTER TABLE `turista`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `codigoPaquete` (`codigoPaquete`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  MODIFY `codAlojam` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `codCiudad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `paquete`
--
ALTER TABLE `paquete`
  MODIFY `codigoPaquete` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `pasaje`
--
ALTER TABLE `pasaje`
  MODIFY `codPasaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `pension`
--
ALTER TABLE `pension`
  MODIFY `codAdicional` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD CONSTRAINT `alojamiento_ibfk_1` FOREIGN KEY (`codCiudad`) REFERENCES `ciudad` (`codCiudad`);

--
-- Filtros para la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD CONSTRAINT `paquete_ibfk_1` FOREIGN KEY (`codPasaje`) REFERENCES `pasaje` (`codPasaje`),
  ADD CONSTRAINT `paquete_ibfk_2` FOREIGN KEY (`codAdicional`) REFERENCES `pension` (`codAdicional`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `paquete_ibfk_3` FOREIGN KEY (`codAlojam`) REFERENCES `alojamiento` (`codAlojam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pasaje`
--
ALTER TABLE `pasaje`
  ADD CONSTRAINT `pasaje_ibfk_1` FOREIGN KEY (`origen`) REFERENCES `ciudad` (`codCiudad`),
  ADD CONSTRAINT `pasaje_ibfk_2` FOREIGN KEY (`destino`) REFERENCES `ciudad` (`codCiudad`);

--
-- Filtros para la tabla `turista`
--
ALTER TABLE `turista`
  ADD CONSTRAINT `turista_ibfk_1` FOREIGN KEY (`codigoPaquete`) REFERENCES `paquete` (`codigoPaquete`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
