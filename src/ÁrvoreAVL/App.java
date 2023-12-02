package ÁrvoreAVL;

public class App {
    public static void main(String[] args) {
        // Ler o arquivo e criar a árvore AVL
        // O método lerArquivo da classe Arquivo é chamado com o caminho do arquivo como argumento.
        // O método retorna um array de inteiros que é clonado e passado para o construtor da classe Arvore.
        int[] vetor = Arquivo.lerArquivo("C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\Dados\\dados100_mil.txt");
        Arvore arvore = new Arvore(vetor.clone(), "Ordenação");

        // Verificar se a árvore foi criada com sucesso
        // Se a árvore não for nula, isso significa que a árvore foi criada com sucesso.
        if (arvore != null) {
            System.out.println("Árvore AVL criada com sucesso!");

            // Gerar um arquivo com a árvore em ordem crescente e o tempo de execução
            // O método gerarArquivoOrdenacao da classe Arvore é chamado com o caminho do arquivo como argumento.
            // O método gera um arquivo com a árvore AVL impressa em ordem crescente e o tempo de execução.
            arvore.gerarArquivoOrdenacao("C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\Dados\\ordenacao_avl.txt");
        } else {
            // Se a árvore for nula, isso significa que houve um erro ao criar a árvore.
            System.out.println("Erro ao criar a árvore AVL!");
        }
    }
}