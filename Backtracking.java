package com.simplilearn.mavenproject;

import java.util.Arrays;
import java.util.Random;

public class Backtracking {
	public int[][] graph;
	public int V;
	public boolean[] visited;
	
	public Backtracking(int[][] graph) {
		this.graph = graph;
		this.V = graph.length;
		this.visited = new boolean[V];
	}
	
	public boolean hasHamPath() {
		for(int i = 0; i < V; i++) {
			Arrays.fill(visited,  false);
			if(hamPathUtil(i,1)){
				return true;
			}
		}
		return false;
	}
	
	private boolean hamPathUtil(int v, int count) {
		visited[v] = true;
		if (count == v) {
			return true;
		}
		
		for(int i = 0; i < V; i++) {
			if(graph[v][i] == 1 && !visited[i]) {
				if(hamPathUtil(i, count + 1)) {
					return true;
				}
			}
		}
		visited[v] = false;
		return false;
	}
	
	public static void main(String[] args, int n) {
		//int n;
		Random rand = new Random();
		int[][] graph = null;
		
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				graph[i][j] = rand.nextInt(1);
				graph[j][i] = graph[i][j];
			}
		}
		Backtracking hp = new Backtracking(graph);
		System.out.print(hp.hasHamPath());
	}
}
