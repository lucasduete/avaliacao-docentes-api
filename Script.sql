CREATE TABLE ADMINISTRADOR(
	Nome VARCHAR(70) NOT NULL,
	Email VARCHAR(50) PRIMARY KEY,
	Senha VARCHAR(60) NOT NULL
);

CREATE TABLE CURSO(
	Nome VARCHAR(50) NOT NULL,
	Codigo int PRIMARY KEY,
	EmailAdministrador VARCHAR(50),
	FOREIGN KEY (EmailAdministrador) REFERENCES
	ADMINISTRADOR(Email)
);

CREATE TABLE ALUNO(
	Nome VARCHAR(70) NOT NULL,
	Senha VARCHAR(60) NOT NULL,
	Matricula VARCHAR(15) PRIMARY KEY,
	EmailAdministrador VARCHAR(50),
	FOREIGN KEY (EmailAdministrador) 
	REFERENCES ADMINISTRADOR(Email),
	CodCurso int,
	FOREIGN KEY (CodCurso) REFERENCES 
	CURSO(Codigo)
);

CREATE TABLE PROFESSOR(
	Nome VARCHAR(70) NOT NULL,
	Senha VARCHAR(50) NOT NULL,
	Matricula VARCHAR(15) PRIMARY KEY,
	Nota real,
	EmailAdministrador VARCHAR(50),
	FOREIGN KEY (EmailAdministrador) REFERENCES
	ADMINISTRADOR(Email)
);

CREATE TABLE AVALIACAO(
	Data date NOT NULL,
	Comentario VARCHAR(200) NOT NULL,
	Codigo int PRIMARY KEY
);

CREATE TABLE CRITERIO(
	Ponto_avaliativo VARCHAR(50) NOT NULL,
	Pontuacao real NOT NULL,
	Codigo int PRIMARY KEY,
	EmailAdministrador VARCHAR(50),
	FOREIGN KEY (EmailAdministrador) REFERENCES
	ADMINISTRADOR(Email)
);

CREATE TABLE AVALIACAO_CURSO_PROFESSOR(
	MatAluno VARCHAR(15),
	MatProfessor VARCHAR(15),
	CodAvaliacao int,
	PRIMARY KEY(MatAluno, MatProfessor, CodAvaliacao),
	FOREIGN KEY (MatAluno) REFERENCES ALUNO(Matricula),
	FOREIGN KEY (MatProfessor) REFERENCES PROFESSOR(Matricula),
	FOREIGN KEY (CodAvaliacao) REFERENCES AVALIACAO(Codigo)
);

CREATE TABLE CRITERIO_AVALIACAO(
	CodAvaliacao int,
	CodCriterio int,
	PRIMARY KEY(CodAvaliacao, CodCriterio),
	FOREIGN KEY (CodAvaliacao) REFERENCES AVALIACAO(Codigo),
	FOREIGN KEY (CodCriterio) REFERENCES CRITERIO(Codigo)

);

CREATE TABLE PROFESSOR_CURSO(
	CodCurso int,
	MatProfessor VARCHAR(15),
	PRIMARY KEY(CodCurso,MatProfessor),
	FOREIGN KEY (CodCurso) REFERENCES CURSO(Codigo),
	FOREIGN KEY (MatProfessor) REFERENCES PROFESSOR(Matricula)
);