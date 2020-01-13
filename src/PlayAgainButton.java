import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayAgainButton implements MouseListener{

	public PlayAgainButton() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		int x = e.getX();
		int y = e.getY();
		
		if(x >= 270 && x <= 720){
			if(y >= 380 && y <= 480){
				System.out.println("Click"); //MODIF DISINI KALO KLIK PLAY AGAIN BUTTON K BALIK K MENU GAME
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
