import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Enemy {
	private String name;
	private int x;
	private int y;
	private int velx = 1;
	private int vely = 1;
	

	// buat djikstra
	int [][] edges = new int[1000][1000];
	boolean[][] visited = new boolean[1000][1000];
	int[][] totalDistance = new int[1000][1000];
	int[] parentX = new int[1000];
	int[] parentY = new int[1000];
	// end djikstra
	
	private int unit = 20;
	private Tile tile = new Tile();
	public Enemy(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void update(Graphics2D g){
		Djikstra();
		tile.drawEnemy(this.x, this.y, g, unit);
	}
	
	void showPath(int currX, int currY){
		if(parentX[currX] == -1 || parentY[currY] == -1){
			this.x = currX;
			this.y = currY;
			return;
		}
		showPath(parentX[currX], parentY[currY]);
//		System.out.print(currX + "-" + currY + " ");
		
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
			int currX = curr.x;
			int currY = curr.y;
			int cost = curr.cost;
			
			if(visited[x][y] == true) continue;
			visited[x][y] = true;
			
			if(x == 20 && y == 25) break;
			int rcc[] = {1, -1, 0 ,0};
			int dcc[] = {0,0,-1,1};
			
			for (int i = 0; i < 4; i++){
				int totalCost = edges[currX + rcc[i]][currY + dcc[i]] + cost;
				if(edges[i][j] != -1 && totalCost < totalDistance[currX + rcc[i]][currY + dcc[i]]){
					pq.add(new Node(i, j, totalCost));
					totalDistance[i][j] = totalCost;
					parentX[currX + rcc[i]] = currX;
					parentY[currY + dcc[i]] = currY;
				}
//				
//				for(int j = 0; j < 30; j++){
//					int totalCost = edges[i][j] + cost;
//					if(edges[i][j] != -1 && totalCost < totalDistance[i][j]){
//						pq.add(new Node(i, j, totalCost));
//						totalDistance[i][j] = totalCost;
//						parentX[i] = currX;
//						parentY[j] = currY;
//					}
//				}
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
	public Tile getTile() {
		return tile;
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

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	
	
}
