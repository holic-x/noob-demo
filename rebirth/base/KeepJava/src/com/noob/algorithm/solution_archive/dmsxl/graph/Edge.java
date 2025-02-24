package com.noob.algorithm.solution_archive.dmsxl.graph;

public class Edge {
    public int u; // 边的端点1
    public int v; // 边的端点2
    public int val; // 边的权值

    public Edge() {
    }

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
        this.val = 0;
    }

    public Edge(int u, int v, int val) {
        this.u = u;
        this.v = v;
        this.val = val;
    }
}