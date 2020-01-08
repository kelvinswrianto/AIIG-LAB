import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.Arrays;

public class Tile {
	int weight[][] = new int[50][50];
	final int initial_weight = 1;
	public Tile() {
		for (int[] is : weight) {
			Arrays.fill(is, 1);
		}
	}
	
	public void drawTower(int i, int j, Graphics2D g, int unit){
		
		// draw range tower
		int dirx[] = {+1,+1,+0,+0,-1,-1,+1,-1};
		int diry[] = {+1,-1,+1,-1,+1,-1,+0,+0};
		for(int x=0; x<8; x++){
			g.setColor(new Color(255, 0, 255, 60));
			g.fillRect(unit * (i+dirx[x]), unit * (j+diry[x]), unit, unit);
			weight[i+dirx[x]][j+diry[x]] += 27-initial_weight;
		}
		int dirx2[] = {+2,+2,-2,-2,+0,+0,+2,-2,-1,-1,+1,+1};
		int diry2[] = {+1,-1,+1,-1,+2,-2,+0,+0,+2,-2,+2,-2};
		for(int x=0; x<12; x++){
			g.setColor(new Color(255, 0, 255, 60));
			g.fillRect(unit * (i+dirx2[x]), unit * (j+diry2[x]), unit, unit);
			weight[i+dirx2[x]][j+diry2[x]] += 27-initial_weight;
		}
		int dirx3[] = {+3,-3,+0,+0};
		int diry3[] = {+0,+0,+3,-3};
		for(int x=0; x<4; x++){
			g.setColor(new Color(255, 0, 255, 60));
			g.fillRect(unit * (i+dirx3[x]), unit * (j+diry3[x]), unit, unit);
			weight[i+dirx3[x]][j+diry3[x]] += 27-initial_weight;
		}
		
		g.setColor(new Color(255, 0, 255, 60));
		g.fillRect(unit * i, unit * j, unit, unit);
		int xPoints[] = {i*unit+0, i*unit+10, i*unit+20};
		int yPoints[] = {j*unit+20, j*unit, j*unit+20};
		g.setColor(Color.GREEN);
		g.fillPolygon(xPoints, yPoints, 3);

		// add weight tower
		weight[i][j] += 500-initial_weight;
	}
	
	public void drawTowerInfo(int i, int j, Graphics2D g){
		int xPoints[] = {i+0, i+9, i+20};
		int yPoints[] = {j+18, j-2, j+18};
		g.setColor(Color.GREEN);
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	public void drawNormalTile(int i, int j, Graphics2D g, int unit){
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawRect(unit * i, unit * j, unit, unit);
		weight[i][j] = 1;
	}
	
	public void drawSpawner(int i, int j, Graphics2D g, int unit, boolean visible){
		if(visible){
			int p = 2; // padding
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.RED);
			g.drawLine(unit * i + p, unit * j + p, unit * i + 20 - p, unit * j + 20 - p);
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.RED);
			g.drawLine(unit * i + 20 - p, unit * j + p, unit * i + p, unit * j + 20 - p);
			weight[i][j] = 50;
		}
		else{
			drawNormalTile(i, j, g, unit);
		}
	}
	
	public void drawSpawnerInfo(int i, int j, Graphics2D g){
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.RED);
		g.drawLine(i, j, i+15, j+15);
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.RED);
		g.drawLine(i, j+15, i+15, j);
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
	
	public void drawWall(int i, int j, Graphics2D g, int unit){
		g.setColor(Color.BLACK);
		g.fillRect(unit * i, unit * j, unit, unit);
		weight[i][j] = 99999;
	}

	public void drawWallInfo(int i, int j, Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(i, j, 20, 20);
	}
	
	public void drawHome(int i, int j, Graphics2D g, int unit){
		int xPoints[] = {i*unit, i*unit, i*unit+10, i*unit+20, i*unit+20};
		int yPoints[] = {j*unit+20, j*unit+10, j*unit, j*unit+10, j*unit+20};
		g.setColor(Color.BLUE);
		g.fillPolygon(xPoints, yPoints, 5);
		weight[i][j] = 0;
	}
	
	public void drawEnemy(int i, int j, Graphics2D g, int unit){
		int xPoints[] = {i*unit+10, i*unit, i*unit+19};
		int yPoints[] = {j*unit, j*unit+10, j*unit+19};
		g.setColor(Color.RED);
		g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(Color.RED);
		g.fillOval(i*unit+2, j*unit+2, 16, 16);
		weight[i][j] = 600;
	}
	
	public void drawEnemyInfo(int x, int y, Graphics2D g){
        g.setColor(Color.RED);
		g.fillOval(x, y, 23, 23);
	}
	
	public void drawEnemyBase(int i, int j, Graphics2D g){
		Color customColor = new Color(165, 42, 42);
		g.setColor(customColor);
		g.fillOval(i, j, 23, 23);
	}
	
	public int getWeight(int i, int j){
		return weight[i][j];
	}
	public void setWeight(int i, int j, int val){
		weight[i][j] = val;
	}
	public int[][] getWeightAll(){
		return weight;
	}
	
}
