public class Comparador {

    public static void main(String[] args) {
        int[] vetor1 = {12, 18, 9, 25, 17, 31, 22, 27, 16, 13, 19, 23, 20, 30, 14, 11, 15, 24, 26, 28};
        int[] vetor2 = {5, 7, 9, 10, 12, 14, 15, 17, 19, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32};
        int[] vetor3 = {99, 85, 73, 60, 50, 40, 35, 30, 25, 20, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6};

        testarTodos("Vetor 1 (Desordenado)", vetor1);
        testarTodos("Vetor 2 (Ordenado)", vetor2);
        testarTodos("Vetor 3 (Decrescente)", vetor3);
    }

    static void testarTodos(String nome, int[] vetorOriginal) {
        System.out.println("\n====================== " + nome + " ======================");

        testarAlgoritmo("Comb Sort", vetorOriginal, new CombSort());
        testarAlgoritmo("Gnome Sort", vetorOriginal, new GnomeSort());
        testarAlgoritmo("Bucket Sort", vetorOriginal, new BucketSort());
        testarAlgoritmo("Bubble Sort (flag)", vetorOriginal, new BubbleSortFlag());
        testarAlgoritmo("Selection Sort", vetorOriginal, new SelectionSort());
        testarAlgoritmo("Cocktail Sort", vetorOriginal, new CocktailSort());
    }

    static void testarAlgoritmo(String nome, int[] vetorOriginal, Ordenador algoritmo) {
        int tamanho = 0;
        for (int v : vetorOriginal) tamanho++;

        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = vetorOriginal[i];
        }

        algoritmo.limparContadores();
        algoritmo.ordenar(vetor);

        System.out.println(nome + " -> Trocas: " + algoritmo.getTrocas() + " | Iterações: " + algoritmo.getIteracoes());
    }
}

interface Ordenador {
    void ordenar(int[] vetor);
    int getTrocas();
    int getIteracoes();
    void limparContadores();
}

/* ======================================================
   COMB SORT
====================================================== */
class CombSort implements Ordenador {
    private int trocas = 0;
    private int iteracoes = 0;

    public void ordenar(int[] vetor) {
        int tamanho = 0;
        for (int v : vetor) tamanho++;
        int gap = tamanho;
        boolean troca = true;
        while (gap > 1 || troca) {
            if (gap > 1) gap = (gap * 10) / 13;
            troca = false;
            for (int i = 0; i + gap < tamanho; i++) {
                iteracoes++;
                if (vetor[i] > vetor[i + gap]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i + gap];
                    vetor[i + gap] = temp;
                    trocas++;
                    troca = true;
                }
            }
        }
    }

    public int getTrocas() { return trocas; }
    public int getIteracoes() { return iteracoes; }
    public void limparContadores() { trocas = 0; iteracoes = 0; }
}

/* ======================================================
   GNOME SORT
====================================================== */
class GnomeSort implements Ordenador {
    private int trocas = 0;
    private int iteracoes = 0;

    public void ordenar(int[] vetor) {
        int tamanho = 0;
        for (int v : vetor) tamanho++;
        int i = 0;
        while (i < tamanho) {
            iteracoes++;
            if (i == 0 || vetor[i] >= vetor[i - 1]) {
                i++;
            } else {
                int temp = vetor[i];
                vetor[i] = vetor[i - 1];
                vetor[i - 1] = temp;
                trocas++;
                i--;
            }
        }
    }

    public int getTrocas() { return trocas; }
    public int getIteracoes() { return iteracoes; }
    public void limparContadores() { trocas = 0; iteracoes = 0; }
}

/* ======================================================
   BUCKET SORT
====================================================== */
class BucketSort implements Ordenador {
    private int trocas = 0;
    private int iteracoes = 0;

