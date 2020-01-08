import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	int node[][] = new int[200][200];
	int parent[] = new int[200];
	boolean visited[] = new boolean[200];
	int totalDistance[] = new int[200];
	
	public Dijkstra(Tile tile, int source){

		Arrays.fill(visited, false);

		

		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2){
				return n1.getCost() - n2.getCost();
			}
		});

		pq.add(new Node(source, 0));

		while(pq.isEmpty() == false){
			Node curr = pq.poll();
			int cost = curr.getCost();
			int index = curr.getIndex();

			if(visited[index] == true){
				continue;
			}
			visited[index] = true;
			if(index == dest){
				break;
			}
			for(int i=0; i<node; i++){
				int totalCost = cost + edges[i][index];
				if(edges[i][index] != -1 && totalCost < totalDistance[i]){
					totalDistance[i] = totalCost;
					pq.add(new Node(i, totalCost));
					parent[i] = index;
				}
			}
		}
		System.out.println("total cost : " + totalDistance[dest]);
		System.out.println("path : ");
		showPath(dest);

	}
	void showPath(int dest){
		if(dest == -1){
			return;
		}
		showPath(parent[dest]);
		System.out.print(dest + "=>");
	}
}