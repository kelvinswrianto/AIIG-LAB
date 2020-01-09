import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Enemy {
	private int x = 3;
	private int y = 3;
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
		Dijkstra d = new Dijkstra(x, y, weight);
		int dir = d.showPath(20, 25, x, y);
		System.out.println(dir);
		if(dir == 1){
			x--;
		}
		if(dir == 2){
			x++;
		}
		if(dir == 3){
			y--;
		}
		if(dir == 4){
			y++;
		}
		
		tile.drawEnemy(x, y, g, unit);
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
