package pooyan;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel{
	private ImageIcon icBomb;
	public boolean isIn = false;
	public int x=0;
	public int y=0;
	public PooyanApp pooyanApp;
	public Pooyan pooyan;
	public Wolf wolf;
	public boolean isKill = false;
	public Bomb() {
		icBomb = new ImageIcon("images/bomb.png");
		setIcon(icBomb);
		setSize(20,20);
		setLocation(0,0);
	}
	
	
	
		
	
	/*
	 * if(pooyan.x == wolf.bomb.x
	 * 
	 * */
}