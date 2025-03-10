import javax.swing.*;
import java.awt.*;
import java.util.*;

class Vertex {
    private String label;
    private final java.util.List<Vertex> adjacentVertices;

    public Vertex(String label) {
        this.label = label;
        this.adjacentVertices = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public java.util.List<Vertex> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void addAdjacentVertex(Vertex vertex) {
        adjacentVertices.add(vertex);
    }
}

class Graph {
    private Map<String, Vertex> vertices;
    private Map<String, Integer> edgeWeights;

    public Graph() {
        this.vertices = new HashMap<>();
        this.edgeWeights = new HashMap<>();
    }

    public void addVertex(String label) {
        vertices.putIfAbsent(label, new Vertex(label));
    }

    public void addEdge(String label1, String label2) {
        Vertex vertex1 = vertices.get(label1);
        Vertex vertex2 = vertices.get(label2);
        if (vertex1 != null && vertex2 != null) {
            vertex1.addAdjacentVertex(vertex2);
            vertex2.addAdjacentVertex(vertex1);
            String edgeKey = label1 + "-" + label2;
            edgeWeights.put(edgeKey, edgeWeights.getOrDefault(edgeKey, 0) + 1);
        }
    }

    public Map<String, Vertex> getVertices() {
        return vertices;
    }

    public Map<String, Integer> getEdgeWeights() {
        return edgeWeights;
    }
}

class GraphPanel extends JPanel {
    private Graph graph;

    public GraphPanel(Graph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Map<String, Point> positions = new HashMap<>();
        int numVertices = graph.getVertices().size();
        int radius = Math.min(width, height) / 3;
        int centerX = width / 2;
        int centerY = height / 2;
        
        int i = 0;
        for (String label : graph.getVertices().keySet()) {
            int x = centerX + (int) (radius * Math.cos(2 * Math.PI * i / numVertices));
            int y = centerY + (int) (radius * Math.sin(2 * Math.PI * i / numVertices));
            positions.put(label, new Point(x, y));
            i++;
        }

        g.setColor(Color.BLACK);
        for (Vertex vertex : graph.getVertices().values()) {
            Point p1 = positions.get(vertex.getLabel());
            for (Vertex adjacent : vertex.getAdjacentVertices()) {
                Point p2 = positions.get(adjacent.getLabel());
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                String edgeKey = vertex.getLabel() + "-" + adjacent.getLabel();
                int weight = graph.getEdgeWeights().getOrDefault(edgeKey, 1);
                g.setColor(Color.RED);
                g.drawString(String.valueOf(weight), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
                g.setColor(Color.BLACK);
            }
        }

        for (Map.Entry<String, Point> entry : positions.entrySet()) {
            g.setColor(Color.PINK);
            g.fillOval(entry.getValue().x - 25, entry.getValue().y - 25, 50, 50);
            g.setColor(Color.WHITE);
            g.drawString(entry.getKey(), entry.getValue().x - 10, entry.getValue().y + 5);
        }
    }
}

public class GraphGUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        System.out.println("Enter the first schedule (comma-separated):");
        String[] schedule1 = scanner.nextLine().split(",");

        System.out.println("Enter the second schedule (comma-separated):");
        String[] schedule2 = scanner.nextLine().split(",");

        Set<String> uniqueSubjects = new HashSet<>();
        Collections.addAll(uniqueSubjects, schedule1);
        Collections.addAll(uniqueSubjects, schedule2);

        for (String subject : uniqueSubjects) {
            graph.addVertex(subject.trim());
        }

        addEdges(graph, schedule1);
        addEdges(graph, schedule2);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Adjacency Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.add(new GraphPanel(graph));
            frame.setVisible(true);
        });
    }

    private static void addEdges(Graph graph, String[] schedule) {
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule.length; j++) {
                if (i != j) {
                    graph.addEdge(schedule[i].trim(), schedule[j].trim());
                }
            }
        }
    }
}