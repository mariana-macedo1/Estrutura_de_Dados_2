package ed2.projeto.algoritmosordenacao;

import java.util.Scanner;

public class Simulacao {

  private static final int REPETICOES = 3;
  private static final int LIMITE_VALORES = 1_000_000;

  private Simulacao() {

  }

  public static void executar(Scanner entrada) {

    int base = lerBase(entrada);

    long tamanhoN = base;
    long tamanhoN2 = (long) base * base;
    long tamanhoN3 = (long) base * base * base;
    double tamanhoN10 = Math.pow(base, 10);

    long[] tamanhos = { tamanhoN, tamanhoN2, tamanhoN3 };

    System.out.println("\nSIMULACAO DE TEMPOS");
    System.out.println("==============================================================");
    System.out.println("Tamanhos reais: n=" + tamanhoN
        + ", n^2=" + tamanhoN2
        + ", n^3=" + tamanhoN3);
    System.out.println("Tamanho estimado: n^10=" + formatarNumero(tamanhoN10));
    System.out.println("(n^10 nao e executavel; tempo estimado pela complexidade)");
    System.out.println("==============================================================");

    IAlgoritmoOrdenacao[] algoritmos = {
        new BubbleSort(),
        new InsertionSort(),
        new SelectionSort(),
        new MergeSort(),
        new QuickSort()
    };

    imprimirCabecalho();

    for (IAlgoritmoOrdenacao algoritmo : algoritmos) {

      double[] tempos = new double[tamanhos.length];

      for (int i = 0; i < tamanhos.length; i++) {
        tempos[i] = medirTempo(algoritmo, (int) tamanhos[i]);
      }

      double tempoEstimado = estimarTempo(
          algoritmo,
          tempos[tempos.length - 1],
          tamanhos[tamanhos.length - 1],
          tamanhoN10);

      imprimirLinha(algoritmo.toString(), tempos, tempoEstimado);

    }

    System.out.println("==============================================================");
    System.out.println("Obs.: QuickSort usa o caso medio O(n log n); pior caso O(n^2).");

  }

  private static int lerBase(Scanner entrada) {

    int base;

    while (true) {

      System.out.print("Informe a base n para a simulacao (recomendado 10): ");

      if (entrada.hasNextInt()) {

        base = entrada.nextInt();

        if (base >= 2 && base <= 20) {
          return base;
        }

      } else {

        entrada.next();

      }

      System.out.println("informe um inteiro entre 2 e 20!");

    }

  }

  private static double medirTempo(IAlgoritmoOrdenacao algoritmo, int tamanho) {

    double menorTempo = Double.MAX_VALUE;

    for (int repeticao = 0; repeticao < REPETICOES; repeticao++) {

      int[] vetor = Utils.gerarVetor(tamanho, LIMITE_VALORES);

      long inicio = System.nanoTime();
      algoritmo.ordenarVetor(vetor);
      long fim = System.nanoTime();

      double tempoMilissegundos = (fim - inicio) / 1_000_000.0;

      if (tempoMilissegundos < menorTempo) {
        menorTempo = tempoMilissegundos;
      }

    }

    return menorTempo;

  }

  private static double estimarTempo(IAlgoritmoOrdenacao algoritmo,
                                     double tempoMedido,
                                     double tamanhoMedido,
                                     double tamanhoAlvo) {

    if (tempoMedido <= 0) {
      tempoMedido = 0.000001;
    }

    if (ehQuadratico(algoritmo)) {

      double fator = tamanhoAlvo / tamanhoMedido;
      return tempoMedido * fator * fator;

    }

    double trabalhoMedido = tamanhoMedido * (Math.log(tamanhoMedido) / Math.log(2));
    double trabalhoAlvo = tamanhoAlvo * (Math.log(tamanhoAlvo) / Math.log(2));

    return tempoMedido * (trabalhoAlvo / trabalhoMedido);

  }

  private static boolean ehQuadratico(IAlgoritmoOrdenacao algoritmo) {

    return algoritmo instanceof BubbleSort
        || algoritmo instanceof InsertionSort
        || algoritmo instanceof SelectionSort;

  }

  private static void imprimirCabecalho() {

    System.out.printf("%-15s | %-14s | %-14s | %-14s | %-20s%n",
        "Algoritmo", "n (real)", "n^2 (real)", "n^3 (real)", "n^10 (estimado)");
    System.out.println("--------------------------------------------------------------------------------------------");

  }

  private static void imprimirLinha(String nome, double[] tempos, double estimado) {

    System.out.printf("%-15s | %-14s | %-14s | %-14s | %-20s%n",
        nome,
        formatarTempo(tempos[0]),
        formatarTempo(tempos[1]),
        formatarTempo(tempos[2]),
        formatarTempo(estimado));

  }

  private static String formatarTempo(double milissegundos) {

    if (milissegundos < 1000) {
      return String.format("%.4f ms", milissegundos);
    }

    double segundos = milissegundos / 1000.0;

    if (segundos < 60) {
      return String.format("%.4f s", segundos);
    }

    double minutos = segundos / 60.0;

    if (minutos < 60) {
      return String.format("%.4f min", minutos);
    }

    double horas = minutos / 60.0;

    if (horas < 24) {
      return String.format("%.4f h", horas);
    }

    double dias = horas / 24.0;

    if (dias < 365) {
      return String.format("%.4f dias", dias);
    }

    return String.format("%.4f anos", dias / 365.0);

  }

  private static String formatarNumero(double valor) {
    return String.format("%.0f", valor);
  }

}
