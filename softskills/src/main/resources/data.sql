INSERT INTO usuario (nome_completo, nome_usuario, senha, papel, ativo) VALUES 
('Daricelio', 'dari123', '$2a$12$V03BcpEIEafC1RaY.JBClOH7zxwQivzgu1NEAJUT4Wrza8nlXRi.S', 'COORDENADOR', true),
('Daniel', 'danzinho123', '$2a$12$vKtQzByx.nrmTRMPiDjh0OmDdvk2V..qSpTzhPw0s8SFEwtAxRp5a', 'PROFESSOR', true),
('Limeira', 'limoeiro', '$2a$12$jMfSl06n45qSGeg94Kf07.KO/sEXOKqxQv/Fu.sCou2/vAvdM/zKO', 'PROFESSOR', true),
('Nasserala', 'macarronada', '$2a$12$68h2CqQPIgwzKUojx4DbJeYMXX5cn7RpkBuyjqPyzD4UQXYobfwbC', 'PROFESSOR', true),
('Rodrigo', 'rodrigozinho', '$2a$12$8h/.Qj9uZKMNc4izXE5oU.FKMAVkjBv6kfKYdqtNHDe0JPetTgzkC', 'PROFESSOR', true),
('Catarina', 'catrina', '$2a$12$LM8KZtSmv.DBZUE46o0DrOapbwn7WscAPWYdS7eMjMmt2/uZdlYGq', 'PROFESSOR', true),
('Admin','admin','$2a$12$ZDa72F.OQrgP2bA05mtrTecYj/qRvNpXljP2j39b0BTy2tHURSQSi','ADMIN',true);

INSERT INTO coordenador ( nome, email, telefone, usuario_id) VALUES 
('Daricelio', 'daricelio@sou.ufac.br', '(68)98756-2547', 1);

INSERT INTO professor ( nome, email, telefone, usuario_id) VALUES 
('Daniel', 'daniel@sou.ufac.br', '(68)98521-2548', 2),
('Limeira', 'limeira@sou.ufac.br', '(68)96532-7824', 3),
('Nasserala', 'nasserala@sou.ufac.br', '(68)98031-9634', 4),
('Rodrigo', 'rodrigo@sou.ufac.br', '(68)98346-9745', 5),
('Catarina', 'catarina@sou.ufac.br', '(68)98483-9634', 6);

INSERT INTO capacitacao ( nome, carga_horaria, data_inicio, data_fim, coordenador_id) VALUES 
('Web Academy', 40, '2024-10-01', '2024-12-01', 1);

INSERT INTO turma ( nome, quant_vagas,data_inicio,data_fim, capacitacao_id) VALUES 
('Turma 1', 30,'2024-10-01', '2024-12-01', 1),
('Turma 2', 30,'2024-10-01', '2024-12-01', 1),
('Turma 3', 30,'2024-10-01', '2024-12-01', 1);

INSERT INTO disciplina ( codigo, nome, carga_horaria, data_inicio, data_fim) VALUES 
('D001', 'Topicos Fundamentais', 15,'2024-10-01', '2024-12-01'),
('D002', 'Ux e Desing Thinking', 10,'2024-10-01', '2024-12-01'),
('D003', 'Fundamentos de Programação Front-End',20,'2024-10-01', '2024-12-01'),
('D004', 'Fundamentos de Programação Back-End', 20,'2024-10-01', '2024-12-01'),
('D005', 'Computação em Nuvem', 10,'2024-10-01', '2024-12-01'),
('D006', 'Frameworks Front - End', 15,'2024-10-01', '2024-12-01'),
('D007', 'Frameworks Back - End', 15,'2024-10-01', '2024-12-01'),
('D404', 'Testes', 15,'2024-10-01', '2024-12-01'),
('D008', 'Integração Contínua', 10,'2024-10-01', '2024-12-01'),
('D009', 'Programação Avançada Front - End', 25,'2024-10-01', '2024-12-01'),
('D010', 'Programação Avançada Back - End', 25,'2024-10-01', '2024-12-01'),
('D011', 'Tópicos Emergentes em WEB-Mobile', 15,'2024-10-01', '2024-12-01'),
('D666', 'Hands On', 105,'2024-10-01', '2024-12-01');

