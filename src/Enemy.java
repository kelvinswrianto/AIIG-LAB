import java.awt.Graphics2D;

public class Enemy {
	private String name;
	private int x;
	private int y;
	private int velx = 1;
	private int vely = 1;
	
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
		
		if(x >= 25){
			velx = -1;
		}
		if(x <= 3){
			velx = +1;
		}
		if(y >= 25){
			vely = -1;
		}
		if(y <= 3){
			vely = +1;
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
