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