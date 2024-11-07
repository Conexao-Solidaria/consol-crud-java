-- Inserindo dados na tabela `familia`
INSERT INTO `consol`.`familia` (`nome`, `cep`, `numero_casa`, `renda`, `flag_retirada`, `data_cadastro`) VALUES 
('Família Souza', '12345678', 100, 1500.00, 1, '2023-05-01'),
('Família Oliveira', '87654321', 200, 2500.00, 0, '2023-06-15'),
('Família Lima', '13579111', 300, 1200.00, 1, '2023-07-20');

-- Inserindo dados na tabela `titular` (incluindo um menor de 12 anos)
INSERT INTO `consol`.`titular` (`nome`, `rg`, `cpf`, `data_nascimento`, `telefone1`, `telefone2`, `estado_civil`, `escolaridade`, `trabalhando`, `ocupacao`, `fk_familia`, `referencia_s3`) VALUES 
('Carlos Souza', '123456789', '11122233344', '1985-02-20', '11987654321', '11987654322', 'Casado', 'Ensino Médio Completo', 1, 'Empregado', 1, NULL),
('Beatriz Oliveira', '987654321', '55566677788', '2010-09-20', '11912345678', NULL, 'Solteiro', 'Fundamental Completo', 0, 'Estudante', 2, NULL),
('Ana Lima', '876543210', '12312312312', '1975-04-05', '11955544433', '11933322211', 'Divorciado', 'Ensino Superior Completo', 1, 'Autônoma', 3, NULL);

-- Inserindo dados na tabela `despesa`
INSERT INTO `consol`.`despesa` (`tipo`, `gasto`, `fk_familia`) VALUES 
('Alimentação', 500.00, 1),
('Transporte', 150.00, 2),
('Saúde', 300.00, 3);

-- Inserindo dados na tabela `beneficio`
INSERT INTO `consol`.`beneficio` (`nome`, `valor`, `fk_titular`) VALUES 
('Bolsa Família', 200.00, 1),
('Auxílio Emergencial', 300.00, 2),
('Vale Gás', 100.00, 3);

-- Inserindo dados na tabela `instituicao`
INSERT INTO `consol`.`instituicao` (`nome_instituicao`, `cep`, `numero_imovel`, `descricao`) VALUES 
('Instituto Esperança', '12345678', '100', 'Instituição de apoio à comunidade carente'),
('ONG Solidariedade', '87654321', '200', 'Organização para assistência social');

-- Inserindo dados na tabela `doacao`
INSERT INTO `consol`.`doacao` (`descricao`, `data_doacao`, `flag_doacao_entregue`, `fk_instituicao`, `fk_titular`) VALUES 
('Cesta Básica', '2023-09-10 10:30:00', 1, 1, 1),
('Kit Escolar', '2023-10-05 15:00:00', 0, 2, 2);

-- Inserindo dados na tabela `usuario`
INSERT INTO `consol`.`usuario` (`coordenador`, `nome_usuario`, `email`, `senha`, `cpf`, `flag_aprovado`, `fk_instituicao`) VALUES 
(1, 'Maria da Silva', 'maria@example.com', 'senha123', '12345678901', 1, 1),
(0, 'João Oliveira', 'joao@example.com', 'senha456', '10987654321', 1, 2);

-- Inserindo dados na tabela `instituicao_familia`
INSERT INTO `consol`.`instituicao_familia` (`fk_instituicao`, `fk_familia`) VALUES 
(1, 1),
(2, 2),
(1, 3);
