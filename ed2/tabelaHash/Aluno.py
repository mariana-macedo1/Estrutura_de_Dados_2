class Aluno:
    def __init__(self, matricula: int, nome: str, idade: int):
        self.matricula = matricula
        self.nome = nome
        self.idade = idade

    def __str__(self):
        return f"Matricula: " + self.matricula + "Nome: " + self.nome + "Idade: " + self.idade;

class No:
    def __init__(self, aluno: Aluno):
        self.aluno = aluno
        self.proximo = None