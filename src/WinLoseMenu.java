import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class WinLoseMenu {

	public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.PLAIN, 70));
		g.drawString("YOUR SCORE : ", 210, 100);
		
		//========================cara validasi score
		int totalScore = 11700;
		String score = Integer.toString(totalScore);
		g.drawString(score, 620, 100);
		//==================================
		
		g.drawString("YOUR WIN", 340, 210); //validasi aj utk tampilan ini kalo menang ini keluar atau ngk
		
		g.drawString("GAME OVER", 315, 350);//validasi aj utk tampilan ini kalo menang ini keluar atau ngk
		
		g.setColor(Color.BLACK);
		g.fillRect(270, 380, 450, 100);
		g.setColor(Color.WHITE);
		g.drawString("PLAY AGAIN", 325, 450);
	}
	
	public WinLoseMenu() {
		// TODO Auto-generated constructor stub
	}

}
