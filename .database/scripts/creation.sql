CREATE TABLE IF NOT EXISTS `consol`.`familia` (
  `id_familia` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  `numero_casa` INT NOT NULL,
  `renda` DOUBLE(8,2) NOT NULL,
  `flag_retirada` TINYINT NOT NULL,
  `data_cadastro` DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS `consol`.`titular` (
  `id_titular` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `rg` VARCHAR(9) NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `telefone1` VARCHAR(11) NOT NULL,
  `telefone2` VARCHAR(11) NULL,
  `estado_civil` VARCHAR(15) NOT NULL,
  `escolaridade` VARCHAR(30) NOT NULL,
  `trabalhando` TINYINT NOT NULL,
  `ocupacao` VARCHAR(45) NOT NULL,
  `fk_familia` INT NOT NULL,
  `referencia_s3` TEXT NULL,
  FOREIGN KEY (`fk_familia`)
  REFERENCES `familia` (`id_familia`)
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`despesa` (
  `id_despesa` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `gasto` DOUBLE(7,2) NOT NULL,
  `fk_familia` INT NOT NULL,
    FOREIGN KEY (`fk_familia`) REFERENCES `consol`.`familia` (`id_familia`)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`beneficio` (
  `id_beneficio` INT PRIMARY KEY auto_increment,
  `nome` VARCHAR(45) NOT NULL,
  `valor` DOUBLE(7,2) NOT NULL,
  `fk_titular` INT NOT NULL,
    FOREIGN KEY (`fk_titular`) REFERENCES `consol`.`titular` (`id_titular`)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`instituicao` (
  `id_instituicao` INT PRIMARY KEY NOT NULL auto_increment,
  `nome_instituicao` VARCHAR(70) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  `numero_imovel` VARCHAR(10) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `consol`.`doacao` (
  `id_doacao` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NOT NULL,
  `data_doacao` DATETIME NOT NULL,
  `flag_doacao_entregue` TINYINT NOT NULL,
  `fk_instituicao` INT NOT NULL,
  `fk_titular` INT NOT NULL,
    FOREIGN KEY (`fk_instituicao`) REFERENCES `consol`.`instituicao` (`id_instituicao`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_titular`) REFERENCES `consol`.`titular` (`id_titular` ) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`usuario` (
  `id_usuario` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `coordenador` TINYINT NOT NULL,
  `nome_usuario` VARCHAR(60) NOT NULL,
  `email` VARCHAR(70) NOT NULL,
  `senha` TEXT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `flag_aprovado` TINYINT NOT NULL,
  `fk_instituicao` INT NOT NULL,
    FOREIGN KEY (`fk_instituicao`) REFERENCES `consol`.`instituicao` (`id_instituicao`)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `consol`.`instituicao_familia` (
  `id_familia_instituicao` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `fk_instituicao` INT NOT NULL,
  `fk_familia` INT NOT NULL,
    FOREIGN KEY (`fk_instituicao`) REFERENCES `consol`.`instituicao` (`id_instituicao`) ON DELETE CASCADE,
    FOREIGN KEY (`fk_familia`) REFERENCES `consol`.`familia` (`id_familia`)
    ON DELETE CASCADE
);

INSERT INTO instituicao (nome_instituicao, cep, numero_imovel, descricao)
VALUES (
    'nomeInstituicao_8445bcfcfc75',
    '01234567',
    '2524725',
    'descricao_f578e2d88fea'
);