INSERT INTO skill ( nome, descricao, tipo) VALUES 
( 'Prototipagem de Interfaces', 'Habilidade em criar protótipos de interfaces de usuário', 'HARD_SKILL'),
( 'Arquitetura de Informação', 'Habilidade em organizar a estrutura de informação de sistemas', 'HARD_SKILL'),
( 'HTML e CSS', 'Habilidade em construir páginas web usando HTML e CSS', 'HARD_SKILL'),
( 'JavaScript', 'Habilidade em programação com JavaScript', 'HARD_SKILL'),
( 'Git', 'Habilidade em controle de versão com Git', 'HARD_SKILL'),
( 'Java', 'Habilidade em programação Java', 'HARD_SKILL'),
( 'Node.js', 'Habilidade em desenvolvimento de back-end com Node.js', 'HARD_SKILL'),
( 'Bancos de Dados', 'Habilidade em trabalhar com bancos de dados relacionais', 'HARD_SKILL'),
( 'AWS', 'Habilidade em utilizar serviços da AWS para computação em nuvem', 'HARD_SKILL'),
( 'Docker', 'Habilidade em usar containers com Docker', 'HARD_SKILL'),
( 'React', 'Habilidade em desenvolvimento de interfaces com React', 'HARD_SKILL'),
( 'Angular', 'Habilidade em desenvolvimento de interfaces com Angular', 'HARD_SKILL'),
( 'Spring Boot', 'Habilidade em desenvolver aplicações com Spring Boot', 'HARD_SKILL'),
( 'Testes Unitários', 'Habilidade em realizar testes unitários', 'HARD_SKILL'),
( 'Automação de Testes', 'Habilidade em criar automação de testes', 'HARD_SKILL'),
( 'CI/CD', 'Habilidade em configurar integração contínua', 'HARD_SKILL'),
( 'TypeScript', 'Habilidade em programação usando TypeScript', 'HARD_SKILL'),
( 'APIs RESTful', 'Habilidade em criar APIs RESTful', 'HARD_SKILL'),
( 'Progressive Web Apps', 'Habilidade em desenvolver aplicações web progressivas', 'HARD_SKILL'),
( 'DevOps', 'Habilidade em práticas de DevOps', 'HARD_SKILL'),
( 'Resolução de Problemas', 'Habilidade em resolver problemas complexos', 'SOFT_SKILL'),
( 'Colaboração', 'Habilidade de trabalhar de forma colaborativa em equipe', 'SOFT_SKILL'),
( 'Atenção aos Detalhes', 'Capacidade de perceber pequenos detalhes que fazem a diferença', 'SOFT_SKILL'),
( 'Criatividade', 'Capacidade de gerar ideias e soluções inovadoras', 'SOFT_SKILL'),
( 'Organização', 'Habilidade em manter processos e informações organizadas', 'SOFT_SKILL'),
( 'Adaptabilidade', 'Capacidade de se adaptar a novas situações e desafios', 'SOFT_SKILL'),
( 'Persistência', 'Capacidade de continuar se esforçando mesmo diante de dificuldades', 'SOFT_SKILL'),
( 'Autogestão', 'Capacidade de gerir o próprio tempo e trabalho sem supervisão direta', 'SOFT_SKILL');


