//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//
//public class Dijkstra {
//	
//	int node[][] = new int[200][200];
//	int parent[][] = new int[200][200];
//	boolean visited[][] = new boolean[200][200];
//	int totalDistance[][] = new int[200][200];
//	int x, y;
//	public Dijkstra(int x, int y, int node[][]){
//		this.node = node;
//
//		Arrays.fill(visited, false);
//		for (int[] td : totalDistance) {
//			Arrays.fill(td, Integer.MAX_VALUE);
//		}
//		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
//			@Override
//			public int compare(Node n1, Node n2){
//				return n1.getCost() - n2.getCost();
//			}
//		});
//
//		pq.add(new Node(x, y, node[x][y]));
//
//		while(pq.isEmpty() == false){
//			Node curr = pq.poll();
//			int cost = curr.getCost();
//			int indexI = curr.getI();
//			int indexJ = curr.getJ();
//
//			if(visited[indexI][indexJ] == true){
//				continue;
//			}
//			visited[indexI][indexJ] = true;
//			if(indexI == 20 && indexJ == 25){
//				break;
//			}
//			int rcc[] = {1,-1,0,0};
//			int dcc[] = {0,0,1,-1};
//			for(int i=0; i<4; i++){
//				int totalCost = cost + node[indexI + rcc[i]][indexJ + dcc[i]];
//				if(node[indexI + rcc[i]][indexJ + dcc[i]] != -1 && totalCost < totalDistance[indexI + rcc[i]][indexJ + dcc[i]]){
//					totalDistance[rcc[i]][dcc[i]] = totalCost;
//					pq.add(new Node(indexI + rcc[i],indexJ + dcc[i], totalCost));
//					parent[indexI + rcc[i]][indexJ + dcc[i]] = ;
//				}
//			}
//		}
//		System.out.println("total cost : " + totalDistance[dest]);
//		System.out.println("path : ");
//		showPath(dest);
//
//	}
//	void showPath(int dest){
//		if(dest == -1){
//			return;
//		}
//		showPath(parent[dest]);
//		System.out.print(dest + "=>");
//	}
//}