    public void ordenar(int[] vetor) {
        int tamanho = 0;
        for (int v : vetor) tamanho++;
        if (tamanho <= 0) return;

        int max = vetor[0];
        int min = vetor[0];
        for (int i = 1; i < tamanho; i++) {
            iteracoes++;
            if (vetor[i] > max) max = vetor[i];
            if (vetor[i] < min) min = vetor[i];
        }

        int bucketCount = (max - min) / 5 + 1;
        int[][] buckets = new int[bucketCount][tamanho];
        int[] count = new int[bucketCount];

        for (int i = 0; i < tamanho; i++) {
            iteracoes++;
            int index = (vetor[i] - min) / 5;
            buckets[index][count[index]++] = vetor[i];
        }

        int pos = 0;
        for (int b = 0; b < bucketCount; b++) {
            for (int i = 1; i < count[b]; i++) {
                int chave = buckets[b][i];
                int j = i - 1;
                while (j >= 0 && buckets[b][j] > chave) {
                    iteracoes++;
                    buckets[b][j + 1] = buckets[b][j];
                    trocas++;
                    j--;
                }
                buckets[b][j + 1] = chave;
            }
            for (int i = 0; i < count[b]; i++) {
                vetor[pos++] = buckets[b][i];
            }
        }
    }

    public int getTrocas() { return trocas; }
    public int getIteracoes() { return iteracoes; }
    public void limparContadores() { trocas = 0; iteracoes = 0; }
}

/* ======================================================
   BUBBLE SORT (FLAG)
====================================================== */
class BubbleSortFlag implements Ordenador {
    private int trocas = 0;
    private int iteracoes = 0;

    public void ordenar(int[] vetor) {
        int tamanho = 0;
        for (int v : vetor) tamanho++;
        boolean trocou = true;
        while (trocou) {
            trocou = false;
            for (int i = 0; i < tamanho - 1; i++) {
                iteracoes++;
                if (vetor[i] > vetor[i + 1]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = temp;
                    trocas++;
                    trocou = true;
                }
            }
        }
    }

    public int getTrocas() { return trocas; }
    public int getIteracoes() { return iteracoes; }
    public void limparContadores() { trocas = 0; iteracoes = 0; }
}

/* ======================================================
   SELECTION SORT
====================================================== */
class SelectionSort implements Ordenador {
    private int trocas = 0;
    private int iteracoes = 0;

    public void ordenar(int[] vetor) {
        int tamanho = 0;
        for (int v : vetor) tamanho++;
        for (int i = 0; i < tamanho - 1; i++) {
            int min = i;
            for (int j = i + 1; j < tamanho; j++) {
                iteracoes++;
                if (vetor[j] < vetor[min]) min = j;
            }
            if (min != i) {
                int temp = vetor[i];
                vetor[i] = vetor[min];
                vetor[min] = temp;
                trocas++;
            }
        }
    }

    public int getTrocas() { return trocas; }
    public int getIteracoes() { return iteracoes; }
    public void limparContadores() { trocas = 0; iteracoes = 0; }
}

/* ======================================================
   COCKTAIL SORT
====================================================== */
class CocktailSort implements Ordenador {
    private int trocas = 0;
    private int iteracoes = 0;

    public void ordenar(int[] vetor) {
        int tamanho = 0;
        for (int v : vetor) tamanho++;
        boolean trocou = true;
        int inicio = 0;
        int fim = tamanho - 1;

        while (trocou) {
            trocou = false;
            for (int i = inicio; i < fim; i++) {
                iteracoes++;
                if (vetor[i] > vetor[i + 1]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = temp;
                    trocas++;
                    trocou = true;
                }
            }
            if (!trocou) break;
            trocou = false;
            fim--;
            for (int i = fim - 1; i >= inicio; i--) {
                iteracoes++;
                if (vetor[i] > vetor[i + 1]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = temp;
                    trocas++;
                    trocou = true;
                }
            }
            inicio++;
        }
    }

    public int getTrocas() { return trocas; }
    public int getIteracoes() { return iteracoes; }
    public void limparContadores() { trocas = 0; iteracoes = 0; }
}
