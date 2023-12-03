package Árvore;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Arquivo {
    // Método para ler um arquivo de texto e retornar um array de inteiros
    public static int[] lerArquivo(String url) {
        // Cria um objeto Path com o caminho do arquivo
        Path caminho = Paths.get(url);

        String leitura = "";

        try {
            // Lê todos os bytes do arquivo e armazena em um array de bytes
            byte[] texto = Files.readAllBytes(caminho);
            // Converte o array de bytes em uma string
            leitura = new String(texto);
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo!");
            return null;
        }

        // Cria um ArrayList para armazenar os números do arquivo
        ArrayList<Integer> protoVetor = new ArrayList<>();

        String num = "";

        // Percorre a string lida do arquivo
        for (int i = 0; i < leitura.length(); i++) {
            // Se o caractere atual não for um dos delimitadores
            if (leitura.charAt(i) != '[' && leitura.charAt(i) != ']' && leitura.charAt(i) != ',' && leitura.charAt(i) != ' ') {
                // Adiciona o caractere ao número atual
                num += leitura.charAt(i);
            } else {
                // Se o número atual não for vazio
                if (num != "") {
                    // Converte o número atual para int e adiciona ao ArrayList
                    int numero = Integer.parseInt(num);
                    protoVetor.add(numero);
                    // Limpa o número atual
                    num = "";
                }
            }
        }

        // Cria um array de inteiros com o mesmo tamanho do ArrayList
        int[] vetor = new int[protoVetor.size()];

        // Copia os números do ArrayList para o array de inteiros
        for (int i = 0; i < protoVetor.size(); i++) {
            vetor[i] = protoVetor.get(i);
        }

        // Retorna o array de inteiros
        return vetor;
    }

    // Método para gravar um array de inteiros em um arquivo de texto
    public static void gravarArquivo(String url, int[] conteudo) {
        // Cria um objeto Path com o caminho do arquivo
        Path caminho = Paths.get(url);

        // Cria um cabeçalho com o nome do aluno e o nome do algoritmo
        String cabecalho = "Nome do Aluno: Arthur\n Ordenação Árvore AVLn\n";

        // Converte o array de inteiros para uma string
        String vetor = cabecalho + Arrays.toString(conteudo);

        // Converte a string para um array de bytes
        byte[] textoEmBytes = vetor.getBytes();

        try {
            // Grava o array de bytes no arquivo
            Files.write(caminho, textoEmBytes);
        } catch (Exception e) {
            System.out.println("Erro!!");
        }
    }

    // Método para gravar uma string em um arquivo de texto
    public static void gravarArquivo(String url, String conteudo, String nomeAlg, long tempoExe, long qtdComp, long qtdMov) {
        // Cria um objeto Path com o caminho do arquivo
        Path caminho = Paths.get(url);
        // Converte a string para um array de bytes
        byte[] textoEmBytes = conteudo.getBytes();

        try {
            // Grava o array de bytes no arquivo
            Files.write(caminho, textoEmBytes);
        } catch (Exception e) {
            System.out.println("Erro!!");
        }
    }

    // Método para gravar um array de inteiros em um arquivo de texto com cabeçalho personalizado
    public static void gravarArquivo(String url, int[] conteudo, String nomeAlg, long tempoExe, long qtdComp, long qtdMov) {
        // Cria um objeto Path com o caminho do arquivo
        Path caminho = Paths.get(url);

        // Converte o tempo de execução de milissegundos para o formato HH:MM:SS:MMM
        int miliSeg = (int) (tempoExe % 1000);
        int seg = (int) (tempoExe / 1000) % 60;
        int min = (int) (tempoExe / (60 * 1000)) % 60;
        int hr = (int) (tempoExe / (60 * 60 * 1000));
        String tempo = String.format("%02d:%02d:%02d:%03d", hr, min, seg, miliSeg);

        // Cria um cabeçalho com o nome do aluno, o nome do algoritmo, o tempo de execução, a quantidade de comparações e a quantidade de movimentos
        String cabecalho = "Nome do Aluno: Arthur Paganoti" + "\nNome do algoritmo: Ávore AVL " + nomeAlg + "\nTempo de execução: " + tempo + "\nQuantidade de Comparações: " + qtdComp + "\nQuantidade de Movimentos: " + qtdMov + "\n\n";

        // Converte o array de inteiros para uma string
        String vetor = cabecalho + Arrays.toString(conteudo);

        // Converte a string para um array de bytes
        byte[] textoEmBytes = vetor.getBytes();

        try {
            // Grava o array de bytes no arquivo
            Files.write(caminho, textoEmBytes);
        } catch (Exception e) {
            System.out.println("Erro!!");
        }
    }
}