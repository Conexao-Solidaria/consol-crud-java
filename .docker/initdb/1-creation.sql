DROP DATABASE IF EXISTS `consol` ;

CREATE DATABASE IF NOT EXISTS `consol` DEFAULT CHARACTER SET utf8 ;
USE `consol` ;


CREATE TABLE IF NOT EXISTS `consol`.`familia` (
  `id_familia` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NULL,
  `cep` VARCHAR(8) NULL,
  `numero_casa` INT NULL,
  `renda` DOUBLE(8,2) NULL,
  `flag_retirada` TINYINT NULL
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `consol`.`titular` (
  `id_titular` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `data_cadastro` DATE NULL,
  `nome` VARCHAR(60) NULL,
  `rg` VARCHAR(9) NOT NULL,
  `cpf` VARCHAR(11) NULL,
  `data_nascimento` DATE NULL,
  `telefone1` VARCHAR(11) NULL,
  `telefone2` VARCHAR(11) NULL,
  `estado_civil` VARCHAR(15) NULL,
  `escolaridade` VARCHAR(30) NULL,
  `trabalhando` TINYINT NULL,
  `ocupacao` VARCHAR(45) NULL,
  `fk_familia` INT NOT NULL,
  FOREIGN KEY (`fk_familia`)
  REFERENCES `familia` (`id_familia`)
  ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS `consol`.`registroVisita` (
  `id_registro_visita` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `data_visita` DATE NULL,
  `descricao` TEXT NULL,
  `fk_titular` INT NOT NULL,
    FOREIGN KEY (`fk_titular`) REFERENCES `consol`.`titular` (`id_titular`)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`despesa` (
  `id_despesa` INT PRIMARY KEY auto_increment,
  `tipo` VARCHAR(45) NULL,
  `gasto` DOUBLE(7,2) NULL,
  `fk_familia` INT NOT NULL,
    FOREIGN KEY (`fk_familia`) REFERENCES `consol`.`familia` (`id_familia`)
    ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS `consol`.`beneficio` (
  `id_beneficio` INT PRIMARY KEY auto_increment,
  `nome` VARCHAR(45) NULL,
  `valor` DOUBLE(7,2) NULL,
  `fk_titular` INT NOT NULL,
    FOREIGN KEY (`fk_titular`) REFERENCES `consol`.`titular` (`id_titular`)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`instituicao` (
  `id_instituicao` INT PRIMARY KEY NOT NULL auto_increment,
  `nome_instituicao` VARCHAR(70) NULL,
  `cep` CHAR(8) NULL,
  `numero_imovel` VARCHAR(10) NULL,
  `descricao` VARCHAR(255) NULL,
  `foto_perfil` TEXT NULL
);

CREATE TABLE IF NOT EXISTS `consol`.`doacao` (
  `id_doacao` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NULL,
  `status_doacao` tinyint NULL,
  `data_doacao` DATETIME NULL,
  `flag_doacao_entregue` TINYINT NULL,
  `fk_instituicao` INT NOT NULL,
  `fk_titular` INT NOT NULL,
    FOREIGN KEY (`fk_instituicao`) REFERENCES `consol`.`instituicao` (`id_instituicao`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_titular`) REFERENCES `consol`.`titular` (`id_titular` ) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`usuario` (
  `id_usuario` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `coordernador` TINYINT NOT NULL,
  `nome_usuario` VARCHAR(60) NOT NULL,
  `email` VARCHAR(70) NOT NULL,
  `senha` TEXT NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `flag_aprovado` TINYINT NOT NULL,
  `fk_instituicao` INT NOT NULL,
    FOREIGN KEY (`fk_instituicao`) REFERENCES `consol`.`instituicao` (`id_instituicao`)
    ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS `consol`.`instituicao_familia` (
  `id_familia_instituicao` INT PRIMARY KEY auto_increment,
  `fk_instituicao` INT NOT NULL,
  `fk_familia` INT NOT NULL,
    FOREIGN KEY (`fk_instituicao`) REFERENCES `consol`.`instituicao` (`id_instituicao`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_familia`) REFERENCES `consol`.`familia` (`id_familia`)
    ON DELETE CASCADE
);

INSERT INTO instituicao (nome_instituicao, cep, numero_imovel, descricao, foto_perfil)
VALUES (
    'nomeInstituicao_8445bcfcfc75',
    '01234567',
    '2524725',
    'descricao_f578e2d88fea',
    'kansdjnasojncoinascoi'
);

select * from instituicao;
