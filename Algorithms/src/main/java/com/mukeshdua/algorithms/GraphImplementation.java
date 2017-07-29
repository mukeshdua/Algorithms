package com.mukeshdua.algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphImplementation {
	// Two ways to traverse graphs:
	// DFS: TimeComplexity - O(V2) if graph is represented as Adjacency matrix but O(V+E) if graph is represented as Adjacency Lis
	// Problems:Maze, Topological sort, Finding connected component number
	// BFS:
	// Check Page 9.6 to get comparison and applications.
	public static void dfs(int[][] M, int[] visited, int i) {
		System.out.println("dfs-" + i);
		for (int j = 0; j < M.length; j++) {
			System.out.println("dfs:inner-" + j);
			if (M[i][j] == 1 && visited[j] == 0) {
				System.out.println("dfs:inner-inner-" + j);
				visited[j] = 1;
				dfs(M, visited, j);
			}
		}
	}

	public static int findCircleNum(int[][] M) {
		int[] visited = new int[M.length];
		int count = 0;
		for (int i = 0; i < M.length; i++) {
			if (visited[i] == 0) {
				System.out.println("findCircleNum-" + i);
				dfs(M, visited, i);
				count++;
			}
		}
		return count;
	}

	// BFS traversal of a tree is performed by the bfs() function

	// DFS traversal of a tree is performed by the dfs() function
	public static void DepthFirstSearchSimple(Graph g) {
		Stack<GraphNode> s = new Stack<GraphNode>();
		s.push(g.rootNode);
		g.rootNode.isVisited = true;
		System.out.println(g.rootNode.value);
		while (!s.isEmpty()) {
			GraphNode n = (GraphNode) s.peek();
			GraphNode child = getUnvisitedNode(g, n);
			if (child != null) {
				child.isVisited = true;
				System.out.println(child.value);
				s.push(child);
			} else {
				s.pop();
			}
		}
		clearNodes(g);
	}

	// DFS traversal of a tree is performed by the dfs() function
	public static void BreadthFirstSearchSimple(Graph g) {
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.add(g.rootNode);
		g.rootNode.isVisited = true;
		System.out.println(g.rootNode.value);
		while (!q.isEmpty()) {
			GraphNode n = (GraphNode) q.remove();
			GraphNode child=null;
			while((child=getUnvisitedNode(g,n))!=null)
			{
				child.isVisited=true;
				System.out.println(child.value);
				q.add(child);
			}
		}
		clearNodes(g);
	}

	public static GraphNode getUnvisitedNode(Graph g, GraphNode currentNode) {
		int currentNodeIndex = g.nodes.indexOf(currentNode);
		for (int j = 0; j < g.nodes.size(); j++) {

			if (g.adjacencyMatrix[currentNodeIndex][j] == 1 && ((GraphNode) g.nodes.get(j)).isVisited == false) {
				return (GraphNode) g.nodes.get(j);
			}
		}
		return null;
	}

	// Utility methods for clearing visited property of node
	private static void clearNodes(Graph g) {
		int i = 0;
		while (i < g.nodes.size()) {
			GraphNode n = (GraphNode) g.nodes.get(i);
			n.isVisited = false;
			i++;
		}
	}

	public static void main(String[] args) {
		// Perform the traversal of the graph
		System.out.println("DFS Traversal of a tree is ------------->");
		Graph g = generateGraph();
		DepthFirstSearchSimple(g);

		System.out.println("\nBFS Traversal of a tree is ------------->");
		BreadthFirstSearchSimple(g);

		int[][] M = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		findCircleNum(M);
	}

	public static Graph generateGraph() {
		// Lets create nodes as given as an example in the article
		GraphNode nA = new GraphNode("A");
		GraphNode nB = new GraphNode("B");
		GraphNode nC = new GraphNode("C");
		GraphNode nD = new GraphNode("D");
		GraphNode nE = new GraphNode("E");
		GraphNode nF = new GraphNode("F");

		// Create the graph, add nodes, create edges between nodes
		Graph g = new Graph();
		g.addGraphNode(nA);
		g.addGraphNode(nB);
		g.addGraphNode(nC);
		g.addGraphNode(nD);
		g.addGraphNode(nE);
		g.addGraphNode(nF);
		g.setRootGraphNode(nA);

		g.connectGraphNode(nA, nB);
		g.connectGraphNode(nA, nC);
		g.connectGraphNode(nA, nD);

		g.connectGraphNode(nB, nE);
		g.connectGraphNode(nB, nF);
		g.connectGraphNode(nC, nF);
		return g;
	}

}
