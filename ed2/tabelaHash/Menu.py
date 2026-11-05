from Tabela_hash import TabelaHash
from Aluno import Aluno

def menu():

    Tabela = TabelaHash()

    while True:
        try:

            print("======= Menu =======")
            print("1. Inserir novo Aluno")
            print("2. Buscar Aluno")
            print("3. Remover Aluno")
            print("4. Exibir Tabela Hash")
            print("5. Encerrar Sistema")

            opcao = input("Escolha uma opção: ")

            if opcao == "1":
                nome = input("Digite o nome do aluno:")
                matricula = int(input("Digite a matricula do aluno:"))
                idade = int(input("Digite a idade do aluno:"))

                aluno = Aluno(matricula, nome, idade)

                Tabela.inserir(aluno)

            elif opcao == "2":
                matricula = int(input("Digite a matricula do aluno:"))
                aluno = Tabela.buscar(matricula)
                
                if aluno:
                    print(f"Aluno encontrado: {aluno}")
                else:
                    print("Aluno não encontrado")

            elif opcao == "3":
                matricula = int(input("Digite a matricula do aluno:"))
                Tabela.remover(matricula)

            elif opcao == "4":
                Tabela.exibir()

            elif opcao == "5":
                print("encerrando")
                break
            else:
                print("opção inválida! tente novamente.")

        except ValueError:
            print("Erro: Digite valores numéricos válidos para matrícula e idade!")


if __name__ == "__main__":
    menu()
