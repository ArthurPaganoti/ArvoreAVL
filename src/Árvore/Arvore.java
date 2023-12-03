package Árvore;

import java.util.ArrayList;

public class Arvore {
    // Variáveis para armazenar a informação, altura, nós direito e esquerdo, valor, movimentos e comparações
    int info;
    int altura;
    Arvore direita = null;
    Arvore esquerda = null;
    int valor;
    private long mov;
    private long comp;

    // Construtor para criar um novo nó na árvore
    public Arvore(int valor) {
        this.info = 0;
        this.altura = 0;
        this.direita = null;
        this.esquerda = null;
        this.valor = valor;
        this.mov = 0;
        this.comp = 0;
    }

    // Construtor para criar uma árvore a partir de um array de inteiros
    public Arvore(int[] vetor, String nomeArquivo) {
        this.mov = 0;
        this.comp = 0;

        // Registra o tempo inicial
        long tempoIni = System.currentTimeMillis();

        // Insere cada valor do vetor na árvore
        for (int i = 0; i < vetor.length; i++) {
            inserir(this, vetor[i]);
        }

        // Registra o tempo final
        long tempoFim = System.currentTimeMillis();

        // Calcula o tempo total
        long tempoTotal = tempoFim - tempoIni;

        // Grava o vetor ordenado e o tempo de execução em um arquivo
        Arquivo.gravarArquivo("C:\\Users\\pagan\\OneDrive\\Área de Trabalho\\IF BCC\\Atividades Programação BCC\\ArvoreAVL\\src\\ResultadoExecução" + nomeArquivo + ".txt", vetor, "ÁrvoreAVL Ordenação", tempoTotal, this.comp, this.mov);
    }

    // Método para calcular a altura de um nó
    int alturaNo(Arvore no) {
        if (no == null) {
            return -1;
        }
        return no.altura;
    }

    // Método para calcular o fator de balanceamento de um nó
    int fatorBalanceamentoNo(Arvore no) {
        return alturaNo(no.esquerda) - alturaNo(no.direita);
    }

