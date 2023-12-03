package Árvore;

public class App {
    public static void main(String[] args) {
        // Ler o arquivo e criar as árvores AVL e Rubro-Negra
        int[] vetor = Arquivo.lerArquivo("C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\Dados\\dados100_mil.txt");
        Arvore arvoreAVL = new Arvore(vetor.clone(), "Ordenação_AVL");
        ArvoreRubroNegra arvoreRN = new ArvoreRubroNegra();

        // Inserir os valores do arquivo na árvore Rubro-Negra
        long tempoInicioRN = System.currentTimeMillis();
        for (int value : vetor) {
            arvoreRN.inserir(value);
        }
        long tempoFimRN = System.currentTimeMillis();
        long tempoInsercaoRN = tempoFimRN - tempoInicioRN;

        // Verificar se as árvores foram criadas com sucesso
        if (arvoreAVL != null && arvoreRN != null) {
            System.out.println("Árvores AVL e Rubro-Negra criadas com sucesso!");

            // Medir o tempo necessário para preencher as árvores AVL e Rubro-Negra
            long tempoInicioAVL = System.currentTimeMillis();
            for (int value : vetor) {
                arvoreAVL.inserir(arvoreAVL, value);
            }
            long tempoFimAVL = System.currentTimeMillis();
            long tempoInsercaoAVL = tempoFimAVL - tempoInicioAVL;

            // Sortear 50.000 números e realizar operações na árvore
            int[] randomNumbers = new int[50000];
            for (int i = 0; i < 50000; i++) {
                int random = (int) (Math.random() * 19999) - 9999;
                randomNumbers[i] = random;

                if (random % 3 == 0) {
                    arvoreAVL.inserir(arvoreAVL, random);
                    arvoreRN.inserir(random);
                } else if (random % 5 == 0) {
                    arvoreAVL.remover(arvoreAVL, random);
                    arvoreRN.remover(random);
                } else {
                    int countAVL = arvoreAVL.contar(arvoreAVL, random);
                    int countRN = arvoreRN.contar(random);
                    System.out.println("Número " + random + " aparece " + countAVL + " vezes na AVL e " + countRN + " vezes na Rubro-Negra");
                }
            }
        } else {
            System.out.println("Erro ao criar as árvores AVL e Rubro-Negra!");
        }
    }
}