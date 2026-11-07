package ed2.projeto.algoritmosordenacao;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    private static final String ARQUIVO_PADRAO = "entrada.txt";

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int[] vetorCarregado = carregarVetorPadrao();

        while (true) {

            imprimirMenu();

            int opcao = lerInteiro(entrada);

            if (opcao == 0) {
                System.out.println("encerrando sistema");
                break;
            }

            if (opcao >= 1 && opcao <= 5) {

                IAlgoritmoOrdenacao algoritmo = criarAlgoritmo(opcao);
                Supplier<int[]> gerador = criarGerador(vetorCarregado, entrada);

                executar(algoritmo, gerador);

            } else if (opcao == 6) {

                int[] novoVetor = lerArquivo(entrada);

                if (novoVetor != null) {
                    vetorCarregado = novoVetor;
                }

            } else if (opcao == 7) {

                Simulacao.executar(entrada);

            } else {

                System.out.println("opcao invalida!");

            }

        }

        entrada.close();
    }

    private static void imprimirMenu() {

        System.out.println("\nALGORITMOS DE ORDENACAO");
        System.out.println("==================================");
        System.out.println("1 - Bubble Sort");
        System.out.println("2 - Insertion Sort");
        System.out.println("3 - Merge Sort");
        System.out.println("4 - Quick Sort");
        System.out.println("5 - Selection Sort");
        System.out.println("6 - Carregar vetor de arquivo .txt");
        System.out.println("7 - Simular tempos (n, n^2, n^3, n^10)");
        System.out.println("0 - Encerrar Sistema");
        System.out.println("==================================");
        System.out.print("Escolha a opcao: ");

    }

    private static int[] carregarVetorPadrao() {

        File arquivo = new File(ARQUIVO_PADRAO);

        if (!arquivo.exists()) {
            return null;
        }

        try {

            int[] vetor = Utils.lerVetorArquivo(ARQUIVO_PADRAO);

            System.out.println("Vetor carregado automaticamente de " + ARQUIVO_PADRAO + ":");
            Utils.imprimirVetor(vetor);

            return vetor;

        } catch (IOException e) {

            System.out.println("nao foi possivel ler " + ARQUIVO_PADRAO + ": " + e.getMessage());
            return null;

        }

    }

    private static int[] lerArquivo(Scanner entrada) {

        System.out.print("Informe o caminho do arquivo .txt: ");
        String caminho = entrada.next();

        try {

            int[] vetor = Utils.lerVetorArquivo(caminho);

            System.out.println("\nVetor carregado com sucesso (" + vetor.length + " elementos):");
            Utils.imprimirVetor(vetor);

            return vetor;

        } catch (IOException e) {

            System.out.println("erro ao ler arquivo: " + e.getMessage());
            return null;

        }

    }

    private static Supplier<int[]> criarGerador(int[] vetorCarregado, Scanner entrada) {

        if (vetorCarregado != null && vetorCarregado.length > 0) {

            System.out.println("usando vetor carregado (" + vetorCarregado.length + " elementos)");
            return () -> vetorCarregado.clone();

        }

        int tamanho = lerPositivo("Informe o tamanho do vetor: ", entrada);
        int limite = lerPositivo("Informe o limite superior dos valores: ", entrada);

        return () -> Utils.gerarVetor(tamanho, limite);

    }

    private static void executar(IAlgoritmoOrdenacao algoritmo, Supplier<int[]> gerador) {

        System.out.println("\n" + algoritmo);

        int[] vetor = gerador.get();

        System.out.println("\nVetor original:");
        Utils.imprimirVetor(vetor);

        long inicio = System.nanoTime();
        algoritmo.ordenarVetor(vetor);
        long fim = System.nanoTime();

        System.out.println("\nVetor ordenado:");
        Utils.imprimirVetor(vetor);

        System.out.printf("%nTempo de ordenacao: %.4f ms%n", (fim - inicio) / 1000000.0);
    }

    private static IAlgoritmoOrdenacao criarAlgoritmo(int opcao) {

        switch (opcao) {
            case 1:
                return new BubbleSort();
            case 2:
                return new InsertionSort();
            case 3:
                return new MergeSort();
            case 4:
                return new QuickSort();
            case 5:
                return new SelectionSort();
            default:
                return null;
        }

    }

    private static int lerPositivo(String mensagem, Scanner entrada) {

        int valor;

        while (true) {

            System.out.print(mensagem);
            valor = lerInteiro(entrada);

            if (valor > 0) {
                return valor;
            }

            System.out.println("o valor deve ser maior que zero!");

        }

    }

    private static int lerInteiro(Scanner entrada) {

        while (!entrada.hasNextInt()) {
            entrada.next();
            System.out.print("digite um numero inteiro: ");
        }

        return entrada.nextInt();

    }

}