import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Enemy {
	private int x;
	private int y;
	private int velx = 1;
	private int vely = 1;

	// buat djikstra
	boolean[][] visited = new boolean[1000][1000];
	int[][] totalDistance = new int[1000][1000];
	int[] parentX = new int[1000];
	int[] parentY = new int[1000];
	// end djikstra
	
	int [][] weight = new int [200][200];
	
	private int unit = 20;
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update(Graphics2D g, Tile tile){
		Djikstra();
		tile.drawEnemy(this.x, this.y, g, unit);
	}
	
	void showPath(int currX, int currY){
		if(parentX[currX] == -1 || parentY[currY] == -1){
			this.x = currX;
			this.y = currY;
			return;
		}
		System.out.println(currX + "-" + currY);
		showPath(parentX[currX], parentY[currY]);
	}
	
	// dest x = 20 , y = 25
	
	void Djikstra(){
		
		for (int[] is : totalDistance) {
			Arrays.fill(is, Integer.MAX_VALUE);
		}
		
		for (boolean[] is : visited) {
			Arrays.fill(is, false);
		}
		
		parentX[this.x]= -1;
		parentY[this.y] = -1;
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			};
		});
		
		totalDistance[this.x][this.y] = 0;
		pq.add(new Node(this.x, this.y, 0));
		
		while(pq.isEmpty() == false) {
			Node curr = pq.poll();
			int currX = curr.getI();
			int currY = curr.getJ();
			int cost = curr.getCost();
			if(visited[currX][currY] == true) continue;
			visited[currX][currY] = true;
			if(currX == 20 && currY == 25){
				break;
			}
			int rcc[] = {1, -1, 0 ,0};
			int dcc[] = {0,0,1,-1};
			if(currX == 20 || currY == 24)System.out.println(parentX[20] + " " + parentY[24]);
			
			for (int i = 0; i < 4; i++){
				int totalCost = weight[currX + rcc[i]][currY + dcc[i]] + cost;
				if(weight[currX + rcc[i]][currY + dcc[i]] != -1 && totalCost < totalDistance[currX + rcc[i]][currY + dcc[i]]){
					if(currX + rcc[i] == 20 || currY + dcc[i] == 25){
						System.out.println("AY " + currX + " " + currY + " " + totalCost);
						System.out.println(weight[currX + rcc[i]][currY + dcc[i]]);
						System.out.println(cost);
						System.out.println("PARENT : " + parentX[currX] + " " + parentY[currY]);
					}
					pq.add(new Node(currX + rcc[i], currY + dcc[i], totalCost));
					totalDistance[currX + rcc[i]][currY + dcc[i]] = totalCost;
					parentX[currX + rcc[i]] = currX;
					parentY[currY + dcc[i]] = currY;
				}
			}
		}
		showPath(20, 25);
	}

	
	
	
	
	
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getVelx() {
		return velx;
	}

	public void setVelx(int velx) {
		this.velx = velx;
	}

	public int getVely() {
		return vely;
	}

	public void setVely(int vely) {
		this.vely = vely;
	}

	public void setWeight(int[][] weight){
		this.weight = weight;
	}
	
}
