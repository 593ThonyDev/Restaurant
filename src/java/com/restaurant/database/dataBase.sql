-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-08-2023 a las 00:10:10
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `papeleria`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllClientes` ()   BEGIN
SELECT * from cliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllMenus` ()   BEGIN
SELECT * from menu;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllUsuarios` ()   BEGIN
SELECT * from usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAttempts` (IN `idUsuarioV` INT)   BEGIN 
	SELECT usuIntento FROM usuario WHERE idUsuario = idUsuarioV;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdUsuario` (IN `usuUsuarioV` VARCHAR(15))   BEGIN 
	SELECT idUsuario FROM usuario WHERE usuUsuario = usuUsuarioV;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getPassword` (IN `idUsuarioV` VARCHAR(255))   BEGIN
SELECT usuClave 
FROM usuario 
WHERE idUsuario = idUsuarioV;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsuRole` (IN `idUsuarioV` INT)   BEGIN 
	SELECT usuCargo FROM usuario WHERE idUsuario = idUsuarioV;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerUltimoID` ()   BEGIN
    SELECT MAX(idVenta) FROM venta;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `saveSale` (IN `fkCliente_v` INT, IN `venCodigo_v` VARCHAR(45), IN `venFecha_v` DATE, IN `venTotal_v` DECIMAL(10,2), IN `fkVenta_v` INT, IN `fkMenu_v` INT, IN `detCantidad_v` INT, IN `detTotal_v` DECIMAL(10,2))   BEGIN
    INSERT INTO VENTA 
    (fkCliente, venCodigo, venFecha, venTotal) 
    VALUES
    (fkCliente_v, venCodigo_v, venFecha_v, venTotal_v);

    INSERT INTO detalle(fkVenta, fkMenu, detCantidad, detTotal)
    VALUES(fkVenta_v, fkMenu_v, detCantidad_v, detTotal_v);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarCliente` (IN `p_idCliente` INT, IN `p_cliNombre` VARCHAR(255), IN `p_cliApellido` VARCHAR(255), IN `p_cliCedula` VARCHAR(15), IN `p_cliEmail` VARCHAR(255), IN `p_cliTelefono` VARCHAR(25))   BEGIN
    UPDATE cliente
    SET cliNombre = p_cliNombre,
        cliApellido = p_cliApellido,
        cliCedula = p_cliCedula,
        cliEmail = p_cliEmail,
        cliTelefono = p_cliTelefono
    WHERE idCliente = p_idCliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarDetalle` (IN `p_idDetalle` INT, IN `p_fkVenta` INT, IN `p_fkMenu` INT, IN `p_detCantidad` INT, IN `p_detTotal` DOUBLE(10,2))   BEGIN
    UPDATE detalle
    SET fkVenta = p_fkVenta,
        fkMenu = p_fkMenu,
        detCantidad = p_detCantidad,
        detTotal = p_detTotal
    WHERE idDetalle = p_idDetalle;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarMenu` (IN `p_idMenu` INT, IN `menNombre` VARCHAR(255), IN `p_menCodigo` VARCHAR(25), IN `p_menStock` INT, IN `p_menPrecio` DOUBLE(10,2))   BEGIN
    UPDATE menu
    SET menNombre = menNombre,
        menCodigo = p_menCodigo,
        menStock = p_menStock,
        menPrecio = p_menPrecio
    WHERE idMenu = p_idMenu;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarUsuario` (IN `p_idUsuario` INT, IN `p_usuUsuario` VARCHAR(50), IN `p_usuClave` VARCHAR(255), IN `p_usuIntento` INT)   BEGIN
    UPDATE usuario
    SET usuUsuario = p_usuUsuario,
        usuClave = p_usuClave,
        usuIntento = p_usuIntento
    WHERE idUsuario = p_idUsuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spCrearCliente` (IN `p_cliNombre` VARCHAR(255), IN `p_cliApellido` VARCHAR(255), IN `p_cliCedula` VARCHAR(15), IN `p_cliEmail` VARCHAR(255), IN `p_cliTelefono` VARCHAR(25))   BEGIN
    INSERT INTO cliente (cliNombre, cliApellido, cliCedula, cliEmail, cliTelefono)
    VALUES (p_cliNombre, p_cliApellido, p_cliCedula, p_cliEmail, p_cliTelefono);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spCrearDetalle` (IN `p_fkVenta` INT, IN `p_fkMenu` INT, IN `p_detCantidad` INT, IN `p_detTotal` DOUBLE(10,2))   BEGIN
    INSERT INTO detalle (fkVenta, fkMenu, detCantidad, detTotal)
    VALUES (p_fkVenta, p_fkMenu, p_detCantidad, p_detTotal);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spCrearMenu` (IN `menNombre` VARCHAR(255), IN `p_menCodigo` VARCHAR(25), IN `p_menStock` INT, IN `p_menPrecio` DOUBLE(10,2))   BEGIN
    INSERT INTO menu (menNombre, menCodigo, menStock, menPrecio)
    VALUES (menNombre, p_menCodigo, p_menStock, p_menPrecio);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spCrearUsuario` (IN `p_usuUsuario` VARCHAR(50), IN `p_usuClave` VARCHAR(255), IN `p_usuIntento` INT)   BEGIN
    INSERT INTO usuario (usuUsuario, usuClave, usuIntento)
    VALUES (p_usuUsuario, p_usuClave, p_usuIntento);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spEliminarClientePorID` (IN `p_idCliente` INT)   BEGIN
    DELETE FROM cliente WHERE idCliente = p_idCliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spEliminarDetallePorID` (IN `p_idDetalle` INT)   BEGIN
    DELETE FROM detalle WHERE idDetalle = p_idDetalle;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spEliminarMenuPorID` (IN `p_idMenu` INT)   BEGIN
    DELETE FROM menu WHERE idMenu = p_idMenu;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spEliminarUsuarioPorID` (IN `p_idUsuario` INT)   BEGIN
    DELETE FROM usuario WHERE idUsuario = p_idUsuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spLeerClientes` ()   BEGIN
    SELECT * FROM cliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spLeerMenus` ()   BEGIN
    SELECT * FROM menu;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spLeerUsuarios` ()   BEGIN
    SELECT * FROM usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spObtenerClientePorID` (IN `p_idCliente` INT)   BEGIN
    SELECT * FROM cliente WHERE idCliente = p_idCliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spObtenerDetallePorID` (IN `p_idDetalle` INT)   BEGIN
    SELECT * FROM detalle WHERE idDetalle = p_idDetalle;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spObtenerMenuPorID` (IN `p_idMenu` INT)   BEGIN
    SELECT * FROM menu WHERE idMenu = p_idMenu;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spObtenerUsuarioPorID` (IN `p_idUsuario` INT)   BEGIN
    SELECT * FROM usuario WHERE idUsuario = p_idUsuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUsuario` (IN `idUsuarioV` INT, IN `usuUsuarioV` VARCHAR(255), IN `usuClaveV` VARCHAR(255), IN `usuIntentoV` INT)   BEGIN
	UPDATE usuario SET 
    usuario.usuUsuario = usuUsuarioV,
    usuario.usuClave = usuClaveV,
    usuario.usuIntento = usuIntentoV
    WHERE usuario.idUsuario = idUsuarioV;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL,
  `cliNombre` varchar(255) DEFAULT NULL,
  `cliApellido` varchar(255) DEFAULT NULL,
  `cliCedula` varchar(15) DEFAULT NULL,
  `cliEmail` varchar(255) DEFAULT NULL,
  `cliTelefono` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idcliente`, `cliNombre`, `cliApellido`, `cliCedula`, `cliEmail`, `cliTelefono`) VALUES
(1, 'Pedros', 'PÃ©rez', '1723098764', 'pedro@gmail.com', '0987654321');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `iddetalle` int(11) NOT NULL,
  `fkVenta` int(11) NOT NULL,
  `fkMenu` int(11) NOT NULL,
  `detCantidad` int(11) DEFAULT NULL,
  `detTotal` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu`
