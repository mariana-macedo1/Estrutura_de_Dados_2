from Tabela_hash import TabelaHash
from Aluno import Aluno

def menu():

    Tabela = TabelaHash()

    while True:
        try:

            print("======= Menu =======")
            print("1. Adicionar aluno")
            print("2. Buscar aluno")
            print("3. Remover aluno")
            print("4. Tabela Hash")
            print("5. Fechar")

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