INSERT INTO aluno (nome, email, telefone,sexo, status) VALUES 
( 'Allan Victor san', 'allan.victor@gmail.com', '(68)98623-2354','M', 'ativo'),
( 'Paulo Guina', 'PaulãoDoAmassa@gmail.com', '(69)66699-6969','M', 'ativo'),
( 'Beatriz Souza', 'beatriz.souza@gmail.com', '(68)98234-1234', 'F', 'ativo'),
('Carlos Lima', 'carlos.lima@gmail.com', '(68)98765-4321', 'M', 'ativo'),
('Diana Oliveira', 'diana.oliveira@gmail.com', '(68)98512-4578', 'F', 'ativo'),
('Eduardo Costa', 'eduardo.costa@gmail.com', '(68)98123-6543', 'M', 'ativo'),
('Fernanda Alves', 'fernanda.alves@gmail.com', '(68)98345-6789', 'F', 'ativo'),
('Gabriel Menezes', 'gabriel.menezes@gmail.com', '(68)98012-3456', 'M', 'ativo'),
('Helena Pereira', 'helena.pereira@gmail.com', '(68)98723-8976', 'F', 'ativo'),
('Igor Ferreira', 'igor.ferreira@gmail.com', '(68)98456-3241', 'M', 'ativo'),
('Juliana Santos', 'juliana.santos@gmail.com', '(68)98654-9876', 'F', 'ativo'),
( 'Lucas Andrade', 'lucas.andrade@gmail.com', '(68)98652-3241','M', 'ativo'),
( 'Mariana Silva', 'mariana.silva@gmail.com', '(68)98745-9987','F', 'ativo'),
( 'Carla Souza', 'carla.souza@gmail.com', '(68)98231-7412','F', 'ativo'),
( 'Ricardo Pereira', 'ricardo.pereira@gmail.com', '(68)98364-8475','M', 'ativo'),
( 'Bruno Lima', 'bruno.lima@gmail.com', '(68)98123-6589','M', 'ativo'),
( 'Amanda Costa', 'amanda.costa@gmail.com', '(68)98421-3965','F', 'ativo'),
( 'Felipe Cardoso', 'felipe.cardoso@gmail.com', '(68)98274-8954','M', 'ativo'),
( 'Patrícia Santos', 'patricia.santos@gmail.com', '(68)98653-2145','F', 'ativo'),
( 'Daniel Oliveira', 'daniel.oliveira@gmail.com', '(68)98742-8563','M', 'ativo'),
( 'Renata Ferreira', 'renata.ferreira@gmail.com', '(68)98456-1423','F', 'ativo'),
( 'Gabriel Rocha', 'gabriel.rocha@gmail.com', '(68)98237-9514','M', 'ativo'),
( 'Luciana Castro', 'luciana.castro@gmail.com', '(68)98125-3497','F', 'ativo'),
( 'Thiago Gomes', 'thiago.gomes@gmail.com', '(68)98723-6521','M', 'ativo'),
( 'Isabel Mendes', 'isabel.mendes@gmail.com', '(68)98147-2695','F', 'ativo'),
( 'Paulo Almeida', 'paulo.almeida@gmail.com', '(68)98412-5378','M', 'ativo'),
( 'Sofia Ramos', 'sofia.ramos@gmail.com', '(68)98254-1637','F', 'ativo'),
( 'Vinícius Dias', 'vinicius.dias@gmail.com', '(68)98352-2479','M', 'ativo'),
( 'Beatriz Martins', 'beatriz.martins@gmail.com', '(68)98136-5472','F', 'ativo'),
( 'Rafael Teixeira', 'rafael.teixeira@gmail.com', '(68)98756-1236','M', 'ativo'),
( 'Fernanda Carvalho', 'fernanda.carvalho@gmail.com', '(68)98632-8594','F', 'ativo'),
( 'Pedro Moreira', 'pedro.moreira@gmail.com', '(68)98361-4523','M', 'ativo'),
( 'Juliana Barbosa', 'juliana.barbosa@gmail.com', '(68)98146-3245','F', 'ativo'),
( 'Matheus Batista', 'matheus.batista@gmail.com', '(68)98452-1763','M', 'ativo'),
( 'Camila Borges', 'camila.borges@gmail.com', '(68)98235-6594','F', 'ativo'),
( 'Ana Clara', 'ana.clara@gmail.com', '(68)98741-8956','F', 'ativo'),
( 'Lucas Henrique', 'lucas.henrique@gmail.com', '(68)98123-6741','M', 'ativo'),
( 'Maria Cecília', 'maria.cecilia@gmail.com', '(68)98456-8743','F', 'ativo'),
( 'Eduardo Vieira', 'eduardo.vieira@gmail.com', '(68)98321-5964','M', 'ativo'),
( 'Roberta Alves', 'roberta.alves@gmail.com', '(68)98174-3265','F', 'ativo'),
( 'Gustavo Farias', 'gustavo.farias@gmail.com', '(68)98741-5682','M', 'ativo'),
( 'Larissa Ribeiro', 'larissa.ribeiro@gmail.com', '(68)98623-1475','F', 'ativo'),
( 'Marcos Vinícius', 'marcos.vinicius@gmail.com', '(68)98265-8541','M', 'ativo'),
( 'Aline Souza', 'aline.souza@gmail.com', '(68)98471-2365','F', 'ativo'),
( 'Leonardo Azevedo', 'leonardo.azevedo@gmail.com', '(68)98365-4178','M', 'ativo'),
( 'Marcela Nunes', 'marcela.nunes@gmail.com', '(68)98236-4521','F', 'ativo'),
( 'Victor Almeida', 'victor.almeida@gmail.com', '(68)98174-6254','M', 'ativo'),
( 'Bruna Santos', 'bruna.santos@gmail.com', '(68)98463-7592','F', 'ativo'),
( 'João Paulo', 'joao.paulo@gmail.com', '(68)98652-4136','M', 'ativo'),
( 'Helena Braga', 'helena.braga@gmail.com', '(68)98721-9653','F', 'ativo'),
( 'Isadora Macedo', 'isadora.macedo@gmail.com', '(68)98356-2147','F', 'desistente'),
( 'André Souza', 'andre.souza@gmail.com', '(68)98631-8547','M', 'desistente'),
( 'Clara Moura', 'clara.moura@gmail.com', '(68)98123-6598','F', 'desistente'),
( 'Fábio Castro', 'fabio.castro@gmail.com', '(68)98452-1436','M', 'desistente'),
( 'Alice Cardoso', 'alice.cardoso@gmail.com', '(68)98214-9635','F', 'desistente'),
( 'Murilo Nogueira', 'murilo.nogueira@gmail.com', '(68)98152-3264','M', 'desistente'),
( 'Viviane Campos', 'viviane.campos@gmail.com', '(68)98625-8432','F', 'desistente'),
( 'Renato Silva', 'renato.silva@gmail.com', '(68)98741-3256','M', 'desistente'),
( 'Larissa Monteiro', 'larissa.monteiro@gmail.com', '(68)98324-6854','F', 'desistente'),
( 'Henrique Guimarães', 'henrique.guimaraes@gmail.com', '(68)98432-7546','M', 'desistente'),
('Adriana Gomes', 'adriana.gomes@gmail.com', '(68)98123-4321', 'F', 'ativo'),
('Bruno Lima', 'bruno.lima@gmail.com', '(68)98678-4567', 'M', 'ativo'),
('Carla Ribeiro', 'carla.ribeiro@gmail.com', '(68)98512-8765', 'F', 'ativo'),
('Daniel Martins', 'daniel.martins@gmail.com', '(68)98234-6789', 'M', 'ativo'),
('Eliana Silva', 'eliana.silva@gmail.com', '(68)98345-2345', 'F', 'ativo'),
('Fernando Costa', 'fernando.costa@gmail.com', '(68)98112-6789', 'M', 'desistente'),
('Gabriela Pires', 'gabriela.pires@gmail.com', '(68)98456-5678', 'F', 'ativo'),
('Hugo Almeida', 'hugo.almeida@gmail.com', '(68)98212-1234', 'M', 'ativo'),
('Isabela Santos', 'isabela.santos@gmail.com', '(68)98112-3456', 'F', 'ativo'),
('Jorge Almeida', 'jorge.almeida@gmail.com', '(68)98678-7890', 'M', 'desistente'),
('Karen Lima', 'karen.lima@gmail.com', '(68)98234-5678', 'F', 'desistente'),
('Lucas Ferreira', 'lucas.ferreira@gmail.com', '(68)98001-3456', 'M', 'ativo'),
('Marta Souza', 'marta.souza@gmail.com', '(68)98512-6789', 'F', 'ativo'),
('Nathan Lima', 'nathan.lima@gmail.com', '(68)98345-5432', 'M', 'ativo'),
('Olga Ribeiro', 'olga.ribeiro@gmail.com', '(68)98654-1234', 'F', 'ativo'),
('Paulo Santos', 'paulo.santos@gmail.com', '(68)98101-2345', 'M', 'ativo'),
('Quintina Ferreira', 'quintina.ferreira@gmail.com', '(68)98456-7890', 'F', 'ativo'),
('Rodrigo Martins', 'rodrigo.martins@gmail.com', '(68)98022-4567', 'M', 'desistente'),
('Simone Costa', 'simone.costa@gmail.com', '(68)98212-5678', 'F', 'desistente'),
('Tomas Almeida', 'tomas.almeida@gmail.com', '(68)98678-6789', 'M', 'ativo'),
('Ursula Lima', 'ursula.lima@gmail.com', '(68)98112-4321', 'F', 'ativo'),
('Valentina Pires', 'valentina.pires@gmail.com', '(68)98512-7890', 'F', 'ativo'),
('William Souza', 'william.souza@gmail.com', '(68)98011-1234', 'M', 'desistente'),
('Xuxa Ribeiro', 'xuxa.ribeiro@gmail.com', '(68)98234-2345', 'F', 'ativo'),
('Yvan Ferreira', 'yvan.ferreira@gmail.com', '(68)98112-6789', 'M', 'ativo'),
('Jéssica Oliveira', 'jessica.oliveira@gmail.com', '(68)98234-5678', 'F', 'ativo'),
('Kleber Martins', 'kleber.martins@gmail.com', '(68)98345-6789', 'M', 'ativo'),
('Luana Ferreira', 'luana.ferreira@gmail.com', '(68)98123-7890', 'F', 'ativo'),
('Mário Almeida', 'mario.almeida@gmail.com', '(68)98456-6789', 'M', 'ativo'),
('Zita Santos', 'zita.santos@gmail.com', '(68)98654-7890', 'F', 'ativo');

