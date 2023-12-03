package Árvore;

public class ArvoreRubroNegra {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int key;
        Node left, right;
        boolean color;
        int size;

        Node(int key, boolean color, int size) {
            this.key = key;
            this.color = color;
            this.size = size;
        }
    }

    private Node root;

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void inserir(int key) {
        root = inserir(root, key);
        root.color = BLACK;
    }

    private Node inserir(Node h, int key) {
        if (h == null) return new Node(key, RED, 1);

        if (key < h.key) h.left = inserir(h.left, key);
        else if (key > h.key) h.right = inserir(h.right, key);
        else h.key = key;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }


    public boolean buscar(int key) {
        return buscar(root, key) != null;
    }

    private Node buscar(Node x, int key) {
        while (x != null) {
            int cmp = key - x.key;
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x;
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // Método para contar o número de ocorrências de um valor na árvore
    public int contar(int valor) {
        Node node = buscar(root, valor);
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    // Método para remover um valor da árvore
    // Método auxiliar para remover um valor da árvore
    private Node remover(Node h, int key) {
        if (key < h.key) {
            if (h.left != null) {
                if (!isRed(h.left) && !isRed(h.left.left)) {
                    h = moveRedLeft(h);
                }
                h.left = remover(h.left, key);
            }
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key == h.key && (h.right == null)) {
                return null;
            }
            if (h.right != null) {
                if (!isRed(h.right) && !isRed(h.right.left)) {
                    h = moveRedRight(h);
                }
                if (key == h.key) {
                    Node x = minValueNode(h.right);
                    h.key = x.key;
                    h.right = deleteMin(h.right);
                } else {
                    h.right = remover(h.right, key);
                }
            }
        }
        return balance(h);
    }

    // Método para remover um valor da árvore
// Método para remover um valor da árvore
    // Método para remover um valor da árvore
    public void remover(int key) {
        if (!buscar(key)) {
            System.out.println("Erro: valor não encontrado na árvore");
            return;
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = BLACK;
        }
        root = remover(root, key);
        if (!isEmpty() && root != null) { // Add check for root != null
            root.color = BLACK;
        }
    }


    // Método auxiliar para deletar o nó com o menor valor
    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }

        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }

    // Método auxiliar para encontrar o nó com o menor valor
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Método auxiliar para balancear a árvore após remoção
    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    // Método auxiliar para mover o nó vermelho para a esquerda
    private Node moveRedLeft(Node h) {
        // Verifique se h.right não é null antes de chamar flipColors
        if (h.right != null) {
            flipColors(h);
            if (isRed(h.right.left)) {
                h.right = rotateRight(h.right);
                h = rotateLeft(h);
                flipColors(h);
            }
        }
        return h;
    }

    private Node deleteMinFromNode(Node h) {
        if (h == null || h.left == null) {
            return null;
        }

        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }

        if (h.left != null) {
            h.left = deleteMinFromNode(h.left);
        }
        return balance(h);
    }


    // Método auxiliar para mover o nó vermelho para a direita
    private Node moveRedRight(Node h) {
        if (h.left != null) { // Adicione esta verificação
            flipColors(h);
        }
        if (h.left != null && isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
}