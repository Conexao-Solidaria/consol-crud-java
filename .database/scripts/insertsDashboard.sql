INSERT INTO `consol`.`titular` (nome, rg, cpf, data_nascimento, telefone1, telefone2, estado_civil, escolaridade, trabalhando, ocupacao, referencia_s3, fk_familia)
VALUES 
('Ana Silva', '123456789', '11122233344', '2012-10-25', '1234567890', '0987654321', 'Solteiro', 'Fundamental', 0, 'Estudante', 'referencia_ana', 1),
('Carlos Santos', '987654321', '22233344455', '2016-06-15', '0981234567', NULL, 'Solteiro', 'Fundamental', 0, 'Estudante', 'referencia_carlos', 2),
('Beatriz Almeida', '543216789', '33344455566', '2020-01-30', '1230984567', '5678901234', 'Solteiro', 'Pré-escolar', 0, 'Estudante', 'referencia_beatriz', 3),
('Lucas Pereira', '234567891', '44455566677', '2018-08-12', '9876541230', NULL, 'Solteiro', 'Fundamental', 0, 'Estudante', 'referencia_lucas', 1),
('Mariana Costa', '345678912', '55566677788', '2023-05-05', '3456781234', NULL, 'Solteiro', 'Pré-escolar', 0, 'Estudante', 'referencia_mariana', 2);

INSERT INTO `consol`.`titular` (nome, rg, cpf, data_nascimento, telefone1, telefone2, estado_civil, escolaridade, trabalhando, ocupacao, referencia_s3, fk_familia)
VALUES 
('João Ferreira', '123456789', '11122233344', '2009-03-14', '1234567890', '0987654321', 'Solteiro', 'Ensino Médio', 1, 'Estudante', 'referencia_joao', 1),
('Maria Oliveira', '987654321', '22233344455', '1998-11-25', '0981234567', NULL, 'Solteiro', 'Ensino Superior', 1, 'Estudante', 'referencia_maria', 2),
('Pedro Silva', '543216789', '33344455566', '1995-07-05', '1230984567', '5678901234', 'Casado', 'Ensino Superior', 1, 'Analista', 'referencia_pedro', 3),
('Carla Mendes', '234567891', '44455566677', '2000-04-22', '9876541230', NULL, 'Solteiro', 'Ensino Médio', 1, 'Estudante', 'referencia_carla', 1),
('Roberto Costa', '345678912', '55566677788', '1990-08-19', '3456781234', NULL, 'Solteiro', 'Ensino Fundamental', 1, 'Motorista', 'referencia_roberto', 2);

INSERT INTO `consol`.`titular` (nome, rg, cpf, data_nascimento, telefone1, telefone2, estado_civil, escolaridade, trabalhando, ocupacao, referencia_s3, fk_familia)
VALUES 
('Ana Costa', '123456789', '11122233344', '1985-02-14', '1234567890', '0987654321', 'Casada', 'Ensino Superior', 1, 'Professora', 'referencia_ana', 1),
('Carlos Almeida', '987654321', '22233344455', '1978-05-22', '0981234567', NULL, 'Divorciado', 'Pós-graduação', 1, 'Engenheiro', 'referencia_carlos', 2),
('Fernanda Silva', '543216789', '33344455566', '1990-10-30', '1230984567', '5678901234', 'Solteira', 'Ensino Superior', 1, 'Analista', 'referencia_fernanda', 3),
('Roberto Oliveira', '234567891', '44455566677', '1970-04-10', '9876541230', NULL, 'Casado', 'Ensino Médio', 1, 'Técnico', 'referencia_roberto', 1),
('Patrícia Mendes', '345678912', '55566677788', '1965-08-19', '3456781234', NULL, 'Viúva', 'Ensino Superior', 1, 'Administradora', 'referencia_patricia', 2);

INSERT INTO `consol`.`familia` (`nome`, `cep`, `numero_casa`, `renda`, `flag_retirada`, `data_cadastro`) VALUES 
('Família Souza', '12345678', 100, 1500.00, 1, '2024-10-01');

INSERT INTO `consol`.`titular` (nome, rg, cpf, data_nascimento, telefone1, telefone2, estado_civil, escolaridade, trabalhando, ocupacao, referencia_s3, fk_familia)
VALUES 
('José da Silva', '123456789', '11122233344', '1955-06-15', '1234567890', NULL, 'Casado', 'Ensino Médio', 1, 'Aposentado', 'referencia_jose', 1),
('Maria Pereira', '987654321', '22233344455', '1948-03-22', '0981234567', '5678901234', 'Viúva', 'Ensino Superior', 1, 'Professora', 'referencia_maria', 2),
('Antônio Souza', '543216789', '33344455566', '1940-12-10', '1230984567', NULL, 'Casado', 'Ensino Fundamental', 1, 'Agricultor', 'referencia_antonio', 3),
('Célia Lima', '234567891', '44455566677', '1952-01-30', '9876541230', NULL, 'Solteira', 'Ensino Médio', 1, 'Técnica de Enfermagem', 'referencia_celia', 1),
('Cláudio Rocha', '345678912', '55566677788', '1945-08-19', '3456781234', NULL, 'Divorciado', 'Pós-graduação', 1, 'Engenheiro', 'referencia_claudio', 2);

INSERT INTO `consol`.`doacao` (descricao, data_doacao, flag_doacao_entregue, fk_instituicao, fk_titular)
VALUES 
('Doação de roupas', '2024-10-01 10:00:00', 1, 1, 1),
('Doação de alimentos', '2024-10-05 14:30:00', 1, 1, 2),
('Doação de brinquedos', '2024-10-10 09:15:00', 0, 1, 3),
('Doação de utensílios domésticos', '2024-10-15 11:45:00', 1, 1, 4),
('Doação de produtos de higiene', '2024-10-20 16:00:00', 1, 1, 5),
('Doação de roupas infantis', '2024-10-25 08:30:00', 0, 1, 6),
('Doação de alimentos não perecíveis', '2024-10-28 13:00:00', 1, 1, 7);

INSERT INTO `consol`.`doacao` (descricao, data_doacao, flag_doacao_entregue, fk_instituicao, fk_titular) VALUES
('Doação de alimentos não perecíveis', '2024-10-01 10:00:00', 1, 1, 6),  -- `consol`.`titular`: José da Silva
('Doação de roupas infantis', '2024-10-02 12:30:00', 1, 1, 7),         -- `consol`.`titular`: Maria Pereira
('Doação de produtos de higiene', '2024-10-03 09:15:00', 1, 1, 8),     -- `consol`.`titular`: Antônio Souza
('Doação de brinquedos', '2024-10-04 11:45:00', 1, 1, 9),             -- `consol`.`titular`: Célia Lima
('Doação de material escolar', '2024-10-05 14:00:00', 1, 1, 10),      -- `consol`.`titular`: Cláudio Rocha
('Doação de roupas para adultos', '2024-10-10 15:30:00', 1, 1, 11),    -- `consol`.`titular`: Ana Costa
('Doação de alimentos para pets', '2024-10-15 08:00:00', 1, 1, 12); 
