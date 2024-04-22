/**
 * Author: rubensanrodrigues
 * Created: 3 de abr. de 2024
 */

CREATE SCHEMA `agenda_da_beleza` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `agenda_da_beleza`;

CREATE USER agenda_user IDENTIFIED BY 'auser1234';

GRANT USAGE ON *.* TO agenda_user@localhost IDENTIFIED BY 'auser1234';
GRANT ALL PRIVILEGES ON agenda_da_beleza.* TO agenda_user@localhost;

CREATE TABLE `agendamento` (
  `contato` varchar(50) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `observacao` varchar(200) DEFAULT NULL,
  `horario` bigint(20) NOT NULL,
  `chaveacesso` bigint(20) NOT NULL,
  PRIMARY KEY (`chaveacesso`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
