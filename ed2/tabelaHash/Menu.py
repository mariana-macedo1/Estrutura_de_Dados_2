def menu():
    while True:
        print("Menu")
        print("1. Adicionar aluno")
        print("2. Buscar aluno")
        print("3. Remover aluno")
        print("4. Tabela Hash")
        print("5. Fechar")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            print("opcao 1")
        elif opcao == "2":
            print("opcao 2")
        elif opcao == "3":
            print("opcao 3")
        elif opcao == "4":
            print("opcao 4")
        elif opcao == "5":
            print("encerrando")
            break
        else:
            print("opção inválida! tente novamente.")


    if __name__ == "__main__":
        menu()