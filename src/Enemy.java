import java.awt.Graphics2D;

public class Enemy {
	private int x;
	private int y;
	private int velx = 1;
	private int vely = 1;
	
	private int[][] weight = new int[200][200];
	
	private int unit = 20;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update(Graphics2D g){
		
		if(x >= 37){
			velx = -1;
		}
		if(x <= 2){
			velx = +1;
		}
		if(y >= 28){
			vely = -1;
		}
		if(y <= 2){
			vely = +1;
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

	public void setWeight(int[][] weight){
		this.weight = weight;
	}
	
}
