insert into Aluno values (123456789,'Marcelo Vieira');
insert into Aluno values (987654321,'John Connor');
insert into Aluno values (154689479,'John Wick');
insert into Aluno values (354691356,'Marty McFly Jr.');
insert into Aluno values (216597359,'Emmett Brown');
insert into Aluno values (164982634,'Lorraine Baines');
insert into Aluno values (132698259,'George McFly');

INSERT INTO TB_ATIVIDADE (ATV_NOME, ATV_percentualCargaHoraria, ATV_maximoAtividadesSemestre, ATV_percentualPorAtividade)
values ('Monitoria', 50, 1, 100);

INSERT INTO TB_ATIVIDADE (ATV_NOME, ATV_percentualCargaHoraria, ATV_maximoAtividadesSemestre, ATV_percentualPorAtividade)
values ('Disciplinas extracurriculares', 40, 1, 100);

INSERT INTO TB_ATIVIDADE (ATV_NOME, ATV_percentualCargaHoraria, ATV_maximoAtividadesSemestre, ATV_percentualPorAtividade)
values ('Participação em Colegiado / Conselho', 10, 1, 100);

INSERT INTO TB_ATIVIDADE (ATV_NOME, ATV_percentualCargaHoraria, ATV_maximoAtividadesSemestre, ATV_percentualPorAtividade)
values ('Cursos e Minicursos', 10, 2, 50);


INSERT INTO TB_LANCAMENTO_ATIVIDADE (LANC_QUANTIDADE_HORAS, LANC_DATAINICIO, LANC_DATAFIM, ALUNO_RA, ATIVIDADE_CODIGO)
values (100, '2018-10-20', '2018-11-20', 123456789, 1);