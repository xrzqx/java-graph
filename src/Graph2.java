import java.io.*;
import java.util.*;

import javax.xml.stream.events.StartElement;

class Graph2 {
    private static HashMap <String,LinkedList<String>> adj = new HashMap<String, LinkedList<String>>();
    private static HashMap <String, String> parent = new HashMap<String,String>();

    void addEdge(String v, String w) {
        if (adj.containsKey(v)) {
            adj.get(v).add(w);
        }
        else {
            adj.put(v, new LinkedList<String>());
            adj.get(v).add(w);
        }
    }

    void BFS(String s,String g) {
        parent.put(s, null);
        LinkedList<String> frontier = new LinkedList<String>();
        frontier.add(s);
        while (!frontier.isEmpty()) {
            LinkedList<String>next = new LinkedList<String>();
            for (String u : frontier) {
                if (!adj.containsKey(u)) {
                    frontier.removeFirst();
                }
                else {
                    for (String v : adj.get(u)) {
                        if (!parent.containsKey(v)) {
                            parent.put(v, u);
                            next.add(v);
                        }
                    }
                }
            }
            frontier = next;
        }
        bfsPath(g);
    }

    void bfsPath(String goal) {
        String temp = parent.get(goal);
        if (temp != null) {
            bfsPath(temp);
        }
        System.out.print(goal + " ");
    }

    public static void main(String args[]) {
        Graph2 g = new Graph2();

        g.addEdge("s", "a");
        g.addEdge("s", "c");
        g.addEdge("c", "a");
        g.addEdge("a", "b");
        g.addEdge("b", "g");

        System.out.println("Following is Breadth First Traversal " + "(starting from vertex S)");

        String s = "s";
        String goal = "g";

        g.BFS(s,goal);

    }
}