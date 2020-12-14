package pooyan;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Meat extends JLabel{
	private ImageIcon icMeat;
	public boolean isIn = false;
	public int x=0;
	public int y=0;
	public Meat() {
		icMeat = new ImageIcon("images/meat.png");
		setIcon(icMeat);
		setSize(160,90);
		setLocation(496,70);
	}
}