--

CREATE TABLE `menu` (
  `idmenu` int(11) NOT NULL,
  `menNombre` varchar(255) DEFAULT NULL,
  `menCodigo` varchar(25) DEFAULT NULL,
  `menStock` int(11) DEFAULT NULL,
  `menPrecio` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `menu`
--

INSERT INTO `menu` (`idmenu`, `menNombre`, `menCodigo`, `menStock`, `menPrecio`) VALUES
(1, 'Menu nuevo', '08383 sdf', 100, 1.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `usuUsuario` varchar(50) DEFAULT NULL,
  `usuClave` varchar(255) DEFAULT NULL,
  `usuIntento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `usuUsuario`, `usuClave`, `usuIntento`) VALUES
(1, 'admin', 'LFPfLv8ji08=', 0),
(2, 'admin001', 'LFPfLv8ji08=', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `idventa` int(11) NOT NULL,
  `fkCliente` int(11) NOT NULL,
  `venCodigo` varchar(25) DEFAULT NULL,
  `venFecha` date DEFAULT NULL,
  `venTotal` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`iddetalle`),
  ADD KEY `fk_detalle_venta1` (`fkVenta`),
  ADD KEY `fk_detalle_menu1` (`fkMenu`);

--
-- Indices de la tabla `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`idmenu`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`idventa`),
  ADD KEY `fk_venta_cliente` (`fkCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `menu`
--
ALTER TABLE `menu`
  MODIFY `idmenu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `idventa` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `fk_detalle_menu1` FOREIGN KEY (`fkMenu`) REFERENCES `menu` (`idmenu`),
  ADD CONSTRAINT `fk_detalle_venta1` FOREIGN KEY (`fkVenta`) REFERENCES `venta` (`idventa`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_venta_cliente` FOREIGN KEY (`fkCliente`) REFERENCES `cliente` (`idcliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
