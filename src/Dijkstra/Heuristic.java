package Dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Heuristic {
	int edges[][] = new int[200][200];
	int parent[] = new int[200];
	boolean visited[] = new boolean[200];
	int totalDistance[] = new int[200];
	int heuristic[] = new int[200];
	public Heuristic(){
		for(int row[] : edges){
			Arrays.fill(row, -1);
		}
		Arrays.fill(visited, false);
		Arrays.fill(totalDistance, Integer.MAX_VALUE);
		
		Scanner scan = new Scanner(System.in);
		int node = scan.nextInt();
		int n = scan.nextInt();
		scan.nextLine();
		
		for(int i=0; i<n; i++){
			heuristic[i] = scan.nextInt();
		}
		scan.nextLine();
		
		int x, y, z;
		
		for(int i=0; i<n; i++){
			x = scan.nextInt();
			y = scan.nextInt();
			z = scan.nextInt();
			scan.nextLine();
			edges[x][y] = z;
			edges[y][x] = z;
		}
		
		int source = scan.nextInt();
		int dest = scan.nextInt();
		scan.nextLine();
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
			@Override
			public int compare(Node n1, Node n2){
				return n1.getCost() - n2.getCost();
			}
		});
		
		totalDistance[source] = 0;
		parent[source] = -1;
		
		pq.add(new Node(source, 0));
		
		while(pq.isEmpty() == false){
			Node curr = pq.poll();
			int index = curr.getIndex();
			int cost = curr.getCost();
			
			if(visited[index] == true){
				continue;
			}
			visited[index] = true;
			if(index == dest){
				break;
			}
			for(int i=0; i<n; i++){
				int totalCost = edges[index][i] + cost + heuristic[i];
				if(edges[index][i] != -1 && totalDistance[i] >= totalCost){
					pq.add(new Node(i, totalCost));
					totalDistance[i]  = totalCost;
					parent[i] = index;
				}
			}
		}
		System.out.println("Total cost : " + totalDistance[dest]);
		System.out.println("Path : ");
		showPath(dest);		
	}
	public void showPath(int dest){
		if(dest == -1){
			return;
		}
		showPath(parent[dest]);
		System.out.print(dest + " => ");
	}
}
