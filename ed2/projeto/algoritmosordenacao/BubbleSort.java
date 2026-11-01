package ed2.projeto.algoritmosordenacao;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] vetorOriginal = carregarArquivo("dados.txt");

        if (vetorOriginal == null || vetorOriginal.length == 0) {
            System.out.println("Nenhum dado carregado.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - BubbleSort");
            System.out.println("2 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            if (opcao == 1) {
                IAlgoritmoOrdenacao algoritmo = new BubbleSort();
                int[] vetor = vetorOriginal.clone();

                System.out.println("\n[ENTRADA]:");
                imprimirVetor(vetor);

                long inicio = System.currentTimeMillis();
                algoritmo.ordenarVetor(vetor);
                long fim = System.currentTimeMillis();

                System.out.println("\n[SAÍDA]:");
                imprimirVetor(vetor);

                System.out.println("Tempo: " + (fim - inicio) + " ms");
            }

        } while (opcao != 2);

        scanner.close();
    }

    public static int[] carregarArquivo(String caminho) {
        try {
            Scanner arquivoScanner = new Scanner(new File(caminho));
            ArrayList<Integer> lista = new ArrayList<>();

            while (arquivoScanner.hasNextInt()) {
                lista.add(arquivoScanner.nextInt());
            }
            arquivoScanner.close();

            int[] vetor = new int[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                vetor[i] = lista.get(i);
            }
            return vetor;

        } catch (Exception e) {
            return null;
        }
    }

    public static void imprimirVetor(int[] vetor) {
        int limite = Math.min(vetor.length, 100);
        for (int i = 0; i < limite; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }
}