import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	Pair parent[][] = new Pair[100][100];
	
	int node[][] = new int[200][200];
	int newX, newY;
	
	boolean visited[][] = new boolean[200][200];
	int totalDistance[][] = new int[200][200];
	int x, y;
	
	public Dijkstra(int x, int y, int node[][]){
		
		this.node = node;
		
		this.x = x;
		this.y = y;
		
		for (boolean[] td : visited) {
			Arrays.fill(td, false);
		}
		
		for (int[] td : totalDistance) {
			Arrays.fill(td, Integer.MAX_VALUE);
		}
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2){
				return n1.getCost() - n2.getCost();
			}
		});

		pq.add(new Node(x, y, node[x][y]));
		parent[x][y] = new Pair(-1,-1);
		
		
		while(pq.isEmpty() == false){
			Node curr = pq.poll();
			int cost = curr.getCost();
			int indexI = curr.getI();
			int indexJ = curr.getJ();
			
			if(visited[indexI][indexJ] == true){
				continue;
			}
			visited[indexI][indexJ] = true;
			
			if(indexI == 20 && indexJ == 25){
				break;
			}
//			System.out.println("process : " + indexI + " " + indexJ + " = " + parent[indexI][indexJ].getFirst() + " " + parent[indexI][indexJ].getSecond() + " " + cost);
			int rcc[] = {1,-1,0,0};
			int dcc[] = {0,0,1,-1};
			for(int i=0; i<4; i++){
				if(visited[indexI + rcc[i]][indexJ + dcc[i]]) continue;
				int totalCost = cost + node[indexI + rcc[i]][indexJ + dcc[i]];
				if(node[indexI + rcc[i]][indexJ + dcc[i]] != -1 && totalCost < totalDistance[indexI + rcc[i]][indexJ + dcc[i]]){
					totalDistance[indexI + rcc[i]][indexJ + dcc[i]] = totalCost;
					pq.add(new Node(indexI + rcc[i],indexJ + dcc[i], totalCost));
						
					parent[indexI + rcc[i]][indexJ + dcc[i]] = new Pair(indexI, indexJ);
				}
//				
			}
//			System.out.println("process : " + indexI + " " + indexJ + " = " + parent[indexI][indexJ].getFirst() + " " + parent[indexI][indexJ].getSecond() + " " + cost);
		}
		
	}
	public int showPath(int i, int j, int newX, int newY){
		if(i == newX && j == newY) return 0;
		int ret = 0;
		if(parent[i][j].getFirst() == newX && parent[i][j].getSecond() == newY){
			if(i < newX){
				ret = 1;
				return ret;
			}
			if(i > newX){
				ret = 2;
				return ret;
			}
			if(j < newY){
				ret = 3;
				return ret;
			}
			if(j > newY){
				ret = 4;
				return ret;
			}
		}
		
		ret = showPath(parent[i][j].getFirst(), parent[i][j].getSecond(), newX, newY);
		
		return ret;
	}
	
	
}
