import java.io.*;
import java.util.*;


class Graph {
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

    void dfsVisit(String s) {
        if (adj.containsKey(s)) {
            for (String x : adj.get(s)) {
                if (!parent.containsKey(x)) {
                    parent.put(x, s);
                    dfsVisit(x);
                }
            }
        }
    }

    void dfsPath(String goal) {
        String temp = parent.get(goal);
        if (temp != null) {
            dfsPath(temp);
        }
        System.out.print(goal + " ");
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge("s", "a");
        g.addEdge("s", "c");
        g.addEdge("a", "c");
        g.addEdge("c", "b");
        g.addEdge("c", "g");
        parent.put("s", null); //node start from s
        String start = "s";
        String goal = "g";
        g.dfsVisit(start);
        g.dfsPath(goal);

    }

}