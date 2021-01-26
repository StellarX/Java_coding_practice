package space.graph;

import java.util.ArrayList;

public class AdjGraph {
//	private static final int MAXV = 100;
	private static final int INF = 999999;
	static final int n = 4;
	static final int e = 5;
	
	static class Graph{
		ArrayList<Vnode> adjlist;
		int n, e;
		Graph(int n, int e, ArrayList<Vnode> adjlist){
			this.n = n;
			this.e = e;
			this.adjlist = adjlist;
		}
	}
	static class Vnode{
		char info;
		Arcnode firstarc;
	}
	static class Arcnode{
		int no;
		int weight;
		Arcnode nextarc;
		Arcnode(int weight) {this.weight = weight;}
	}

	static int[] dfs_visited = new int[n];
	public static void main(String[] args) {
		int[][] arr = {
			{0, 1,   1,   1},
			{1, 0,   INF, 1},
			{1, INF, 0,   1},
			{1, 1,   1,   0}
		};
		Graph g = createAdj(arr, n, e);
		dispAdj(g);
//		bfs(g, 1);
		System.out.println();
		System.out.println("res: " + isTree(g)); //todo some problems occurred
//		dfs(g, 1);
	}
	static int vn = 0, en = 0;
	static int isTree(Graph graph){

		for(int i = 0; i < graph.n; i++) {
			dfs_visited[i] = 0;
		}
		dfs2(graph, 0, vn, en);
		System.out.println();
		System.out.println("vn: " + vn + "  en: " + en);
		if (vn == graph.n && en/2 == graph.n - 1) return 1;
		else return 0;
	}
	static void dfs2(Graph graph, int v, int vn, int en){
		dfs_visited[v] = 1;
		vn++;

//		System.out.print(v + " ");
		Arcnode a = graph.adjlist.get(v).firstarc;
		while(a != null) {
			en++;
			System.out.println("en: " + en);
			if(dfs_visited[a.no] == 0) {
				dfs2(graph, a.no, vn, en);
			}
			else a = a.nextarc;
		}
	}
	public static Graph createAdj(int[][] arr, int n, int e) {
		Graph g = new Graph(n, e, new ArrayList<Vnode>());
		for(int i = 0; i < n; i++) {
			g.adjlist.add(new Vnode());
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(arr[i][j] != INF && arr[i][j] != 0) {
					Arcnode arcnode = new Arcnode(arr[i][j]);
					arcnode.no = j;
					//尾插法
					if(g.adjlist.get(i).firstarc == null)
						g.adjlist.get(i).firstarc = arcnode;
					else {
						Arcnode p = g.adjlist.get(i).firstarc;
						while(p.nextarc != null) {
							p = p.nextarc;
						}
						p.nextarc = arcnode;
					}
					//头插法，简单一点，只需2行
//					arcnode.nextarc = g.adjlist.get(i).firstarc;
//					g.adjlist.get(i).firstarc = arcnode;
				}
			}
		}
		return g;
	}
	public static void bfs(Graph g, int v) {
		int[] visited = new int[g.n];
		int[] queue = new int[g.n];
		int front = 0, rear = 0;
		visited[v] = 1;
		System.out.print(v + " ");
		rear = (rear + 1) % g.n;
		queue[rear] = v;
		while(front != rear) {
			front = (front + 1) % g.n;
			int t = queue[front];
			Arcnode arc = g.adjlist.get(t).firstarc;
			while(arc != null) {
				if(visited[arc.no] == 0) {
					visited[arc.no] = 1;
					System.out.print(arc.no + " ");
					rear = (rear + 1) % g.n;
					queue[rear] = arc.no;
				}
				else arc = arc.nextarc;
			}
		}
	}


	public static void dfs(Graph g, int v) {
		dfs_visited[v] = 1;
		System.out.print(v + " ");
		Arcnode a = g.adjlist.get(v).firstarc;
		while(a != null) {
			if(dfs_visited[a.no] == 0) {
				dfs(g, a.no);
			}
			else a = a.nextarc;
		}
		
	}
	public static void dispAdj(Graph g) {
		for(int i = 0; i < g.n; i++) {
			System.out.print("no:" + i + " :: ");
			Arcnode arc = g.adjlist.get(i).firstarc;
			while(arc != null) {
				System.out.print("no:" + arc.no + " --> ");
				arc = arc.nextarc;
			}
			System.out.println();
		}
	}
}





