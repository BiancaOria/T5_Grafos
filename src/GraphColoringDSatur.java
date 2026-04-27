import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
public class GraphColoringDSatur {
    private final Graph graph;
    private int[] colors;
    private int[] coloringOrder;
    private int totalColors;
    private boolean colored = false;

    public GraphColoringDSatur(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }
        this.graph = graph;
        this.colors = new int[graph.V()];
        this.coloringOrder = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) colors[i] = -1;
    }

    public Graph getGraph() {
        return graph;
    }

    private Set<Integer> getNeighborColors(int v) {
        Set<Integer> neighborColors = new HashSet<>();
        for (int neighbor : graph.adj(v)) {
            int color = colors[neighbor];
            if (color != -1) {
                neighborColors.add(color);
            }
        }
        return neighborColors;
    }
    public int calculateDS(int v) {
        return getNeighborColors(v).size();
    }

    private int findSmallestAvailableColor(int v) {
        Set<Integer> occupiedColors = getNeighborColors(v);
        int color = 0;
        while (occupiedColors.contains(color)) {
            color++;
        }
        return color;
    }
    public void color() {
        int V = graph.V();

        Set<Integer> uncolored = new HashSet<>();
        for (int i = 0; i < V; i++) {
            uncolored.add(i);
            colors[i] = -1;
        }

        int step = 0;
        while (!uncolored.isEmpty()) {
            int vAtual = -1;
            int maiorDS = -1;
            int maiorGrau = -1;


            for (int u : uncolored) {
                int ds = calculateDS(u);
                int degree = graph.degree(u);

                if (ds > maiorDS || (ds == maiorDS && degree > maiorGrau)) {
                    maiorDS = ds;
                    maiorGrau = degree;
                    vAtual = u;
                }
            }

            int k = findSmallestAvailableColor(vAtual);
            colors[vAtual] = k;

            coloringOrder[step++] = vAtual;


            if (k >= totalColors) {
                totalColors = k + 1;
            }

            uncolored.remove(vAtual);
        }
        this.colored = true;
    }

    public int getColor(int vertex) {
        return colors[vertex];
    }

    public int getColorCount() {
        return totalColors;
    }

    public int[] getColoringOrder() {
        return coloringOrder;
    }

    public boolean isValidColoring() {
        if (!colored) return false;
        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                if (v != w && colors[v] == colors[w]) return false;
            }
        }
        return true;
    }

    public String getLabel(int vertex) {
        String[] labels = {
                "AC", // 0
                "AL", // 1
                "AM", // 2
                "AP", // 3
                "BA", // 4
                "CE", // 5
                "DF", // 6
                "ES", // 7
                "GO", // 8
                "MA", // 9
                "MG", // 10
                "MS", // 11
                "MT", // 12
                "PA", // 13
                "PB", // 14
                "PE", // 15
                "PI", // 16
                "PR", // 17
                "RJ", // 18
                "RN", // 19
                "RO", // 20
                "RR", // 21
                "RS", // 22
                "SC", // 23
                "SE", // 24
                "SP", // 25
                "TO"  // 26
        };
        if (vertex >= 0 && vertex < labels.length) return labels[vertex];
        return "V" + vertex;
    }
}
