package pooyan;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Arrow extends JLabel{
	private ImageIcon icBow;
	public boolean isIn = false;
	public boolean isRemove = false;
	public int x = 0;
	public int y = 0;
	
	public Arrow() {
		icBow = new ImageIcon("images/bow.png");
		setIcon(icBow);
		setSize(60, 5);
		setLocation(0,0);
	}
}
