package com.noob.algorithm.solution_archive.dmsxl.kmw.q105;

import java.util.*;

public class Main {
    public static List<List<Integer>> adjList = new ArrayList<>();

    public static void dfs(boolean[] visited, int key) {
        if (visited[key]) {
            return;
        }
        visited[key] = true;
        List<Integer> nextKeys = adjList.get(key);
        for (int nextKey : nextKeys) {
            dfs(visited, nextKey);
        }
    }

    public static void bfs(boolean[] visited, int key) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(key);
        visited[key] = true;
        while (!queue.isEmpty()) {
            int curKey = queue.poll();
            List<Integer> list = adjList.get(curKey);
            for (int nextKey : list) {
                if (!visited[nextKey]) {
                    queue.add(nextKey);
                    visited[nextKey] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices_num = sc.nextInt();
        int line_num = sc.nextInt();
        for (int i = 0; i < vertices_num; i++) {
            adjList.add(new LinkedList<>());
        }//Initialization
        for (int i = 0; i < line_num; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            adjList.get(s - 1).add(t - 1);
        }//构造邻接表
        boolean[] visited = new boolean[vertices_num];
        dfs(visited, 0);
//        bfs(visited, 0);

        for (int i = 0; i < vertices_num; i++) {
            if (!visited[i]) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(1);
    }
}