INSERT INTO turma_disciplina(turma_id, disciplina_id, professor_id) VALUES
(1,1,2),
(1,2,4),
(1,3,2),
(1,4,2),
(1,5,3),
(1,6,1),
(1,7,1),
(1,8,2),
(1,9,1),
(1,10,1),
(1,11,1),
(1,12,1),
(1,13,1),
(2,1,2),
(2,2,2),
(2,3,4),
(2,4,2),
(2,5,3),
(2,6,1),
(2,7,1),
(2,8,2),
(2,9,1),
(2,10,1),
(2,11,1),
(2,12,1),
(2,13,1),
(3,1,2),
(3,2,2),
(3,3,4),
(3,4,2),
(3,5,3),
(3,6,1),
(3,7,1),
(3,8,2),
(3,9,1),
(3,10,1),
(3,11,1),
(3,12,1),
(3,13,1);

INSERT INTO turma_aluno (turma_id, aluno_id) VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(1,14),
(1,15),
(1,16),
(1,17),
(1,18),
(1,19),
(1,20),
(1,21),
(1,22),
(1,23),
(1,24),
(1,25),
(1,26),
(1,27),
(1,28),
(1,29),
(1,30),
(2,31),
(2,32),
(2,33),
(2,34),
(2,35),
(2,36),
(2,37),
(2,38),
(2,39),
(2,40),
(2,41),
(2,42),
(2,43),
(2,44),
(2,45),
(2,46),
(2,47),
(2,48),
(2,49),
(2,50),
(2,51),
(2,52),
(2,53),
(2,54),
(2,55),
(2,56),
(2,57),
(2,58),
(2,59),
(2,60),
(3,61),
(3,62),
(3,63),
(3,64),
(3,65),
(3,66),
(3,67),
(3,68),
(3,69),
(3,70),
(3,71),
(3,72),
(3,73),
(3,74),
(3,75),
(3,76),
(3,77),
(3,78),
(3,79),
(3,80),
(3,81),
(3,82),
(3,83),
(3,84),
(3,85),
(3,86),
(3,87),
(3,88),
(3,89),
(3,90);