    // Método para calcular o maior valor entre dois inteiros
    int maior(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    // Método para realizar uma rotação à esquerda
    Arvore rotacaoLL(Arvore raiz) {
        if (raiz == null || raiz.esquerda == null) {
            return raiz;
        }

        Arvore no = raiz.esquerda;
        if (no == null) {
            return raiz;
        }

        raiz.esquerda = no.direita;
        if (no.direita != null) {
            no.direita = raiz;
        }

        raiz.altura = maior(alturaNo(raiz.esquerda), alturaNo(raiz.direita)) + 1;
        no.altura = maior(alturaNo(no.esquerda), raiz.altura) + 1;
        return no;
    }


    // Método para realizar uma rotação à direita
    Arvore rotacaoRR(Arvore raiz) {
        if (raiz == null || raiz.direita == null) {
            return raiz;
        }

        Arvore no = raiz.direita;
        if (no == null) {
            return raiz;
        }

        raiz.direita = no.esquerda;
        if (no.esquerda != null) {
            no.esquerda = raiz;
        }

        raiz.altura = maior(alturaNo(raiz.esquerda), alturaNo(raiz.direita)) + 1;
        no.altura = maior(alturaNo(no.direita), raiz.altura) + 1;
        return no;
    }


    // Método para realizar uma rotação à esquerda e depois à direita
    Arvore rotacaoLR(Arvore raiz) {
        raiz.direita = rotacaoLL(raiz.direita);
        return rotacaoRR(raiz);
    }

    // Método para balancear a árvore
    Arvore balancear(Arvore raiz, int valor) {

        int balance = fatorBalanceamentoNo(raiz);

        if (balance > 1 && valor < raiz.esquerda.valor) {
            return rotacaoLL(raiz);
        }

        if (balance < -1 && valor > raiz.direita.valor) {
            return rotacaoRR(raiz);
        }

        if (balance > 1 && valor > raiz.esquerda.valor) {
            raiz.esquerda = rotacaoRR(raiz.esquerda);
            return rotacaoLL(raiz);
        }

        if (balance < -1 && valor < raiz.direita.valor) {
            raiz.direita = rotacaoLL(raiz.direita);
            return rotacaoRR(raiz);
        }

        return raiz;
    }

    // Método para inserir um valor na árvore
    Arvore inserir(Arvore raiz, int valor) {

        if (raiz == null) {
            raiz = new Arvore(valor);
            raiz.valor = valor;
            raiz.altura = 0;
            raiz.esquerda = raiz.direita = null;
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = inserir(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = inserir(raiz.direita, valor);
        } else {
            return raiz;
        }

        raiz.altura = 1 + maior(alturaNo(raiz.esquerda), alturaNo(raiz.direita));

        return balancear(raiz, valor);
    }

    Arvore remover(Arvore raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = remover(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = remover(raiz.direita, valor);
        } else {
            // Nó com apenas um filho ou sem filhos
            if (raiz.esquerda == null || raiz.direita == null) {
                Arvore temp = null;
                if (temp == raiz.esquerda) {
                    temp = raiz.direita;
                } else {
                    temp = raiz.esquerda;
                }

                // Sem filhos
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else { // Um filho
                    raiz = temp;
                }
            } else {
                // Nó com dois filhos: obter o sucessor em ordem (menor no subárvore direita)
                Arvore temp = minValueNode(raiz.direita);

                // Copia os dados do sucessor em ordem para este nó
                raiz.valor = temp.valor;

                // Remove o sucessor em ordem
                raiz.direita = remover(raiz.direita, temp.valor);
            }
        }

        // Se a árvore tinha apenas um nó, então retorna
        if (raiz == null) {
            return raiz;
        }

        // 2. Atualiza a altura do nó atual
        raiz.altura = 1 + maior(alturaNo(raiz.esquerda), alturaNo(raiz.direita));

        // 3. Obtém o fator de balanceamento para verificar se o nó atual se tornou desbalanceado
        int balance = fatorBalanceamentoNo(raiz);

        // 4. Se o nó estiver desbalanceado, então existem 4 casos a serem tratados
        return balancear(raiz, valor);
    }


    Arvore maxValueNode(Arvore node) {
        if (node == null) {
            return null;
        }

        /* loop up to find the rightmost leaf */
        while (node.esquerda != null) {
            node = node.esquerda;
        }

        return node;
    }


    Arvore minValueNode(Arvore node) {
        if (node == null) {
            return null;
        }

        /* loop down to find the leftmost leaf */
        while (node.direita != null) {
            node = node.direita;
        }

        return node;
    }


    // Método para gerar um arquivo com a árvore em ordem e o tempo de execução
    void gerarArquivoOrdenacao(String nomeArquivo) {
        ArrayList<Integer> valores = new ArrayList<>();
        long tempoIni = System.currentTimeMillis(); // Inicia a contagem do tempo
        valores = gerarArquivoEmOrdem(this, valores);
        long tempoFim = System.currentTimeMillis(); // Encerra a contagem do tempo
        long tempoTotal = tempoFim - tempoIni; // Calcula o tempo total

        int[] conteudo = valores.stream().mapToInt(i -> i).toArray();

        // Passa o tempo total para o método gravarArquivo
        Arquivo.gravarArquivo(nomeArquivo, conteudo, "Árvore AVL", tempoTotal, 0, 0);
    }

    // Método para gerar um ArrayList com a árvore em ordem
    ArrayList<Integer> gerarArquivoEmOrdem(Arvore node, ArrayList<Integer> valores) {
        if (node != null) {
            gerarArquivoEmOrdem(node.esquerda, valores);
            valores.add(node.valor);
            gerarArquivoEmOrdem(node.direita, valores);
        }
        return valores;
    }

    // Método para imprimir a árvore em ordem
    void imprimirEmOrdem(Arvore node, ArrayList<Integer> valores) {
        if (node != null) {
            imprimirEmOrdem(node.esquerda, valores);
            valores.add(node.valor);
            imprimirEmOrdem(node.direita, valores);
        }
    }

    // Método para contar o número de ocorrências de um valor na árvore
    public int contar(Arvore no, int valor) {
        if (no == null) {
            return 0;
        }

        int count = 0;
        if (no.valor == valor) {
            count = 1;
        }

        return count + contar(no.esquerda, valor) + contar(no.direita, valor);
    }

}