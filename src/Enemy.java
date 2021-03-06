import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Enemy {
	private int x = 3;
	private int y = 3;
	private int dir;
	private int velx = 1;
	private int vely = 1;
	private int health = 100;
	private int time = 500;
	private int attackMultiplier;
	private long SECOND_PER_FRAME = 1000/60;
	
	private Thread enemy;
	
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

	public void update(Tile tile){
		Dijkstra d = new Dijkstra(x, y, weight);
		this.dir = d.showPath(20, 25, x, y);
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
//		tile.drawEnemy(x, y, g, unit, this.health);
	}
	
	public void updateHealth(int attackMultiplier){
		this.attackMultiplier = attackMultiplier;
		// karena enemy berpindah (repaint()) tiap 500 ms
		// dan darah enemy berkurang 1 tiap 16ms
		// maka tiap kali perpindahan darah enemy akan berkurang 500/16 = 31.25 HP dikali dengan attackMultiplier
		if(health > 0){
			health -= (attackMultiplier*(500/16));
			if(health < 0){
				health = 0;
			}
		}
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
	public int getHealth(){
		return health;
	}
	
}
