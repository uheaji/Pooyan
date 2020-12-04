package pooyan;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Wolf extends JLabel {

	public Wolf wolf = this;
	public final static String TAG = "Wolf : ";

	public ImageIcon iconWolfM4, iconWalkWolfR1;
	public int x = 50;
	public int y = 50;

	public boolean isDown = false;
	public boolean isRight = false;

	public Wolf() {
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWalkWolfR1 = new ImageIcon("images/walkWolfR1.png");
		setIcon(iconWolfM4);
		setSize(150, 150);
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

//		if (isRight== false) {
		new Thread(new Runnable() {
			@Override
			public void run() {
//					isRight = true;
				while (isRight) {
					if (x > 500) {
						isRight = false;
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

}
//}
