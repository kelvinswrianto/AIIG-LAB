import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class GameInfo extends JPanel{

	Tile tile = new Tile();
	
	public GameInfo() {
		JLabel home = new JLabel("     Home");
		ImageIcon home_ic = new ImageIcon(new ImageIcon("src/home.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		home.setIcon(home_ic);
		home.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel enemy_base = new JLabel("     Enemy Base Color");
		ImageIcon enemy_base_ic = new ImageIcon(new ImageIcon("src/enemy_base.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		enemy_base.setIcon(enemy_base_ic);
		enemy_base.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel enemy_full = new JLabel("     Enemy in Full Health");
		ImageIcon enemy_full_ic = new ImageIcon(new ImageIcon("src/enemy_full.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		enemy_full.setIcon(enemy_full_ic);
		enemy_full.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel tower = new JLabel("     Tower");
		ImageIcon tower_ic = new ImageIcon(new ImageIcon("src/tower.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		tower.setIcon(tower_ic);
		tower.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel spawner = new JLabel("     Enemy Spawner");
		ImageIcon spawner_ic = new ImageIcon(new ImageIcon("src/spawner.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		spawner.setIcon(spawner_ic);
		spawner.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel wall = new JLabel("     Wall");
		ImageIcon wall_ic = new ImageIcon(new ImageIcon("src/wall.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		wall.setIcon(wall_ic);
		wall.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel hp = new JLabel("HP : ");
		//ImageIcon wwall_ic = new ImageIcon(new ImageIcon(tile.drawHeart(g)).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		//hp.setIcon(wwall_ic);
		hp.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel coin = new JLabel("Coin : ");
		//ImageIcon wall_ic = new ImageIcon(new ImageIcon("src/wall.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		//wall.setIcon(wall_ic);
		coin.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel enemy = new JLabel("Enemy : ");
		//ImageIcon wall_ic = new ImageIcon(new ImageIcon("src/wall.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		//wall.setIcon(wall_ic);
		enemy.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JLabel spawn = new JLabel("Spawn : ");
		//ImageIcon wall_ic = new ImageIcon(new ImageIcon("src/wall.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		//wall.setIcon(wall_ic);
		spawn.setFont(new Font("Calibri", Font.PLAIN, 19));
		
		JPanel wrapper = new JPanel();
		JPanel top = new JPanel();
		JPanel topinside1 = new JPanel();
		JPanel topinside2 = new JPanel();
		JPanel mid = new JPanel();
		JPanel bottom = new JPanel();
		
		wrapper.setLayout(new GridLayout(3, 1,0,0));
		
		//TOP
		top.setLayout(new GridLayout(2, 1,0,15));

		topinside1.setLayout(new GridLayout(6, 1));
		topinside1.add(home);
		topinside1.add(enemy_base);
		topinside1.add(enemy_full);
		topinside1.add(tower);
		topinside1.add(spawner);
		topinside1.add(wall);
		add(topinside1);

		topinside2.setLayout(new GridLayout(4, 1));
		topinside2.add(hp);
		topinside2.add(coin);
		topinside2.add(enemy);
		topinside2.add(spawn);
		add(topinside2);
		
		top.add(topinside1);
		top.add(topinside2);
		add(top);
		
		//MID
		mid.setLayout(new GridLayout(2, 1,0,0));
		
		JLabel pause = new JLabel("Press P to Pause");
		pause.setFont(new Font("Calibri", Font.PLAIN, 30));
		
		JLabel exit = new JLabel("Press Esc to Exit");
		exit.setFont(new Font("Calibri", Font.PLAIN, 30));
		
		mid.add(pause);
		mid.add(exit);
		add(mid);
		
		wrapper.add(top);
		wrapper.add(mid);
		add(wrapper);
	}

}