-- Ligação entre as disciplinas e todas as soft skills
INSERT INTO disciplina_skill (disciplina_id, skill_id) VALUES
(1, 21), -- Tópicos Fundamentais / Resolução de Problemas
(1, 22), -- Tópicos Fundamentais / Colaboração
(1, 23), -- Tópicos Fundamentais / Atenção aos Detalhes
(1, 24), -- Tópicos Fundamentais / Criatividade
(1, 25), -- Tópicos Fundamentais / Organização
(1, 26), -- Tópicos Fundamentais / Adaptabilidade
(1, 27), -- Tópicos Fundamentais / Persistência
(1, 28), -- Tópicos Fundamentais / Autogestão

(2, 21), -- Ux e Desing Thinking / Resolução de Problemas
(2, 22), -- Ux e Desing Thinking / Colaboração
(2, 23), -- Ux e Desing Thinking / Atenção aos Detalhes
(2, 24), -- Ux e Desing Thinking / Criatividade
(2, 25), -- Ux e Desing Thinking / Organização
(2, 26), -- Ux e Desing Thinking / Adaptabilidade
(2, 27), -- Ux e Desing Thinking / Persistência
(2, 28), -- Ux e Desing Thinking / Autogestão

(3, 21), -- Fundamentos de Programação Front-End / Resolução de Problemas
(3, 22), -- Fundamentos de Programação Front-End / Colaboração
(3, 23), -- Fundamentos de Programação Front-End / Atenção aos Detalhes
(3, 24), -- Fundamentos de Programação Front-End / Criatividade
(3, 25), -- Fundamentos de Programação Front-End / Organização
(3, 26), -- Fundamentos de Programação Front-End / Adaptabilidade
(3, 27), -- Fundamentos de Programação Front-End / Persistência
(3, 28), -- Fundamentos de Programação Front-End / Autogestão

