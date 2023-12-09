package Árvore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class App {
    public static void escreverDadosEmArquivo(int[] vetor, String nomeArquivo) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

            for (int valor : vetor) {
                writer.write(String.valueOf(valor));
                writer.newLine();  // quebra de linha
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long tempoInicioTotal = System.nanoTime();

        // Ler o arquivo e criar as árvores AVL e Rubro-Negra
        int[] vetor = Arquivo.lerArquivo("C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\Dados\\dados100_mil.txt");
        Arvore arvoreAVL = new Arvore(vetor, "AVL");
        ArvoreRubroNegra arvoreRN = new ArvoreRubroNegra();

        // Inserir os valores do arquivo na árvore Rubro-Negra
        long tempoInicioRN = System.nanoTime();
        for (int valor : vetor) {
            arvoreRN.inserirPublico(valor);
        }
        long tempoFimRN = System.nanoTime();
        double tempoInsercaoRN = (tempoFimRN - tempoInicioRN) / 1_000_000_000.0;
        System.out.println("Tempo de preenchimento da árvore Rubro-Negra: " + tempoInsercaoRN + " segundos");
        escreverDadosEmArquivo(vetor, "C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\Dados\\rubronegro.txt");

        // Inserir os valores do arquivo na árvore AVL
        long tempoInicioAVL = System.nanoTime();
        for (int valor : vetor) {
            arvoreAVL.inserirPublico(valor);
        }
        long tempoFimAVL = System.nanoTime();
        double tempoInsercaoAVL = (tempoFimAVL - tempoInicioAVL) / 1_000_000_000.0;
        System.out.println("Tempo de preenchimento da árvore AVL: " + tempoInsercaoAVL + " segundos");
        escreverDadosEmArquivo(vetor, "C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\Dados\\avl.txt");

        // Gerar o arquivo de ordenação da árvore AVL
        arvoreAVL.gerarArquivoOrdenacao("Ordenação_AVL");

        // Sortear 50.000 números e realizar operações nas árvores
        Random random = new Random();
        int countNotMultiple = 0;
        for (int i = 0; i < 50000; i++) {
            int num = random.nextInt(19999) - 9999;

            if (num % 3 == 0) {
                arvoreAVL.inserirPublico(num);
                arvoreRN.inserirPublico(num);
            } else if (num % 5 == 0) {
                if (arvoreAVL.contarPublico(num) > 0) {
                    arvoreAVL.removerPublico(num);
                }
                if (arvoreRN.contarPublico(num) > 0) {
                    arvoreRN.removerPublico(num);
                }
            } else {
                countNotMultiple += arvoreAVL.contarPublico(num);
                countNotMultiple += arvoreRN.contarPublico(num);
            }
        }

        System.out.println("Quantidade de números que não são múltiplos de 3 ou 5: " + countNotMultiple);

        long tempoFimTotal = System.nanoTime();
        double tempoTotal = (tempoFimTotal - tempoInicioTotal) / 1_000_000_000.0;
        System.out.println("Tempo total de execução: " + tempoTotal + " segundos");
    }
}