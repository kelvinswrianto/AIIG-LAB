import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	JPanel map = new Map(40, 30);
	//JPanel info = new GameInfo();
	public Main() {
		setSize(1057, 670);
		setTitle("Tower Defense");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(map);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}


//setLayout(new GridBagLayout());
//
//GridBagConstraints c = new GridBagConstraints();
//
//c.fill = GridBagConstraints.BOTH;
//c.weightx = 1.0;
//c.weighty = 1.0;
//c.gridwidth = GridBagConstraints.RELATIVE;
//add(map, c);
//
//c.weightx = 0.01;
//c.insets = new Insets(30, 0, 0, 25);
//c.gridwidth = GridBagConstraints.REMAINDER;
//add(info, c);
//setVisible(true);