(4, 21), -- Fundamentos de Programação Back-End / Resolução de Problemas
(4, 22), -- Fundamentos de Programação Back-End / Colaboração
(4, 23), -- Fundamentos de Programação Back-End / Atenção aos Detalhes
(4, 24), -- Fundamentos de Programação Back-End / Criatividade
(4, 25), -- Fundamentos de Programação Back-End / Organização
(4, 26), -- Fundamentos de Programação Back-End / Adaptabilidade
(4, 27), -- Fundamentos de Programação Back-End / Persistência
(4, 28), -- Fundamentos de Programação Back-End / Autogestão

(5, 21), -- Computação em Nuvem / Resolução de Problemas
(5, 22), -- Computação em Nuvem / Colaboração
(5, 23), -- Computação em Nuvem / Atenção aos Detalhes
(5, 24), -- Computação em Nuvem / Criatividade
(5, 25), -- Computação em Nuvem / Organização
(5, 26), -- Computação em Nuvem / Adaptabilidade
(5, 27), -- Computação em Nuvem / Persistência
(5, 28), -- Computação em Nuvem / Autogestão

(6, 21), -- Frameworks Front - End / Resolução de Problemas
(6, 22), -- Frameworks Front - End / Colaboração
(6, 23), -- Frameworks Front - End / Atenção aos Detalhes
(6, 24), -- Frameworks Front - End / Criatividade
(6, 25), -- Frameworks Front - End / Organização
(6, 26), -- Frameworks Front - End / Adaptabilidade
(6, 27), -- Frameworks Front - End / Persistência
(6, 28), -- Frameworks Front - End / Autogestão

(7, 21), -- Frameworks Back - End / Resolução de Problemas
(7, 22), -- Frameworks Back - End / Colaboração
(7, 23), -- Frameworks Back - End / Atenção aos Detalhes
(7, 24), -- Frameworks Back - End / Criatividade
(7, 25), -- Frameworks Back - End / Organização
(7, 26), -- Frameworks Back - End / Adaptabilidade
(7, 27), -- Frameworks Back - End / Persistência
(7, 28), -- Frameworks Back - End / Autogestão

(8, 21), -- Testes / Resolução de Problemas
(8, 22), -- Testes / Colaboração
(8, 23), -- Testes / Atenção aos Detalhes
(8, 24), -- Testes / Criatividade
(8, 25), -- Testes / Organização
(8, 26), -- Testes / Adaptabilidade
(8, 27), -- Testes / Persistência
(8, 28), -- Testes / Autogestão

