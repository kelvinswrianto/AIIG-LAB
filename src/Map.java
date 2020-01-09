import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map extends JPanel implements MouseListener, KeyListener{
	char map[][] = new char[50][50];
	int w, h, unit; 
	int x=-1, y=-1;
	Tile tile = new Tile();
	
	Vector<Enemy> enemies = new Vector<>();
	
	Vector<Pair> towers = new Vector<>();
	Vector<Pair> spawners = new Vector<>();
	java.util.Map<Pair,Boolean> maps = new HashMap<Pair,Boolean>();
	
	private Thread gameThread;
	private boolean boot = true;
	private boolean running = true;
	private double FPS = 60;
	private double NANOSECOND_PER_FRAME = 1e9/FPS;
	private double SECOND_PER_FRAME = 1/FPS;
	private int x1 = 3, y1 = 3;
	private int velx = 1, vely = 1;
	public Map(int w, int h ) {
		this.w = w;
		this.h = h;
		this.unit = 20;
//		enemies.add(new Enemy(2, 2));
//		enemies.add(new Enemy(20, 7));
//		enemies.add(new Enemy(18, 6));
//		enemies.add(new Enemy(28, 2));
//		enemies.add(new Enemy(28, 20));
//		enemies.add(new Enemy(27, 2));
//		enemies.add(new Enemy(26, 2));
//		enemies.add(new Enemy(25, 2));
//		enemies.add(new Enemy(24, 2));
//		enemies.add(new Enemy(23, 2));
//		enemies.add(new Enemy(26, 3));
//		enemies.add(new Enemy(25, 3));
//		enemies.add(new Enemy(24, 3));
//		enemies.add(new Enemy(23, 3));
		
		towers.add(new Pair(19,20));
		towers.add(new Pair(25,20));
		towers.add(new Pair(16,24));
		towers.add(new Pair(26,24));
		addMouseListener(this);
		
		gameThread = new Thread(this::run);
		gameThread.start();
	}
	
	public void run(){
		int max = 25; 
		int min = 1;
		
		x1 = (int) ((Math.random() * ((max - min) + 1)) + min);
		y1 = (int) ((Math.random() * ((max - min) + 1)) + min);
		
		while(running){
			System.out.println("running");
			// jalan miring , test
			repaint();
			if(!boot){
				Random rand = new Random();
				int spawns = rand.nextInt(spawners.size());
				Pair elementAt = spawners.remove(spawns);
//				System.out.println("SPAWN! " + elementAt.getFirst() +" " + elementAt.getSecond());
				enemies.add(new Enemy(elementAt.getFirst(), elementAt.getSecond()));
			}
			
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(x1 > 40 || y1 > 40) break;
//			break;
		}
	}
	
	@Override
	public void paint(Graphics gg) {
		super.paint(gg);
		Graphics2D g = (Graphics2D) gg;
		
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
					Pair pairs = new Pair(i, j);
					if(boot){
						spawners.add(pairs);
//						maps.put(pairs, true);
					}
//					if(maps.get(pairs) == true) 
					tile.drawSpawner(i, j, g, unit, true);
//					else tile.drawSpawner(i, j, g, unit, false);
				}
				// draw home
				if(i == 20 && j == 25){
					tile.drawHome(i, j, g, unit);
				}
			}
		}

		if(this.x != -1 && this.y != -1){
			tile.drawTower(x/unit, y/unit, g, unit);
			this.x = -1;
			this.y = -1;
			System.out.println("running draw tower");
		}
		
		for (Pair tower : towers) {
			tile.drawTower(tower.getFirst(), tower.getSecond(), g, unit);
		}
		
		
		for (Enemy enemy : enemies) {
			enemy.setWeight(tile.getWeightAll());
			enemy.update(g, tile);
//			System.out.println(enemy.getX() + " " + enemy.getX());

		}

		this.boot = false;
		
//		for (Enemy enemy : enemies) {
//			enemy.setWeight(tile.getWeightAll());
//			enemy.update(g, tile);
//			
//		}
		
//		for(int i=0; i<40; i++){
//			for(int j=0; j<30; j++){
//				System.out.print(tile.getWeight(i, j) + " ");
//			}
//			System.out.println();
//		}

		tile.drawHome(41, 2, g, unit);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Home", 865, 55);
		
		tile.drawEnemyBase(818, 65, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy Base Color", 865, 82);

		tile.drawEnemyInfo(818, 93, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy in Full Health", 865, 110);
		
		tile.drawTowerInfo(821, 121, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Tower", 865, 135);
		
		tile.drawSpawnerInfo(823, 147, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy Spawner", 865, 160);
		
		tile.drawWallInfo(821, 167, g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Wall", 865, 183);

		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("HP:", 820, 225);
		//ATUR JUMLAH HATI ===============
		for (int i = 0; i < 3; i++) {
			tile.drawHeart(860+i*35,208,g);
		}
		//==================================

		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Coin:", 820, 260);
		tile.drawCoin(870,243,g);
		g.setColor(Color.BLACK);
		g.drawString("  X", 900, 260);
		
		//i ini contoh angka, ubah di sini sesuai jumlah coin nnt==============
		int i = 1;
		String coin = Integer.toString(i);
		g.drawString(coin, 921, 260);
		//=============================================
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Enemy:", 820, 295);
		tile.drawEnemyInfo(890, 278, g);
		g.setColor(Color.BLACK);
		g.drawString("  X", 917, 295);
		
		//i ini contoh angka, ubah di sini sesuai jumlah enemy nnt==============
		int enem = 5;
		String enemies = Integer.toString(enem);
		g.drawString(enemies, 937, 295);
		//=============================================	
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 19));
		g.drawString("Spawner:", 820, 333);
		tile.drawSpawnerInfo(900, 320, g);
		g.setColor(Color.BLACK);
		g.drawString("  X", 923, 333);
		
		//i ini contoh angka, ubah di sini sesuai jumlah spawners nnt==============
		int spaw = 87;
		String spawners = Integer.toString(spaw);
		g.drawString(spawners, 944, 333);
		//=============================================
		
		g.setColor(Color.BLACK);
		if(true){ //atur nnti pas P di tekan atau ngaknya disini==================
			g.setFont(new Font("Calibri", Font.BOLD, 27));
			g.drawString("Press P to Pause", 820, 405);
		}
		else{
			g.setFont(new Font("Calibri", Font.BOLD, 27));
			g.drawString("Press P to Play", 820, 405);
		}//================================================================

		g.setFont(new Font("Calibri", Font.BOLD, 27));
		g.drawString("Press Esc to Exit", 820, 460);
		
		g.setFont(new Font("Calibri", Font.BOLD, 37));
		g.drawString("Tower", 858, 537);
		g.setFont(new Font("Calibri", Font.BOLD, 37));
		g.drawString("Defense", 846, 587);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		towers.add(new Pair(e.getX()/unit, e.getY()/unit));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
