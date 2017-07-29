package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	int				vertexCount;
	boolean[][]		graph;
	// 3 ways to represent
	// 1. Adjacency matrix
	// 2. Adjacency list -- Disadvantage of using linked list
	// 3. Adjacency Set
	// Please check 9.5 for differences.
	List<GraphNode>	nodes			= new ArrayList<GraphNode>();
	int[][]			adjacencyMatrix	= null;
	GraphNode rootNode=null;
	Graph() {

	}

	public void addGraphNode(GraphNode n) {
		nodes.add(n);
	}
	
	public void setRootGraphNode(GraphNode n) {
		rootNode=n;
	}

	public void connectGraphNode(GraphNode source, GraphNode destination) {
		if (adjacencyMatrix == null) {
			adjacencyMatrix = new int[nodes.size()][nodes.size()];
		}
		int sourceIndex = nodes.indexOf(source);
		int destinationIndex = nodes.indexOf(destination);
		adjacencyMatrix[sourceIndex][destinationIndex] = 1;
		adjacencyMatrix[destinationIndex][sourceIndex] = 1;
	}

}
