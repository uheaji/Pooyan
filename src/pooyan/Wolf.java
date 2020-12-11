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
	
	public ImageIcon iconWolfM4, iconWolfM5, iconWalkWolfR, 
	iconAttackStayWolf, iconAttackWolf1, iconAttackWolf2;

	public int x = 50;
	public int y = 50;

	public int count=0;
	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isUp = false;
	public boolean isAttack = false;

	public Random random = new Random();

	public Wolf() {
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWolfM5 = new ImageIcon("images/WolfMint5.png");
		iconWalkWolfR = new ImageIcon("images/walkWolfR.gif");
		iconAttackStayWolf = new ImageIcon("images/attackStayWolf.gif");
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
							main.floor = main.floor + 1;
							floor = main.floor;
							wolf.moveRight();
							setIcon(iconWalkWolfR);
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
					if (x > 540 && floor <= 4) {
						isRight = false;
						isUp = true;
//						main.floor = main.floor + 1;
//						floor = main.floor;
						wolf.moveUP();
						setIcon(iconAttackStayWolf);
						break;
					}
					 if (x > 540 && floor >= 5) {
						 x++;
						 setLocation(x, y);
						 try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
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
				int num = random.nextInt(1000);
				while (isAttack) {
					System.out.println(num);
					count++;
//					if (num % 7 == 0) {
					if (count == num) {
						setIcon(iconAttackWolf1);
						setLocation(510, getY());
						try {
							Thread.sleep( random.nextInt(2000)+800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						setIcon(iconAttackWolf2);
						setIcon(iconAttackWolf1);
						setIcon(iconAttackStayWolf);
						
						setLocation(x, getY());
						try {
							Thread.sleep( random.nextInt(2000)+800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
//						break;
						num = random.nextInt(10000);
						count=0;
					}
				}
			}
		}).start();
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (isAttack) {
//					if ((int) (Math.random() * 1000) == 5) {
//						setIcon(iconAttackWolf1);
//						setLocation(510, getY());
//					}else {
//						try {
//							Thread.sleep((int) (Math.random() * 1000) );
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//
//			}
//		}).start();
//	}
	}
}
