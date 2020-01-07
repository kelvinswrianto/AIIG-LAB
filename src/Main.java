import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	JPanel map = new Map(40, 30);
	JPanel info = new GameInfo();
	public Main() {
		setSize(1200, 670);
		setTitle("Tower Defense");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = GridBagConstraints.RELATIVE;
		add(map, c);
		
		c.weightx = 0.3;
		c.gridwidth = GridBagConstraints.REMAINDER;
		add(info, c);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
