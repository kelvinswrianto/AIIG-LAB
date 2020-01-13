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
	int attack[][] = new int [100][100];
	boolean placeable;
	
	int spawnTime = 3000;
	int coins = 3;
	int lifes = 3;
	boolean isPaused = false;

	int currentScore = 0;
	boolean caseMouse = true;
	boolean winScreen = true;
	
	Vector<Pair> spawners = new Vector<>();
	Vector<Enemy> enemies = new Vector<>();
	Vector<Pair> towers = new Vector<>();
	
	private Thread gameThread;
	private Thread spawnThread;
	
	private boolean boot = true;
	private boolean running = true;
	
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
		while(running){
			System.out.print("");
			if(winScreen){
				Iterator<Enemy> iterator = enemies.iterator();
				while(iterator.hasNext()){
					try{
						Enemy enemy = iterator.next();
						if(attack[enemy.getX()][enemy.getY()] > 0 && !isPaused){
							enemy.updateHealth(attack[enemy.getX()][enemy.getY()]);
						}
						if(enemy.getHealth() <= 0 && !enemies.isEmpty()){
							iterator.remove();
							coins++;
							repaint();
						}
						else if(tile.nearHome(enemy.getX(), enemy.getY())){
							iterator.remove();
							lifes--;
						}
						else {
							enemy.setWeight(tile.getWeightAll());
							if(!isPaused) enemy.update(tile);
						}
					}
					catch(Exception e){
//						System.out.println("Something is wrong, not sure what, dont know dont care");
						break;
					}
				}
			}
			
			repaint();
			if(!isPaused){
				try {
					gameThread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
					if(spawnTime < 100){
						spawnTime = 100;
					}
				}
			}
		}
	}
	
	@Override
	public void paint(Graphics gg) {
		super.paint(gg);
		try{
			Graphics2D g = (Graphics2D) gg;
			currentScore = lifes*1000 + coins*100;
			if(lifes <= 0){
				caseMouse = false;
				winScreen = false;
				if(spawners.size() > 0 || enemies.size() > 0){
					wlm.draw(g, currentScore, false);
				}
				else if(spawners.size() == 0 && enemies.size() == 0){
					wlm.draw(g, currentScore, true);
				}
			}
			else if(lifes > 0 && spawners.size() == 0 && enemies.size() == 0 && !boot){
				caseMouse = false;
				winScreen = false;
				wlm.draw(g, currentScore, true);
			}
			else{
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
				
				Iterator <Pair> iteratorSpawner = spawners.iterator();
				while(iteratorSpawner.hasNext()){
					Pair sr = iteratorSpawner.next();
					tile.drawSpawner(sr.getFirst(), sr.getSecond(), g, unit, true);
				}
				
				Iterator <Pair> iteratorTower = towers.iterator();
				while(iteratorTower.hasNext()){
					Pair tower = iteratorTower.next();
					tile.drawTower(tower.getFirst(), tower.getSecond(), g, unit);
				}
				
		
				Iterator<Enemy> iterator = enemies.iterator();
				while(iterator.hasNext()){
					Enemy enemy = iterator.next();
					tile.drawEnemy(enemy.getX(), enemy.getY(), g, unit, enemy.getHealth());
				}
				
				Iterator <Pair> iteratorLine = towers.iterator();
				while(iteratorLine.hasNext()){
					Pair lines = iteratorLine.next();
					tile.drawTowerLine(lines.getFirst(), lines.getSecond(), g, unit);
				}
				this.attack = tile.getAttackAll();
		
				infoPanel.showInfo(g, coins, enemies, spawners, isPaused, lifes);
				this.boot = false;
			}
		}
		catch (Exception e){
			// do nothing
		}
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
		
		if(!caseMouse){
			int x = e.getX();
			int y = e.getY();
			
			if(x >= 270 && x <= 720){
				if(y >= 380 && y <= 480){
					spawnTime = 3000;
					coins = 3;
					lifes = 3;
					boot = true;
					spawners.clear();
					enemies.clear();
					towers.clear();
					caseMouse = true;
					winScreen = true;
				}
			}
		}
		else{
			int pixelClickX = e.getX()/unit;
			int pixelClickY = e.getY()/unit;
			
			if(coins <= 0 || isPaused) return;
			
			if(pixelClickX == 20 && pixelClickY == 25) return;
			
			if(!tile.outOfBound(pixelClickX, pixelClickY) && placeable == true){
				towers.add(new Pair(pixelClickX, pixelClickY));
				coins--;
				placeable = false;
			}
		}
		repaint();
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
		
		if(winScreen){
			int mouseX = e.getX()/unit;
			int mouseY = e.getY()/unit;
			
			if(mouseX == 20 && mouseY == 25){
				hoverX = mouseX;
				hoverY = mouseY;
				placeable = false;
				return;
			}
			
			if(!tile.outOfBound(mouseX, mouseY)){
				if(tile.isPlaceable(mouseX, mouseY)){
					hoverX = mouseX;
					hoverY = mouseY;
					placeable = true;
				}
				else{
					hoverX = mouseX;
					hoverY = mouseY;
					placeable = false;
				}
			}
			else{
				hoverX = -1;
				hoverY = -1;
				placeable = false;
			}
			repaint();
		}
	}
}

