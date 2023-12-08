package Árvore;

public class App {
    public static void main(String[] args) {
        long tempoInicioTotal = System.nanoTime();

        // Ler o arquivo e criar as árvores AVL e Rubro-Negra
        int[] vetor = Arquivo.lerArquivo("C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\Dados\\dados100_mil.txt");
        Arvore arvoreAVL = new Arvore(vetor.clone(), "Ordenação_AVL");
        ArvoreRubroNegra arvoreRN = new ArvoreRubroNegra();

        // Inserir os valores do arquivo na árvore Rubro-Negra
        long tempoInicioRN = System.nanoTime();
        for (int value : vetor) {
            arvoreRN.inserir(value);
            System.out.println("Inserindo " + value + " na árvore Rubro-Negra");
        }
        long tempoFimRN = System.nanoTime();
        double tempoInsercaoRN = (tempoFimRN - tempoInicioRN) / 1_000_000_000.0;
        System.out.println("Tempo de preenchimento da árvore Rubro-Negra: " + tempoInsercaoRN + " segundos");

        // Inserir os valores do arquivo na árvore AVL
        long tempoInicioAVL = System.nanoTime();
        for (int value : vetor) {
            arvoreAVL.inserir(arvoreAVL, value);
            System.out.println("Inserindo " + value + " na árvore AVL");
        }
        long tempoFimAVL = System.nanoTime();
        double tempoInsercaoAVL = (tempoFimAVL - tempoInicioAVL) / 1_000_000_000.0;
        System.out.println("Tempo de preenchimento da árvore AVL: " + tempoInsercaoAVL + " segundos");

        arvoreAVL.gerarArquivoOrdenacao("Ordenação_AVL");


        // Sortear 50.000 números e realizar operações na árvore
        int[] randomNumbers = new int[50000];
        for (int i = 0; i < 50000; i++) {
            int random = (int) (Math.random() * 19999) - 9999;
        }

        long tempoFimTotal = System.nanoTime();
        double tempoTotal = (tempoFimTotal - tempoInicioTotal) / 1_000_000_000.0;
        System.out.println("Tempo total de execução: " + tempoTotal + " segundos");
    }
}