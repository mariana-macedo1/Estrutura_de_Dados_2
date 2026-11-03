from Aluno import No

class TabelaHash:
    def __init__(self):
        self.M = 11 # Mod = 11
        self.tabela = [None] * self.M

    def funcao_hash(self, chave):
        return chave % self.M

    def buscar(self, matricula):
        indice = self.funcao_hash(matricula)
        atual = self.tabela[indice]
        while atual is not None:
            if atual.aluno.matricula == matricula:
                return atual.aluno
            atual = atual.proximo
        return None

    def inserir(self, aluno):
        if self.buscar(aluno.matricula) is not None:
            print("Erro: matrícula já cadastrada")
            return False

        indice = self.funcao_hash(aluno.matricula)
        novo_no = No(aluno)

        if self.tabela[indice] is None:
            self.tabela[indice] = novo_no
        else:
            atual = self.tabela[indice]
            while atual.proximo is not None:
                atual = atual.proximo
            atual.proximo = novo_no
        return True
    
    def remover(self, matricula):
        indice = self.funcao_hash(matricula)
        atual = self.tabela[indice]
        anterior = None
        while atual is not None:
            if atual.aluno.matricula == matricula:
                if anterior is None:
                    self.tabela[indice] = atual.proximo
                else:
                    anterior.proximo = atual.proximo
                print(f"Aluno com matrícula {matricula} removido com sucesso!")
                return True
            anterior = atual
            atual = atual.proximo
        print(f"Erro: Aluno com matrícula {matricula} não encontrado!")
        return False
    