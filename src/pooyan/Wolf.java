package pooyan;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Wolf extends JLabel {

	public Wolf wolf = this;
	public final static String TAG = "Wolf : ";

	public Main main;
	public int floor = 0;

	public ImageIcon iconWolfM4, iconWolfM5, iconWalkWolfR1, iconAttackStayWolf, iconAttackWolf1, iconAttackWolf2;

	public int x = 50;
	public int y = 50;

	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isUp = false;
	public boolean isAttack = false;

	public Random random = new Random();

	public Wolf() {
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWolfM5 = new ImageIcon("images/WolfMint5.png");
		iconWalkWolfR1 = new ImageIcon("images/walkWolfR1.png");
		iconAttackStayWolf = new ImageIcon("images/attackStayWolfL.png");
		iconAttackWolf1 = new ImageIcon("images/attackWolf1.png");
		iconAttackWolf2 = new ImageIcon("images/attackWolf2.png");
		setIcon(iconWolfM4);
		setSize(130, 130);
		setLocation(x, y);

	}

	public void moveFall() { // Ç³¼±Å¸°í ¶¥À¸·Î ³»·Á°¨
		System.out.println(TAG + "moveFall");

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while (isDown) {
						if (y > 490) {
							isDown = false;
							isRight = true;
							wolf.moveRight();
							setIcon(iconWalkWolfR1);
							break;
						}
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	public void moveRight() {
		System.out.println(TAG + "moveRight");

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRight) {
					if (x > 540) {
						isRight = false;
						isUp = true;
						main.floor = main.floor + 1;
						floor = main.floor;
						wolf.moveUP();
						setIcon(iconAttackStayWolf);
						break;
					}
					x++;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void moveUP() {
		System.out.println(TAG + "moveUp");

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isUp) {
//					if (y < 405 + y1) {
//						y1 += 50;
//						isUp = false;
//					} 
					if (floor == 1 && y < 400) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 2 && y < 320) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 3 && y < 230) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 4 && y < 150) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					y--;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void attack() {
		System.out.println(TAG + "attack");

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isAttack) {
//					random = new Random();
					random.nextInt(1000);
					int num = random.nextInt() + 1;
					System.out.println(num);

					if (num % 7 == 0) {
						setIcon(iconAttackWolf1);
						setLocation(510, getY());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						setIcon(iconAttackWolf2);
						break;
						
					}
				}
			}
		}).start();
	}

}
