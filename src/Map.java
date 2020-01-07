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
import javax.swing.JPanel;

public class Map extends JPanel{
	char map[][] = new char[50][50];
	int w, h, unit; 
	Tile tile = new Tile();
	
	public Map(int w, int h ) {
		this.w = w;
		this.h = h;
		this.unit = 20;
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
					tile.drawSpawner(i, j, g, unit, true);
				}
				// draw home
				if(i == 20 && j == 25){
					tile.drawHome(i, j, g, unit);
				}
				if(i == 5 && j == 5) tile.drawTower(i, j, g, unit); // example enemy
			}
		}
		
		for(int i=0; i<40; i++){
			for(int j=0; j<30; j++){
				System.out.print(tile.getWeight(i, j) + " ");
			}
			System.out.println();
		}
	}
}
