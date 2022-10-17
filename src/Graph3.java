import java.util.*;

class Graph3 {

    public static void main(String[] args) {
        Node start = new Node("Start", 0);
        Node a = new Node("A", 3);
        Node b = new Node("B", 3);
        Node c = new Node("C", 1);
        Node d = new Node("D", 2);
        Node goal = new Node("Goal", 0);
        start.adj = new Edge[] { new Edge(a, 2), new Edge(b, 1) };
        a.adj = new Edge[] { new Edge(c, 3), new Edge(d, 1), new Edge(b, 1) };
        b.adj = new Edge[] { new Edge(d, 5), new Edge(goal, 10) };
        c.adj = new Edge[] { new Edge(goal, 7) };
        d.adj = new Edge[] { new Edge(goal, 4) };

        AstarSearch(start, d);

        List<Node> path = printPath(goal);
        System.out.println("A * Search");
        for (Node n : path) {
            System.out.print(n.nama + " ");
        }

    }

    public static List<Node> printPath(Node target) {
        List<Node> path = new ArrayList<Node>();

        for (Node node = target; node != null; node = node.parent) {
            path.add(node);
        }

        Collections.reverse(path);

        return path;
    }

    public static void AstarSearch(Node source, Node goal) {

        Set<Node> explored = new HashSet<Node>();

        PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {
            public int compare(Node i, Node j) {
                if (i.f_score > j.f_score) {
                    return 1;
                }

                else if (i.f_score < j.f_score) {
                    return -1;
                }

                else {
                    return 0;
                }
            }

        });
        source.g_score = 0;

        queue.add(source);

        boolean found = false;

        while ((!queue.isEmpty()) && (!found)) {
            Node current = queue.poll();

            explored.add(current);
            if (current.nama.equals(goal.nama)) {
                found = true;
            }
            for (Edge e : current.adj) {
                Node child = e.target;
                double cost = e.cost;
                double temp_g_scores = current.g_score + cost;
                double temp_f_scores = temp_g_scores + child.h_score;
                if ((explored.contains(child)) && (temp_f_scores >= child.f_score)) {
                    continue;
                } else if ((!queue.contains(child)) || (temp_f_scores < child.f_score)) {

                    child.parent = current;
                    child.g_score = temp_g_scores;
                    child.f_score = temp_f_scores;

                    if (queue.contains(child)) {
                        queue.remove(child);
                    }

                    queue.add(child);

                }

            }

        }

    }

}

class Node {

    public String nama;
    public double g_score;
    public double h_score;
    public double f_score = 0;
    public Edge[] adj;
    public Node parent;

    public Node(String val, double hVal) {
        nama = val;
        h_score = hVal;
    }

}

class Edge {
    public double cost;
    public Node target;

    public Edge(Node targetNode, double costVal) {
        target = targetNode;
        cost = costVal;
    }
}