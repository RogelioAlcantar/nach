create schema estacionamiento;
use estacionamiento;

CREATE TABLE `automoviles` (
  `id_automovil` bigint NOT NULL AUTO_INCREMENT,
  `anio` bigint DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `placa` varchar(30) NOT NULL UNIQUE,
  `tipo_auto` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id_automovil`)
);

CREATE TABLE `entradasalida` (
  `id_entradasalida` bigint NOT NULL AUTO_INCREMENT,
  `llegada` datetime(6) DEFAULT NULL,
  `placa` varchar(30) DEFAULT NULL,
  `salida` datetime(6) DEFAULT NULL,
  `tarifa` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `automovil_id_automovil` bigint DEFAULT NULL,
  PRIMARY KEY (`id_entradasalida`),
  KEY `automoviles` (`automovil_id_automovil`),
  CONSTRAINT `automoviles` FOREIGN KEY (`automovil_id_automovil`) REFERENCES `automoviles` (`id_automovil`)
);
select * from automoviles;
select * from entradasalida;

INSERT INTO automoviles (PLACA, MODELO, ANIO, MARCA, TIPO_AUTO, COLOR) VALUES('PAP-12-12', 'RIO', '2021', 'KIA', 'E', 'AZUL');

INSERT INTO automoviles (PLACA, MODELO, ANIO, MARCA, TIPO_AUTO, COLOR) VALUES('MAY-12-32', 'JETTA', '2011', 'VW', 'R', 'NEGRO');

INSERT INTO automoviles (PLACA, MODELO, ANIO, MARCA, TIPO_AUTO, COLOR) VALUES('ROY-13-23', 'C30', '2021', 'VOLVO', 'R', 'CAFE');

INSERT INTO automoviles (PLACA, MODELO, ANIO, MARCA, TIPO_AUTO, COLOR) VALUES('PAU-23-213', 'FOCUS', '2021', 'FORD', 'E', 'VERDE');

INSERT INTO entradasalida (PLACA, LLEGADA, SALIDA, TARIFA, TOTAL, AUTOMOVIL_ID_AUTOMOVIL) VALUES ('PAP-12-12', NOW(), NULL, NULL, NULL , 1);

INSERT INTO entradasalida (PLACA, LLEGADA, SALIDA, TARIFA, TOTAL, AUTOMOVIL_ID_AUTOMOVIL) VALUES ('MAY-12-32', NOW(), NULL, NULL, NULL , 2);

INSERT INTO entradasalida (PLACA, LLEGADA, SALIDA, TARIFA, TOTAL, AUTOMOVIL_ID_AUTOMOVIL) VALUES ('ROY-13-23', NOW(), NULL, NULL, NULL , 3);