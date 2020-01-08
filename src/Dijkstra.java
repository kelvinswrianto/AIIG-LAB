import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	Pair parent[][] = new Pair[100][100];
	
	int node[][] = new int[200][200];
	
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
				if(indexI + rcc[i] > 3 && indexJ + dcc[i] < 30){
					if(visited[indexI + rcc[i]][indexJ + dcc[i]]) continue;
					int totalCost = cost + node[indexI + rcc[i]][indexJ + dcc[i]];
					if(node[indexI + rcc[i]][indexJ + dcc[i]] != -1 && totalCost < totalDistance[indexI + rcc[i]][indexJ + dcc[i]]){
						totalDistance[indexI + rcc[i]][indexJ + dcc[i]] = totalCost;
						pq.add(new Node(indexI + rcc[i],indexJ + dcc[i], totalCost));
						
						parent[indexI + rcc[i]][indexJ + dcc[i]] = new Pair(indexI, indexJ);
					}
				}
			}
//			System.out.println("process : " + indexI + " " + indexJ + " = " + parent[indexI][indexJ].getFirst() + " " + parent[indexI][indexJ].getSecond() + " " + cost);
		}
		showPath(20, 25);
		
	}
	public void showPath(int i, int j){
		if(i == -1 || j == -1){
			return;
		}
		showPath(parent[i][j].getFirst(), parent[i][j].getSecond());
		System.out.println(i + " " + j);
		
	}
	
	
}
