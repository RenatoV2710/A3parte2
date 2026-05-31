import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Troque o nome do arquivo conforme o teste
        String nomeArquivo = "dados_pior_1M.csv";

        try {

            int[] dados = carregarDados(nomeArquivo);

            // Cópias dos arrays
            int[] arrayParaSimples = Arrays.copyOf(dados, dados.length);
            int[] arrayParaEficiente = Arrays.copyOf(dados, dados.length);

            // =========================
            // BUBBLE SORT
            // =========================
            System.out.println("--- Bubble Sort ---");

            exibirPrimeiros15(arrayParaSimples);

            long inicioSimples = System.currentTimeMillis();

            bubbleSort(arrayParaSimples);

            long fimSimples = System.currentTimeMillis();

            exibirPrimeiros15(arrayParaSimples);

            System.out.println("Tempo: "
                    + (fimSimples - inicioSimples) + " ms\n");

            // =========================
            // QUICK SORT
            // =========================
            System.out.println("--- Quick Sort ---");

            exibirPrimeiros15(arrayParaEficiente);

            long inicioEficiente = System.currentTimeMillis();

            quickSort(arrayParaEficiente, 0,
                    arrayParaEficiente.length - 1);

            long fimEficiente = System.currentTimeMillis();

            exibirPrimeiros15(arrayParaEficiente);

            System.out.println("Tempo: "
                    + (fimEficiente - inicioEficiente) + " ms");

        } catch (FileNotFoundException e) {

            System.out.println("Arquivo não encontrado.");

        }
    }

    // =========================
    // BUBBLE SORT
    // =========================
    public static void bubbleSort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (array[j] > array[j + 1]) {

                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                }
            }
        }
    }

    // =========================
    // QUICK SORT
    // =========================
    public static void quickSort(int[] array, int inicio, int fim) {

        if (inicio < fim) {

            int pivo = particionar(array, inicio, fim);

            quickSort(array, inicio, pivo - 1);

            quickSort(array, pivo + 1, fim);
        }
    }

    public static int particionar(int[] array, int inicio, int fim) {

        int pivo = array[fim];

        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {

            if (array[j] < pivo) {

                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;

        return i + 1;
    }

    // =========================
    // LEITURA DO ARQUIVO
    // =========================
    private static int[] carregarDados(String nomeArquivo)
            throws FileNotFoundException {

        File file = new File(nomeArquivo);

        Scanner scanner = new Scanner(file);

        // Pula cabeçalho
        if (scanner.hasNextLine())
            scanner.nextLine();

        int tamanho = nomeArquivo.contains("1M")
                ? 1000000
                : 1000;

        int[] array = new int[tamanho];

        int i = 0;

        while (scanner.hasNextLine() && i < tamanho) {

            array[i] =
                    Integer.parseInt(scanner.nextLine().trim());

            i++;
        }

        scanner.close();

        return array;
    }

    // =========================
    // MOSTRAR 15 PRIMEIROS
    // =========================
    private static void exibirPrimeiros15(int[] array) {

        System.out.print("Dados: ");

        int limite = Math.min(array.length, 15);

        for (int i = 0; i < limite; i++) {

            System.out.print(array[i] + " ");
        }

        System.out.println("...");
    }
}