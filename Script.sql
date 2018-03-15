CREATE TABLE ADMINISTRADOR(
	Email VARCHAR(50),
	Senha VARCHAR(60) NOT NULL,
	Nome VARCHAR(70) NOT NULL,
	CONSTRAINT PK_Administrador_Email PRIMARY KEY(Email),
	CONSTRAINT Verifica_Admin_Email CHECK (Email LIKE '%@%')
);

CREATE TABLE CURSO(
	Codigo INT,
	Nome VARCHAR(50) NOT NULL,
	EmailAdministrador VARCHAR(50),
	CONSTRAINT PK_Curso_Codigo PRIMARY KEY(Codigo),
	CONSTRAINT FK_Curso_Administrador_Email FOREIGN KEY (EmailAdministrador) REFERENCES ADMINISTRADOR(Email) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE ALUNO(
	Matricula VARCHAR(15),
	Nome VARCHAR(70) NOT NULL,
	Senha VARCHAR(60) NOT NULL,
	EmailAdministrador VARCHAR(50),
	CodCurso INT,
	CONSTRAINT PK_Aluno_Matricula PRIMARY KEY(Matricula),
	CONSTRAINT FK_Aluno_Administrador_Email FOREIGN KEY (EmailAdministrador) REFERENCES ADMINISTRADOR(Email) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT FK_Aluno_Curso_Corigo FOREIGN KEY (CodCurso) REFERENCES CURSO(Codigo) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE PROFESSOR(
	Matricula VARCHAR(15),
	Nome VARCHAR(70) NOT NULL,
	Nota REAL,
	Foto VARCHAR,
	EmailAdministrador VARCHAR(50),
	CONSTRAINT PK_Professor_Matricula PRIMARY KEY(Matricula),
	CONSTRAINT FK_Professor_Administrador_Email FOREIGN KEY (EmailAdministrador) REFERENCES ADMINISTRADOR(Email) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE AVALIACAO(
	Codigo SERIAL,
	Data date NOT NULL,
	Comentario VARCHAR(200),
	CONSTRAINT PK_Avaliacao_Codigo PRIMARY KEY(Codigo),
	CONSTRAINT Avaliacao_Data_Valida CHECK (Data <= now())
);

CREATE TABLE CRITERIO(
	Codigo SERIAL,
	Ponto_avaliativo VARCHAR(50) NOT NULL,
	EmailAdministrador VARCHAR(50),
	CONSTRAINT PK_Criterio_Codigo PRIMARY KEY(Codigo),
	CONSTRAINT FK_Criterio_Administrador_Email FOREIGN KEY (EmailAdministrador) REFERENCES ADMINISTRADOR(Email) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE AVALIACAO_ALUNO_PROFESSOR(
	MatAluno VARCHAR(15),
	MatProfessor VARCHAR(15),
	CodAvaliacao INT,
	Media REAL,
	CONSTRAINT PK_AvaliacaoAlunoProfessor_MatAluno_MatProfessor_CodAvaliacao PRIMARY KEY(MatAluno, MatProfessor, CodAvaliacao),
	CONSTRAINT FK_AvaliacaoAlunoProfessor_Aluno_Matricula FOREIGN KEY (MatAluno) REFERENCES ALUNO(Matricula) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT FK_AvaliacaoAlunoProfessor_Professor_Matricula FOREIGN KEY (MatProfessor) REFERENCES PROFESSOR(Matricula) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT FK_AvaliacaoAlunoProfessor_Avaliacao_Codigo FOREIGN KEY (CodAvaliacao) REFERENCES AVALIACAO(Codigo) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE CRITERIO_AVALIACAO(
	CodAvaliacao INT,
	CodCriterio INT,
	Pontuacao REAL NOT NULL,
	CONSTRAINT PK_CriterioAvaliacao_CodAvaliacao_CodCriterio PRIMARY KEY(CodAvaliacao, CodCriterio),
	CONSTRAINT FK_CriterioAvaliacao_Avaliacao_Codigo FOREIGN KEY (CodAvaliacao) REFERENCES AVALIACAO(Codigo) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT FK_CriterioAvaliacao_Criterio_Codigo FOREIGN KEY (CodCriterio) REFERENCES CRITERIO(Codigo) ON UPDATE CASCADE ON DELETE RESTRICT

);

CREATE TABLE PROFESSOR_CURSO(
	CodCurso INT,
	MatProfessor VARCHAR(15),
	CONSTRAINT PK_ProfessorCurso_CodCurso_MatProfessor PRIMARY KEY(CodCurso,MatProfessor),
	CONSTRAINT FK_ProfessorCurso_Curso_Codigo FOREIGN KEY (CodCurso) REFERENCES CURSO(Codigo) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT FK_ProfessorCurso_Professor_Matricula FOREIGN KEY (MatProfessor) REFERENCES PROFESSOR(Matricula) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE AVALIACAO_SEMESTRAL(
	MatProfessor VARCHAR(15),
	CodCurso INT,
	Semestre VARCHAR(7),
	Nota REAL,
	CONSTRAINT PK_AvaliacaoSemestral_MatProfessor_CodCurso_Semetre PRIMARY KEY (MatProfessor,CodCurso,Semestre),
	CONSTRAINT FK_AvaliacaoSemetral_Professor_Matricula FOREIGN KEY (MatProfessor) REFERENCES Professor(Matricula) ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT FK_AvaliacaoSemetral_Curso_Codigo FOREIGN KEY (CodCurso) REFERENCES Curso(Codigo) ON UPDATE CASCADE ON DELETE RESTRICT
);