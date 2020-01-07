import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class GameInfo extends JPanel{

	public GameInfo() {
		JLabel home = new JLabel("     Home");
		ImageIcon home_ic = new ImageIcon(new ImageIcon("src/home.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		home.setIcon(home_ic);
		home.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel enemy_base = new JLabel("     Enemy Base Color");
		ImageIcon enemy_base_ic = new ImageIcon(new ImageIcon("src/enemy_base.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		enemy_base.setIcon(enemy_base_ic);
		enemy_base.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel enemy_full = new JLabel("     Enemy in Full Health");
		ImageIcon enemy_full_ic = new ImageIcon(new ImageIcon("src/enemy_full.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		enemy_full.setIcon(enemy_full_ic);
		enemy_full.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel tower = new JLabel("     Tower");
		ImageIcon tower_ic = new ImageIcon(new ImageIcon("src/tower.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		tower.setIcon(tower_ic);
		tower.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel spawner = new JLabel("     Enemy Spawner");
		ImageIcon spawner_ic = new ImageIcon(new ImageIcon("src/spawner.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		spawner.setIcon(spawner_ic);
		spawner.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel wall = new JLabel("     Wall");
		ImageIcon wall_ic = new ImageIcon(new ImageIcon("src/wall.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		wall.setIcon(wall_ic);
		wall.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel bottom = new JPanel();
		
		top.setLayout(new GridLayout(6, 1));
		top.add(home);
		top.add(enemy_base);
		top.add(enemy_full);
		top.add(tower);
		top.add(spawner);
		top.add(wall);
		
		add(top);
		
	}

}
