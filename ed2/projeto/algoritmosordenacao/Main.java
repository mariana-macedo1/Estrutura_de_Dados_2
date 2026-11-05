
package ed2.projeto.algoritmosordenacao;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int opcao;

        while (true) {

            System.out.println("\nALGORITMOS DE ORDENACAO");
            System.out.println("==================================");
            System.out.println("1 - Bubble Sort");
            System.out.println("2 - Insertion Sort");
            System.out.println("3 - Merge Sort");
            System.out.println("4 - Quick Sort");
            System.out.println("5 - Selection Sort");
            System.out.println("0 - Encerrar Sistema");
            System.out.println("==================================");
            System.out.print("Escolha o algoritmo: ");

            opcao = entrada.nextInt();

            if (opcao == 0) {
                System.out.println("encerrando sistema");
                break;
            }
            if (opcao < 0 || opcao > 5) {
                System.out.println("opcao invalida!");
            }
            if (opcao == 1) {
                System.out.println("Bubble Sort");
            }
            if (opcao == 2) {
                System.out.println("Insertion Sort");
            }
            if (opcao == 3) {
                System.out.println("Merge Sort");
            }
            if (opcao == 4) {
                System.out.println("Quick Sort");
            }
            if (opcao == 5) {
                System.out.println("Selection Sort");
            }
        }

        entrada.close();
    }
}
