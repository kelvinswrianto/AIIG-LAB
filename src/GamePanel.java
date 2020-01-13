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
import java.awt.event.MouseMotionListener;
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
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener{

	Map map;
	
	int w, h, unit; 
	Tile tile = new Tile();
	WinLoseMenu wlm = new WinLoseMenu();
	InfoPanel infoPanel;
	
	int hoverX = -1;
	int hoverY = -1;
	boolean placeable;
	
	int spawnTime = 3000;
	int coins = 3;
	int spawnerCount = 92;
	int lifes = 3;
	boolean isPaused = false;
	
	
	Vector<Pair> spawners = new Vector<>();
	Vector<Enemy> enemies = new Vector<>();
	Vector<Pair> towers = new Vector<>();
	
	private Thread gameThread;
	private Thread spawnThread;
	private Thread enemyThread;
	
	private boolean boot = true;
	private boolean running = true;
	
	private int x1 = 3, y1 = 3;
	
	public GamePanel(int w, int h ) {
		this.w = w;
		this.h = h;
		this.unit = 20;
		map = new Map(w, h);
		infoPanel = new InfoPanel(tile, unit);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		gameThread = new Thread(this::run);
		gameThread.start();
		spawnThread = new Thread(this::runSpawner);
		spawnThread.start();
	}
	
	public void run(){
		int max = 25; 
		int min = 1;
		
		x1 = (int) ((Math.random() * ((max - min) + 1)) + min);
		y1 = (int) ((Math.random() * ((max - min) + 1)) + min);
		
		while(running){
			repaint();
			if(!isPaused){
				try {
					gameThread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(x1 > 40 || y1 > 40) break;
			}
		}
	}
	
	public void runSpawner(){
		
		while(true){
			System.out.print("");
			if(!boot && !spawners.isEmpty() && !isPaused){
				Random rand = new Random();
				int spawns = rand.nextInt(spawners.size());
				Pair elementAt = spawners.remove(spawns);
				enemies.add(new Enemy(elementAt.getFirst(), elementAt.getSecond()));
				
				try {
					spawnThread.sleep(spawnTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(spawnTime > 100){
					spawnTime -= 100;
				}
			}
			
		}
	}
	
	@Override
	public void paint(Graphics gg) {
		super.paint(gg);
		Graphics2D g = (Graphics2D) gg;
		
		//INI SYNTAX BUAT TAMPILAN WIN LOSE GAMENYA
		//==========================================
		//this.addMouseListener(new PlayAgainButton()); -> ini buat mouse listener ketika button play again diketik, modif functionnya d kelas playagainbutton
		//wlm.draw(g);
		//==========================================
		
		map.drawMap(tile, g, boot);
		
		if(boot){
			spawners = map.getSpawner();
		}

		if(placeable == true){
			tile.drawNormalTileHovered(hoverX, hoverY, g, unit, placeable);
		}
		else{
			if(hoverX != -1 && hoverY != -1){
				tile.drawNormalTileHovered(hoverX, hoverY, g, unit, placeable);
			}
		}
		
		for (Pair sr : spawners) {
			tile.drawSpawner(sr.getFirst(), sr.getSecond(), g, unit, true);
		}
		
		for (Pair tower : towers) {
			tile.drawTower(tower.getFirst(), tower.getSecond(), g, unit);
		}
		
		for (Enemy enemy : enemies) {
			enemy.setWeight(tile.getWeightAll());
			if(!isPaused)enemy.update(g, tile);
			else tile.drawEnemy(enemy.getX(), enemy.getY(), g, unit, (enemy.getHealth() <= 50));
		}
		
		for (Pair tower : towers) {
			tile.drawTowerLine(tower.getFirst(), tower.getSecond(), g, unit);
		}
		
		
		Iterator<Enemy> iterator = enemies.iterator();
		while(iterator.hasNext()){
			Enemy enemy = iterator.next();
			if(tile.getAttack(enemy.getX(), enemy.getY()) > 0 && !isPaused){
				enemy.updateHealth(tile.getAttack(enemy.getX(), enemy.getY()));
			}
			if(enemy.getHealth() <= 0 && !enemies.isEmpty()){
				iterator.remove();
				coins++;
			}
			if(tile.nearHome(enemy.getX(), enemy.getY())){
				iterator.remove();
				lifes--;
			}
		}
//		for(int i=0; i<40; i++){
//			for(int j=0; j<30; j++){
//				System.out.print(tile.getAttack(i, j) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println();

		infoPanel.showInfo(g, coins, enemies, spawners, isPaused, lifes);
		this.boot = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_P){
			isPaused = !isPaused;
		}
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
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
		
		int pixelClickX = e.getX()/unit;
		int pixelClickY = e.getY()/unit;
		
		if(coins <= 0 || isPaused) return;
		
		if(pixelClickX == 20 && pixelClickY == 25) return;
		
		if(!tile.outOfBound(pixelClickX, pixelClickY)){
//			if(!spawners.contains(new Pair(pixelClickX, pixelClickY)))
			for (Pair sr : spawners) {
				if(pixelClickX == sr.getFirst() && pixelClickY == sr.getSecond())
					return;
			}
			for (Pair tower : towers) {
				if(pixelClickX == tower.getFirst() && pixelClickY == tower.getSecond())
					return;
			}
			towers.add(new Pair(e.getX()/unit, e.getY()/unit));
			coins--;
		}
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int mouseX = e.getX()/unit;
		int mouseY = e.getY()/unit;
		
		if(mouseX == 20 && mouseY == 25){
			hoverX = mouseX;
			hoverY = mouseY;
			placeable = false;
			return;
		}
		
		if(!tile.outOfBound(mouseX, mouseY)){
//			if(!spawners.contains(new Pair(pixelClickX, pixelClickY)))
			for (Pair sr : spawners) {
				if(mouseX == sr.getFirst() && mouseY == sr.getSecond()){
					hoverX = mouseX;
					hoverY = mouseY;
					placeable = false;
					return;
				}
			}
			for (Pair tower : towers) {
				if(mouseX == tower.getFirst() && mouseY == tower.getSecond()){
					hoverX = mouseX;
					hoverY = mouseY;
					placeable = false;
					return;
				}
					
			}
			hoverX = mouseX;
			hoverY = mouseY;
			placeable = true;
		}
		else{
			hoverX = -1;
			hoverY = -1;
			placeable = false;
		}
	}
}

