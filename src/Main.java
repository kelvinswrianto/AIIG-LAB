import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	JPanel gamePanel = new GamePanel(40, 30);
	public Main() {
		setSize(1057, 635);
		setTitle("Tower Defense");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(gamePanel);
		setVisible(true);
		addKeyListener((KeyListener) gamePanel);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}