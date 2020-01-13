import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class WinLoseMenu {

	public void draw(Graphics2D g, int currentScore, boolean win){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 70));
		g.drawString("YOUR SCORE : ", 210, 100);
		
		String score = Integer.toString(currentScore);
		g.drawString(score, 620, 100);
		
		if(win == false){
			g.drawString("GAME OVER", 315, 350);
		}
		else{
			g.drawString("YOU WIN", 360, 210);
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(270, 380, 450, 100);
		g.setColor(Color.WHITE);
		g.drawString("PLAY AGAIN", 325, 450);
	}
	
	public WinLoseMenu() {
		// TODO Auto-generated constructor stub
	}

}
