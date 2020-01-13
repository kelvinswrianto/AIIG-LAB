import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JPanel;

public class Map{
	int w, h, unit;
	Tile tile;
	Graphics2D g2;
	Vector<Pair> spawners = new Vector<>();
	public Map(int w, int h){
		this.w = w;
		this.h = h;
		this.unit = 20;
	}
	
	public void drawMap(Tile tile, Graphics2D g, boolean boot){
		// draw basic map
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				// draw wall
				if(i == 0 || j == 0 || i == 39 || j == 29){
					tile.drawWall(i, j, g, unit);
				}
				else{ // draw empty tile
					tile.drawNormalTile(i, j, g, unit);
				}
				// draw spawner
				if(i == 1 && j >= 1 && j <= 28 || i == 38 && j >= 1 && j <= 28 || j == 1 && i >= 1 && i <= 38){
					if(boot){
						Pair pairs = new Pair(i, j);
						spawners.add(pairs);
					} 
				}
				// draw home
				if(i == 20 && j == 25){
					tile.drawHome(i, j, g, unit);
				}
			}
		}
	}
	public Vector<Pair> getSpawner(){
		return spawners;
	}
}