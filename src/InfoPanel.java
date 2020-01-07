import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel{

	private int level;
	private int scoreValue;
	private int treasureValue;
	
	public InfoPanel(int level) {
		
		this.level = level;
		
		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel bot = new JPanel();
		
		JLabel title = new JLabel("RUZ SECOND");
		top.add(title);
		
		JLabel score = new JLabel("Score: " + scoreValue);
		mid.setLayout(new GridLayout(5, 1, 10, 10));
		mid.add(score);
		
		JLabel treasure = new JLabel("Treasure Value: \n" + treasureValue);
		JButton pauseBtn = new JButton("Pause");
		bot.setLayout(new GridLayout(2, 1, 5, 5));
		bot.add(treasure);
		bot.add(pauseBtn);
		
		setLayout(new BorderLayout());
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
	}

}
