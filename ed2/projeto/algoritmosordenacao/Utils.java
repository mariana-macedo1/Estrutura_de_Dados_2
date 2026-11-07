package ed2.projeto.algoritmosordenacao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Hilario Tomaz Alves de Oliveira
 *
 */

public class Utils {

  private Utils() {

  }

  // Metodos

  public static final int LIMITE_IMPRESSAO = 100;

  public static void imprimirVetor(int[] vetor) {
    imprimirVetor(vetor, LIMITE_IMPRESSAO);
  }

  public static void imprimirVetor(int[] vetor, int limite) {
    int quantidade = Math.min(vetor.length, limite);

    System.out.print("  Vetor (" + vetor.length + " elementos) = {");

    for (int i = 0; i < quantidade; i++) {

      System.out.print(vetor[i]);

      if (i < (quantidade - 1)) {
        System.out.print(", ");
      }

    }

    System.out.print("}");

    if (vetor.length > limite) {
      System.out.print("  ... (exibindo os " + limite + " primeiros)");
    }

    System.out.println();
  }

  public static int[] gerarVetor(int tamanhoVetor, int limiteSuperior) {

    Random random = new Random();

    int[] vetor = new int[tamanhoVetor];

    for (int i = 0; i < tamanhoVetor; i++) {

      int numero = random.nextInt(limiteSuperior);

      vetor[i] = numero;

    } // FOR VETOR

    return vetor;

  }

  public static int[] lerVetorArquivo(String caminho) throws IOException {

    List<Integer> numeros = new ArrayList<>();

    try (Scanner arquivo = new Scanner(new File(caminho))) {

      arquivo.useDelimiter("[\\s,;]+");

      while (arquivo.hasNext()) {

        if (arquivo.hasNextInt()) {
          numeros.add(arquivo.nextInt());
        } else {
          arquivo.next();
        }

      }

    }

    int[] vetor = new int[numeros.size()];

    for (int i = 0; i < numeros.size(); i++) {
      vetor[i] = numeros.get(i);
    }

    return vetor;

  }

}
