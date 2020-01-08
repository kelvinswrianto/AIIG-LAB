import java.awt.Graphics2D;

public class Enemy {
	private int x = 3;
	private int y = 3;
	private int velx = 1;
	private int vely = 1;
	
	private int[][] weight = new int[200][200];
	
	private int unit = 20;
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update(Graphics2D g, Tile tile){
		Dijkstra d = new Dijkstra(x, y, weight);
//		d.showPath(20, 25);
		int dir = 1;
		if(dir == 1){
			x++;
		}
		if(dir == 2){
			x--;
		}
		if(dir == 3){
			y++;
		}
		if(dir == 4){
			y--;
		}
		tile.drawTower(x, y, g, unit);
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
