public class Main {
    public static void main(String[] args) {

        String caminho = "./src/dados/brasil.txt";
//        String caminho = "./src/dados/teste-caminho4.txt";

        In in = new In(caminho);
        Graph graph = new Graph(in);
        GraphColoringDSatur dsatur = new GraphColoringDSatur(graph);

        StdOut.println("=== Processando Mapa do Brasil ===");
        dsatur.color();

        StdOut.println("Ordem de Coloracao:");
        StdOut.println("-------------------------------------------");
        int[] order = dsatur.getColoringOrder();
        for (int i = 0; i < order.length; i++) {
            int v = order[i];
            StdOut.printf("%02dº: %-5s | ID: %-5d\n", (i + 1), dsatur.getLabel(v), v);

        }

        StdOut.println("\nResultado da Coloração:");
        StdOut.printf("%-20s | %s\n", "Estado", "Cor Atribuída");
        StdOut.println("-------------------------------------------");
        for (int v = 0; v < graph.V(); v++) {
            StdOut.printf("%-20s | Cor %d\n", dsatur.getLabel(v), dsatur.getColor(v));
        }

        StdOut.println("\n-------------------------------------------");
        StdOut.println("Total de cores utilizadas: " + dsatur.getColorCount());

        if (dsatur.isValidColoring()) {
            StdOut.println("Status: Coloração VÁLIDA (Nenhum vizinho com cor igual).");
        } else {
            StdOut.println("Status: ERRO - Coloração inválida encontrada!");
        }

        if (dsatur.getColorCount() <= 4) {
            StdOut.println("Observação: Respeita o Teorema das Quatro Cores.");
        }
        StdOut.println("-------------------------------------------");

    }
}