(9, 21), -- Integração Contínua / Resolução de Problemas
(9, 22), -- Integração Contínua / Colaboração
(9, 23), -- Integração Contínua / Atenção aos Detalhes
(9, 24), -- Integração Contínua / Criatividade
(9, 25), -- Integração Contínua / Organização
(9, 26), -- Integração Contínua / Adaptabilidade
(9, 27), -- Integração Contínua / Persistência
(9, 28), -- Integração Contínua / Autogestão

(10, 21), -- Programação Avançada Front - End / Resolução de Problemas
(10, 22), -- Programação Avançada Front - End / Colaboração
(10, 23), -- Programação Avançada Front - End / Atenção aos Detalhes
(10, 24), -- Programação Avançada Front - End / Criatividade
(10, 25), -- Programação Avançada Front - End / Organização
(10, 26), -- Programação Avançada Front - End / Adaptabilidade
(10, 27), -- Programação Avançada Front - End / Persistência
(10, 28), -- Programação Avançada Front - End / Autogestão

(11, 21), -- Programação Avançada Back - End / Resolução de Problemas
(11, 22), -- Programação Avançada Back - End / Colaboração
(11, 23), -- Programação Avançada Back - End / Atenção aos Detalhes
(11, 24), -- Programação Avançada Back - End / Criatividade
(11, 25), -- Programação Avançada Back - End / Organização
(11, 26), -- Programação Avançada Back - End / Adaptabilidade
(11, 27), -- Programação Avançada Back - End / Persistência
(11, 28), -- Programação Avançada Back - End / Autogestão

(12, 21), -- Tópicos Emergentes em WEB-Mobile / Resolução de Problemas
(12, 22), -- Tópicos Emergentes em WEB-Mobile / Colaboração
(12, 23), -- Tópicos Emergentes em WEB-Mobile / Atenção aos Detalhes
(12, 24), -- Tópicos Emergentes em WEB-Mobile / Criatividade
(12, 25), -- Tópicos Emergentes em WEB-Mobile / Organização
(12, 26), -- Tópicos Emergentes em WEB-Mobile / Adaptabilidade
(12, 27), -- Tópicos Emergentes em WEB-Mobile / Persistência
(12, 28), -- Tópicos Emergentes em WEB-Mobile / Autogestão

(13, 21), -- Hands On / Resolução de Problemas
(13, 22), -- Hands On / Colaboração
(13, 23), -- Hands On / Atenção aos Detalhes
(13, 24), -- Hands On / Criatividade
(13, 25), -- Hands On / Organização
(13, 26), -- Hands On / Adaptabilidade
(13, 27), -- Hands On / Persistência
(13, 28); -- Hands On / Autogestão


-- Ligação entre as disciplinas e hard skills específicas
INSERT INTO disciplina_skill (disciplina_id, skill_id) VALUES
(2, 1), -- Ux e Desing Thinking / Prototipagem de Interfaces
(2, 2), -- Ux e Desing Thinking / Arquitetura de Informação

(3, 3), -- Fundamentos de Programação Front-End / HTML e CSS
(3, 4), -- Fundamentos de Programação Front-End / JavaScript

(4, 6), -- Fundamentos de Programação Back-End / Java
(4, 7), -- Fundamentos de Programação Back-End / Node.js

(5, 9), -- Computação em Nuvem / AWS
(5, 10), -- Computação em Nuvem / Docker

(6, 11), -- Frameworks Front-End / React
(6, 12), -- Frameworks Front-End / Angular

(7, 13), -- Frameworks Back-End / Spring Boot

(8, 14), -- Testes / Testes Unitários
(8, 15), -- Testes / Automação de Testes

(9, 16), -- Integração Contínua / CI/CD

(10, 4), -- Programação Avançada Front-End / JavaScript
(10, 17), -- Programação Avançada Front-End / TypeScript

(11, 7), -- Programação Avançada Back-End / Node.js
(11, 6), -- Programação Avançada Back-End / Java

(12, 18), -- Tópicos Emergentes em WEB-Mobile / Progressive Web Apps

(13, 19), -- Hands On / DevOps
(13, 9),  -- Hands On / AWS
(13, 10); -- Hands On / Docker


 








