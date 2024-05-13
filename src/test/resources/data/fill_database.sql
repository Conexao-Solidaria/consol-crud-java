-- Populando a tabela familia
INSERT INTO familia
(nome, cep, numero_casa, renda)
VALUES
    ('Família Silva', '12345678', 101, 2500.00),
    ('Família Oliveira', '87654321', 202, 3000.00),
    ('Família Santos', '11122333', 303, 1800.00);

-- Populando a tabela donatario
INSERT INTO donatario
(id_familia, data_cadastro, nome, rg, cpf, data_nascimento, telefone, celular, estado_civil, escolaridade, trabalhando)
VALUES
    (1, '2024-04-25', 'João Silva', '123456789', '98765432109', '1990-05-15', '1234567890', '987654321', 'Casado', 'Ensino Médio', 1),
    (1, '2024-04-26', 'Maria Silva', '987654321', '12345678901', '1995-10-20', '9876543210', '123456789', 'Casado', 'Ensino Superior', 0),
    (2, '2024-04-24', 'Pedro Oliveira', '456789123', '01234567890', '1985-03-10', '5556667777', '999888777', 'Solteiro', 'Ensino Médio', 1),
    (3, '2024-04-23', 'Ana Santos', '987654123', '45678901234', '1988-12-05', '2223334444', '777888999', 'Viúvo', 'Ensino Fundamental', 1);
