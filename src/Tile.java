import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.Arrays;

public class Tile {
	private int weight[][] = new int[50][50];
	private int enemy[][] = new int[50][50];  // untuk cek apakah ada enemy
	private int attack[][] = new int[50][50]; // attack multiplier untuk enemy
	private boolean placeable[][] = new boolean[50][50]; // untuk cek apakah tile tersebut bisa dipasang tower
	
	public Tile() {
		for (int[] is : weight) {
			Arrays.fill(is, 1);
		}
		for (int[] is : enemy) {
			Arrays.fill(is, 0);
		}
		for (int[] is : attack) {
			Arrays.fill(is, 0);
		}
		for (boolean[] is : placeable) {
			Arrays.fill(is, true);
		}
	}
	
	public boolean outOfBound(int i, int j){
		if(i <= 0 || j <= 0 || i >= 39 || j >= 29)
			return true;
		return false;
	}
	
	public void drawTower(int i, int j, Graphics2D g, int unit){
		g.setColor(new Color(255, 0, 255, 60));
		g.fillRect(unit * i, unit * j, unit, unit);
		int xPoints[] = {i*unit+0, i*unit+10, i*unit+20};
		int yPoints[] = {j*unit+20, j*unit, j*unit+20};
		g.setColor(Color.GREEN);
		g.fillPolygon(xPoints, yPoints, 3);

		  // add weight tower
		  weight[i][j] = 500;
		  placeable[i][j] = false;
		  int dirx[] = {+1,+1,+0,+0,-1,-1,+1,-1};
		  int diry[] = {+1,-1,+1,-1,+1,-1,+0,+0};
		  for(int x=0; x<8; x++){
			  if(outOfBound(i+dirx[x], j+diry[x])) 
				  continue;
			  g.setColor(new Color(255, 0, 255, 60)); 
			  g.fillRect(unit * (i+dirx[x]), unit * (j+diry[x]), unit, unit);
			  weight[i+dirx[x]][j+diry[x]] += 27;
			  placeable[i+dirx[x]][j+diry[x]] = false;
			  if(enemy[i+dirx[x]][j+diry[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx[x], j+diry[x], i, j, g, unit);
			  }
		  }
		  
		  int dirx2[] = {+2,+2,-2,-2,+0,+0,+2,-2,-1,-1,+1,+1};
		  int diry2[] = {+1,-1,+1,-1,+2,-2,+0,+0,+2,-2,+2,-2};
		  for(int x=0; x<12; x++){
			  if(outOfBound(i+dirx2[x], j+diry2[x]))
					  continue;
			  g.setColor(new Color(255, 0, 255, 60));
			  g.fillRect(unit * (i+dirx2[x]), unit * (j+diry2[x]), unit, unit);
			  weight[i+dirx2[x]][j+diry2[x]] += 27;
			  placeable[i+dirx2[x]][j+diry2[x]] = false;
			  if(enemy[i+dirx2[x]][j+diry2[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx2[x], j+diry2[x], i, j, g, unit);
			  }
		  }
		  
		  int dirx3[] = {+3,-3,+0,+0};
		  int diry3[] = {+0,+0,+3,-3};
		  for(int x=0; x<4; x++){
			  if(outOfBound(i+dirx3[x], j+diry3[x]))
				  continue;
			  g.setColor(new Color(255, 0, 255, 60));
			  g.fillRect(unit * (i+dirx3[x]), unit * (j+diry3[x]), unit, unit);
			  weight[i+dirx3[x]][j+diry3[x]] += 27;
			  placeable[i+dirx3[x]][j+diry3[x]] = false;
			  if(enemy[i+dirx3[x]][j+diry3[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx3[x], j+diry3[x], i, j, g, unit);
			  }
		  }
		  int dirx4[] = {0,0,+4,-4,+1,-1,+1,-1,+4,+4,-4,-4,+2,-2,+2,-2,+1,-1,+1,-1,+2,-2,+2,-2,+2,-2,+2,-2,+3,-3,+3,-3,+3,-3,+3,-3,+3,-3,+3,-3,+5,-5,+0,-0,+4,-4,+4,-4
				  		,+5,-5,+5,-5,+1,+1,-1,-1,+0,-0,-6,+6};
		  int diry4[] = {+4,-4,0,0,+4,+4,-4,-4,+1,-1,+1,-1,+4,+4,-4,-4,+3,+3,-3,-3,+3,+3,-3,-3,+2,+2,-2,-2,+1,+1,-1,-1,+2,+2,-2,-2,+3,+3,-3,-3,+0,+0,+5,-5,+2,+2,-2,-2
				  		,+1,+1,-1,-1,+5,-5,+5,-5,-6,+6,+0,-0};
		  
		  for(int x=0; x<dirx4.length; x++){
			  if(outOfBound(i+dirx4[x], j+diry4[x]))
				  continue;
			  g.setColor(new Color(255, 0, 255, 60));
			  g.fillRect(unit * (i+dirx4[x]), unit * (j+diry4[x]), unit, unit);
			  weight[i+dirx4[x]][j+diry4[x]] += 27;
			  placeable[i+dirx4[x]][j+diry4[x]] = false;
			  if(enemy[i+dirx4[x]][j+diry4[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx4[x], j+diry4[x], i, j, g, unit);
			  }
		  }
	}
	
	public void drawLine(int i1, int j1, int i2, int j2, Graphics2D g, int unit){
		g.setColor(Color.BLUE);
		g.drawLine(i1*unit+10, j1*unit+10, i2*unit+10, j2*unit+10);
		enemy[i2][j2] = 100;
		attack[i1][j1]++;
	}

	public void drawTowerLine(int i, int j, Graphics2D g, int unit){
		  int dirx[] = {+1,+1,+0,+0,-1,-1,+1,-1};
		  int diry[] = {+1,-1,+1,-1,+1,-1,+0,+0};
		  for(int x=0; x<8; x++){
			  if(outOfBound(i+dirx[x], j+diry[x])) 
				  continue;
			  if(enemy[i+dirx[x]][j+diry[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx[x], j+diry[x], i, j, g, unit);
			  }
		  }
		  
		  int dirx2[] = {+2,+2,-2,-2,+0,+0,+2,-2,-1,-1,+1,+1};
		  int diry2[] = {+1,-1,+1,-1,+2,-2,+0,+0,+2,-2,+2,-2};
		  for(int x=0; x<12; x++){
			  if(outOfBound(i+dirx2[x], j+diry2[x]))
					  continue;
			  if(enemy[i+dirx2[x]][j+diry2[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx2[x], j+diry2[x], i, j, g, unit);
			  }
		  }
		  
		  int dirx3[] = {+3,-3,+0,+0};
		  int diry3[] = {+0,+0,+3,-3};
		  for(int x=0; x<4; x++){
			  if(outOfBound(i+dirx3[x], j+diry3[x]))
				  continue;
			  if(enemy[i+dirx3[x]][j+diry3[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx3[x], j+diry3[x], i, j, g, unit);
			  }
		  }
		  int dirx4[] = {0,0,+4,-4,+1,-1,+1,-1,+4,+4,-4,-4,+2,-2,+2,-2,+1,-1,+1,-1,+2,-2,+2,-2,+2,-2,+2,-2,+3,-3,+3,-3,+3,-3,+3,-3,+3,-3,+3,-3,+5,-5,+0,-0,+4,-4,+4,-4
			  		,+5,-5,+5,-5,+1,+1,-1,-1,+0,-0,-6,+6};
		  int diry4[] = {+4,-4,0,0,+4,+4,-4,-4,+1,-1,+1,-1,+4,+4,-4,-4,+3,+3,-3,-3,+3,+3,-3,-3,+2,+2,-2,-2,+1,+1,-1,-1,+2,+2,-2,-2,+3,+3,-3,-3,+0,+0,+5,-5,+2,+2,-2,-2
				  		,+1,+1,-1,-1,+5,-5,+5,-5,-6,+6,+0,-0};
		  
		  for(int x=0; x<dirx4.length; x++){
			  if(outOfBound(i+dirx4[x], j+diry4[x]))
				  continue;
			  if(enemy[i+dirx4[x]][j+diry4[x]] == 1 && enemy[i][j] != 100){
				  drawLine(i+dirx4[x], j+diry4[x], i, j, g, unit);
			  }
		  }
	}
	
	public void drawNormalTile(int i, int j, Graphics2D g, int unit){
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawRect(unit * i, unit * j, unit, unit);
		weight[i][j] = 1;
		enemy[i][j] = 0;
		attack[i][j] = 0;
		placeable[i][j] = true;
	}
	
	public void drawNormalTileHovered(int i, int j, Graphics2D g, int unit, boolean placeable){
		if(placeable){
			g.setColor(new Color(0, 255, 0, 130));
		}
		else{
			g.setColor(new Color(0, 0, 0, 130));
		}
		
		g.fillRect(unit * i, unit * j, unit, unit);
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
			placeable[i][j] = false;
		}
		else{
			drawNormalTile(i, j, g, unit);
		}
	}
	
	public void drawWall(int i, int j, Graphics2D g, int unit){
		g.setColor(Color.BLACK);
		g.fillRect(unit * i, unit * j, unit, unit);
		weight[i][j] = 99999;
		placeable[i][j] = false;
	}

	public void drawHome(int i, int j, Graphics2D g, int unit){
		int xPoints[] = {i*unit, i*unit, i*unit+10, i*unit+20, i*unit+20};
		int yPoints[] = {j*unit+20, j*unit+10, j*unit, j*unit+10, j*unit+20};
		g.setColor(Color.BLUE);
		g.fillPolygon(xPoints, yPoints, 5);
		weight[i][j] = 0;
		placeable[i][j] = false;
	}
	
	public void drawEnemy(int i, int j, Graphics2D g, int unit, int health){
//		int xPoints[] = {i*unit+10, i*unit, i*unit+19};
//		int yPoints[] = {j*unit, j*unit+10, j*unit+19};
		g.setColor(new Color(165, 42, 42));
		g.fillOval(i*unit+2, j*unit+2, 16, 16);
		g.setColor(Color.RED);
		g.fillArc(i*unit+2, j*unit+2, 16, 16, 0, 360 *health / 100);
		weight[i][j] = 600;
		enemy[i][j] = 1;
		placeable[i][j] = false;
	}
	
	public boolean nearHome(int x, int y){
		if((x == 19 && y == 25) || (x == 20 && y == 24) || (x == 20 && y == 26) || (x == 21 && y == 25))
			return true;
		return false;
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
	public int getEnemy(int i, int j){
		return enemy[i][j];
	}
	public int getAttack(int i, int j){
		return attack[i][j];
	}
	
	public int [][] getAttackAll(){
		return attack;
	}
	
	public boolean isPlaceable(int i, int j){
		return placeable[i][j];
	}
}
