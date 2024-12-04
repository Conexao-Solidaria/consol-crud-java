-- Inserindo dados na tabela `familia`
INSERT INTO `consol`.`familia` (`nome`, `cep`, `numero_casa`, `renda`, `flag_retirada`, `data_cadastro`) VALUES 
('Família Souza', '12345678', 100, 1500.00, 1, '2023-05-01'),
('Família Silva', '87654321', 200, 2500.00, 0, '2023-06-15'),
('Família Seba', '03693110', 50, 1600.00, 1, '2023-02-05'),
('Família Santos', '01445020', 230, 2100.00, 0, '2023-01-11'),
('Família Pereira', '05264000', 20, 1200.00, 0, '2022-08-25'),
('Família Araujo', '05182450', 310, 2500.00, 0, '2024-11-29'),
('Família Soares', '02755030', 24, 1700.00, 1, '2024-10-18'),
('Família Camargo', '03286110', 2029, 1900.00, 0, '2022-09-20'),
('Família Rocha' , '04890500', 289, 2900.00, 1, '2023-06-15'),
('Família Lima', '05273170', 301, 1200.00, 1, '2023-07-20');

-- Inserindo dados na tabela `titular` (incluindo um menor de 12 anos)
INSERT INTO `consol`.`titular` (`nome`, `rg`, `cpf`, `data_nascimento`, `telefone1`, `telefone2`, `estado_civil`, `escolaridade`, `trabalhando`, `ocupacao`, `fk_familia`, `referencia_s3`) VALUES
('Carlos Souza', '353056352', '51000443060', '1985-02-20', '11987654321', '11987654322', 'Casado', 'Ensino Médio Completo', 1, 'Empregado', 1, NULL),
('Beatriz Oliveira', '222382995', '44489380003', '2010-09-20', '11912345678', NULL, 'Solteiro', 'Fundamental Completo', 0, 'Estudante', 1, NULL),
('Ana Lima', '351805369', '84968050003', '1975-04-05', '11955544433', '11933322211', 'Divorciado', 'Ensino Superior Completo', 1, 'Autônoma', 1, NULL),
('João Silva', '160367542', '51649639066', '1990-12-10', '11988887777', NULL, 'Casado', 'Ensino Médio Completo', 1, 'Operador', 2, NULL),
('Maria Santos', '478903108', '13818805091', '1982-05-15', '11977778888', '11966665555', 'Viúvo', 'Ensino Fundamental Incompleto', 0, 'Desempregada', 2, NULL),
('Pedro Lima', '103381132', '10787547050', '2001-07-25', '11944443333', NULL, 'Solteiro', 'Ensino Médio Completo', 0, 'Estudante', 2, NULL),
('Fernanda Costa', '302299622', '84266144008', '1998-03-14', '11911112222', NULL, 'Casado', 'Ensino Superior Incompleto', 1, 'Recepcionista', 3, NULL),
('Luiz Andrade', '456198829', '73427454053', '1980-11-30', '11900001111', NULL, 'Divorciado', 'Ensino Superior Completo', 1, 'Engenheiro', 3, NULL),
('Camila Martins', '202416495', '91170209050', '1992-09-19', '11922221111', NULL, 'Solteiro', 'Ensino Médio Completo', 1, 'Técnica', 3, NULL),
('Paulo Rocha', '313726516', '35488945008', '1986-06-12', '11988880000', NULL, 'Casado', 'Ensino Fundamental Completo', 1, 'Motorista', 4, NULL),
('Gabriel Souza', '290674128', '48958597038', '1991-05-21', '11987654312', '11987654433', 'Solteiro', 'Ensino Médio Completo', 1, 'Técnico', 4, NULL),
('Aline Moraes', '502760448', '58946459050', '1995-02-13', '11956473829', NULL, 'Solteiro', 'Ensino Superior Completo', 0, 'Estudante', 4, NULL),
('Renato Vieira', '237760599', '30988068001', '1978-08-09', '11947382910', NULL, 'Casado', 'Ensino Médio Completo', 1, 'Operador', 5, NULL),
('Julia Gomes', '499654419', '71703787013', '2000-03-05', '11928374612', '11947283645', 'Solteiro', 'Ensino Médio Incompleto', 0, 'Estudante', 5, NULL),
('Roberto Almeida', '402945153', '29384703044', '1987-07-15', '11937482910', '11928374620', 'Casado', 'Ensino Superior Completo', 1, 'Gerente', 5, NULL),
('Carla Silva', '242520546', '09784014092', '1992-10-25', '11928374622', '11937284633', 'Solteiro', 'Ensino Médio Completo', 1, 'Recepcionista', 6, NULL),
('Eduardo Freitas', '491274944', '22383668060', '1983-12-20', '11948372613', NULL, 'Casado', 'Ensino Superior Incompleto', 1, 'Engenheiro', 6, NULL),
('Patricia Santos', '486196045', '17848827046', '1999-11-11', '11918273645', NULL, 'Casado', 'Ensino Médio Completo', 1, 'Operadora', 6, NULL),
('Tiago Nunes', '253479149', '70662126068', '1985-03-08', '11947283614', NULL, 'Divorciado', 'Ensino Médio Completo', 1, 'Motorista', 7, NULL),
('Luciana Ribeiro', '496524513', '06351725003', '1989-02-28', '11983726410', NULL, 'Solteiro', 'Ensino Superior Completo', 0, 'Estudante', 7, NULL),
('Rafael Carvalho', '393611280', '45185881003', '1996-04-11', '11928473645', '11938472618', 'Casado', 'Ensino Superior Completo', 1, 'Engenheiro', 7, NULL),
('Viviane Martins', '487251933', '98014735099', '1974-09-19', '11982736459', NULL, 'Viúvo', 'Ensino Fundamental Completo', 0, 'Desempregada', 8, NULL),
('Fernando Torres', '380685437', '96665893030', '1988-12-21', '11938472619', NULL, 'Casado', 'Ensino Superior Completo', 1, 'Engenheiro', 8, NULL),
('Paula Costa', '188937961', '57040851059', '1991-06-30', '11928374610', '11947382916', 'Solteiro', 'Ensino Médio Completo', 1, 'Técnica', 8, NULL),
('Adriana Rocha', '296469397', '73125178037', '1980-10-05', '11982736418', NULL, 'Casado', 'Ensino Fundamental Completo', 1, 'Motorista', 9, NULL),
('André Vieira', '362927686', '98452307098', '1993-01-18', '11927483615', NULL, 'Solteiro', 'Ensino Médio Completo', 0, 'Estudante', 9, NULL),
('Carolina Mendes', '320043940', '97980020081', '1984-08-24', '11983726418', NULL, 'Divorciado', 'Ensino Superior Completo', 1, 'Autônoma', 9, NULL),
('Fernado Queiroz', '321484186', '85707761012', '1984-08-24', '11983734418', NULL, 'Divorciado', 'Ensino Superior Completo', 1, 'Autônom', 10, NULL),
('Daniel Junior', '506945546', '03052499084', '1994-03-24', '11983426418', NULL, 'Divorciado', 'Ensino Superior Completo', 0, 'Autônom', 10, NULL),
('Leonardo Freitas', '326943808', '38046107069', '1987-03-16', '11948273610', NULL, 'Casado', 'Ensino Superior Completo', 1, 'Gerente', 10, NULL);

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
('Cesta Básica', '2023-01-15 08:30:00', 1, 1, 1),
('Material Escolar', '2023-03-10 10:30:00', 0, 1, 2),
('Roupas', '2023-05-20 14:00:00', 1, 1, 3),
('Brinquedos', '2023-06-25 16:00:00', 1, 1, 2),
('Medicamentos', '2023-08-15 09:00:00', 0, 1, 1),
('Alimentos Não Perecíveis', '2023-09-10 18:00:00', 1, 1, 3),
('Cesta Básica', '2023-01-20 08:30:00', 1, 1, 4),
('Kit de Higiene', '2023-03-15 11:00:00', 0, 1, 5),
('Roupas de Inverno', '2023-04-10 13:30:00', 1, 1, 6),
('Livros', '2023-07-05 10:30:00', 1, 1, 5),
('Calçados', '2023-09-01 15:00:00', 0, 1, 4),
('Alimentos Não Perecíveis', '2023-10-10 14:00:00', 1, 1, 6),
('Roupas', '2023-01-25 08:00:00', 1, 1, 7),
('Cesta Básica', '2023-03-20 09:30:00', 1, 1, 8),
('Material Escolar', '2023-04-15 11:00:00', 0, 1, 9),
('Livros', '2023-06-10 13:00:00', 1, 1, 7),
('Medicamentos', '2023-08-05 14:30:00', 0, 1, 9),
('Brinquedos', '2023-10-05 16:00:00', 1, 1, 8),
('Roupas', '2023-02-01 10:00:00', 1, 1, 10),
('Cesta Básica', '2023-04-01 11:30:00', 1, 1, 11),
('Medicamentos', '2023-05-01 09:00:00', 0, 1, 12),
('Material Escolar', '2023-07-01 12:30:00', 1, 1, 10),
('Alimentos Não Perecíveis', '2023-09-01 10:00:00', 1, 1, 12),
('Kit de Higiene', '2023-11-01 15:30:00', 0, 1, 11),
('Cesta Básica', '2023-02-15 10:00:00', 1, 1, 13),
('Kit de Higiene', '2023-04-15 11:00:00', 0, 1, 14),
('Medicamentos', '2023-06-15 09:30:00', 1, 1, 15),
('Material Escolar', '2023-08-15 13:00:00', 1, 1, 14),
('Alimentos Não Perecíveis', '2023-10-15 10:00:00', 1, 1, 15),
('Livros', '2023-12-15 15:30:00', 0, 1, 13),
('Cesta Básica', '2023-02-20 08:30:00', 1, 1, 16),
('Material Escolar', '2023-04-25 09:30:00', 0, 1, 17),
('Medicamentos', '2023-06-10 11:00:00', 1, 1, 18),
('Roupas de Inverno', '2023-07-05 13:00:00', 1, 1, 17),
('Brinquedos', '2023-09-20 15:30:00', 0, 1, 16),
('Alimentos Não Perecíveis', '2023-11-10 16:00:00', 1, 1, 18),
('Kit de Higiene', '2023-02-25 10:00:00', 1, 1, 19),
('Cesta Básica', '2023-04-15 11:30:00', 1, 1, 20),
('Material Escolar', '2023-05-10 12:00:00', 0, 1, 21),
('Livros', '2023-07-01 13:30:00', 1, 1, 19),
('Medicamentos', '2023-09-15 14:00:00', 0, 1, 21),
('Alimentos Não Perecíveis', '2023-10-25 16:00:00', 1, 1, 20),
('Roupas', '2023-03-01 09:30:00', 1, 1, 22),
('Cesta Básica', '2023-05-01 10:00:00', 1, 1, 23),
('Brinquedos', '2023-06-01 11:30:00', 0, 1, 24),
('Material Escolar', '2023-07-01 12:30:00', 1, 1, 22),
('Alimentos Não Perecíveis', '2023-08-01 13:00:00', 1, 1, 24),
('Kit de Higiene', '2023-09-01 14:30:00', 0, 1, 23),
('Roupas de Inverno', '2023-03-15 08:30:00', 1, 1, 25),
('Cesta Básica', '2023-05-15 09:00:00', 1, 1, 26),
('Material Escolar', '2023-06-15 10:30:00', 0, 1, 27),
('Medicamentos', '2023-07-15 11:00:00', 1, 1, 25),
('Livros', '2023-08-15 13:00:00', 0, 1, 27),
('Alimentos Não Perecíveis', '2023-09-15 15:00:00', 1, 1, 26),
('Cesta Básica', '2023-04-01 10:00:00', 1, 1, 28),
('Kit de Higiene', '2023-05-01 11:30:00', 0, 1, 29),
('Brinquedos', '2023-06-01 12:30:00', 1, 1, 30),
('Livros', '2023-07-01 13:30:00', 1, 1, 28),
('Roupas', '2023-08-01 14:30:00', 0, 1, 30),
('Medicamentos', '2023-09-01 15:30:00', 1, 1, 29);

-- Inserindo dados na tabela `usuario`
INSERT INTO `consol`.`usuario` (`coordenador`, `nome_usuario`, `email`, `senha`, `cpf`, `flag_aprovado`, `fk_instituicao`) VALUES 
(1, 'Maria da Silva', 'maria@example.com', 'senha123', '12345678901', 1, 1),
(0, 'João Oliveira', 'joao@example.com', 'senha456', '10987654321', 1, 2);

-- Inserindo dados na tabela `instituicao_familia`
INSERT INTO `consol`.`instituicao_familia` (`fk_instituicao`, `fk_familia`) VALUES 
(1, 1),
(2, 2),
(1, 3);
