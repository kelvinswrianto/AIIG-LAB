import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Vector;

public class InfoPanel {
	
	private Tile tile;
	private int unit;
	
	public InfoPanel(Tile tile, int unit){
		this.tile = tile;
		this.unit = unit;
	}
	
	public void drawHome(int i, int j, Graphics2D g, int unit){
		int xPoints[] = {i*unit, i*unit, i*unit+10, i*unit+20, i*unit+20};
		int yPoints[] = {j*unit+20, j*unit+10, j*unit, j*unit+10, j*unit+20};
		g.setColor(Color.BLUE);
		g.fillPolygon(xPoints, yPoints, 5);
	}
	
	public void drawEnemyBase(int i, int j, Graphics2D g){
		Color customColor = new Color(165, 42, 42);
		g.setColor(customColor);
		g.fillOval(i, j, 23, 23);
	}
	
	public void drawEnemy(int x, int y, Graphics2D g){
        g.setColor(Color.RED);
		g.fillOval(x, y, 23, 23);
	}
	
	public void drawTower(int i, int j, Graphics2D g){
		int xPoints[] = {i+0, i+9, i+20};
		int yPoints[] = {j+18, j-2, j+18};
		g.setColor(Color.GREEN);
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	public void drawSpawner(int i, int j, Graphics2D g){
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.RED);
		g.drawLine(i, j, i+15, j+15);
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.RED);
		g.drawLine(i, j+15, i+15, j);
	}
	
	public void drawWall(int i, int j, Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(i, j, 20, 20);
	}
	
	public void drawHeart(int x, int y, Graphics2D g2) {
        g2.setColor(Color.RED);
		g2.fillArc(x, y, 15, 15, 180, -180);

        g2.setColor(Color.RED);
		g2.fillArc(x+12, y, 15, 15, 180, -180);
        
        g2.setColor(Color.RED);
        g2.fillPolygon(new int[]{x,x+14,x+26}, new int[]{y+7,y+20,y+7}, 3);
	}
	
	public void drawCoin(int x, int y, Graphics2D g){
        g.setColor(Color.YELLOW);
		g.fillOval(x, y, 23, 23);
	}
	
	public void showInfo(Graphics2D g, int coins, Vector<Enemy> enemies, Vector<Pair> spawners, boolean isPaused, int lifes ){
		
		drawHome(41, 2, g, unit);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Home", 865, 55);
		
		drawEnemyBase(818, 65, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy Base Color", 865, 82);

		drawEnemy(818, 93, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy in Full Health", 865, 110);
		
		drawTower(821, 121, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Tower", 865, 135);
		
		drawSpawner(823, 147, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy Spawner", 865, 160);
		
		drawWall(821, 167, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Wall", 865, 183);
		
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("HP:", 820, 225);
		
		for (int i = 0; i < lifes; i++) {
			drawHeart(860+i*35,208,g);
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Coin:", 820, 260);
		drawCoin(870,243,g);
		g.setColor(Color.BLACK);
		g.drawString("  X", 900, 260);
		
		String coin = Integer.toString(coins);
		g.drawString(coin, 921, 260);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy:", 820, 295);
		drawEnemy(890, 278, g);
		g.setColor(Color.BLACK);
		g.drawString("  X", 917, 295);
		
		int enemySizeInt = enemies.size();
		String enemySizeStr = Integer.toString(enemySizeInt);
		g.drawString(enemySizeStr, 937, 295);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Spawner:", 820, 333);
		drawSpawner(900, 320, g);
		g.setColor(Color.BLACK);
		g.drawString("  X", 923, 333);
		
		String spaw = Integer.toString(spawners.size());
		g.drawString(spaw, 944, 333);
		
		g.setColor(Color.BLACK);
		if(isPaused){
			g.setFont(new Font("Calibri", Font.BOLD, 27));
			g.drawString("Press P to Pause", 820, 405);
		}
		else{
			g.setFont(new Font("Calibri", Font.BOLD, 27));
			g.drawString("Press P to Play", 820, 405);
		}

		g.setFont(new Font("Calibri", Font.BOLD, 27));
		g.drawString("Press Esc to Exit", 820, 460);
		
		g.setFont(new Font("Calibri", Font.BOLD, 37));
		g.drawString("Tower", 858, 537);
		g.setFont(new Font("Calibri", Font.BOLD, 37));
		g.drawString("Defense", 846, 587);
	}
}
