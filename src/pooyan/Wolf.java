package pooyan;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Wolf extends JLabel {
	private static final long serialVersionUID = 1L;
	public Wolf wolf = this;
	private final static String TAG = "Wolf : ";

	private PooyanApp pooyanApp;
	private Pooyan pooyan;

	private int floor = 0;
	private ImageIcon iconWolfM4, iconWolfM5, iconWalkWolfR, iconAttackStayWolf, iconAttackStayWolfR, iconAttackWolf1,
			iconAttackWolf2, iconFallingWolf;
	public int x = 0;
	public int y = -30;

	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isRightGround = false;
	public boolean isUp = false;
	public boolean isAttack = false;
	public boolean isDie = false;

	public boolean wolfStatus = true;
	public boolean isAttackBomb = false;

	public int rand;
	public Bomb bomb;
	private int randBomb;
	private int randBombLocation;

	public Wolf(PooyanApp pooyanApp, Pooyan pooyan) {
		this.pooyanApp = pooyanApp;
		this.pooyan = pooyan;
		bomb = new Bomb();
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWolfM5 = new ImageIcon("images/WolfMint5.png");
		iconWalkWolfR = new ImageIcon("images/walkWolfR.gif");
		iconAttackStayWolf = new ImageIcon("images/attackStayWolf.gif");
		iconAttackStayWolfR = new ImageIcon("images/attackStayWolfR.png");
		iconAttackWolf1 = new ImageIcon("images/attackWolf1.png");
		iconAttackWolf2 = new ImageIcon("images/attackWolf2.png");
		iconFallingWolf = new ImageIcon("images/fallingWolf1.png");

		setIcon(iconWolfM4);
		setSize(130, 130);
		setLocation(x, y);
		rand = (int) (Math.random() * 300) + 20;
		moveRight();
	}

	public void moveFall() {
//		System.out.println(TAG + "moveFall");

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					setIcon(iconWolfM4);
					bomb.x = x;
					randBomb = (int) (Math.random() * 4) + 1;
					randBombLocation = (int) (Math.random() * 400) + 50;
					while (isDown) {
						bomb.setLocation(bomb.x, y);
						if (randBomb == 2)
							pooyanApp.add(bomb);
						// bomb.setVisible(false);
						bomb.y = y;

						if (y > randBombLocation) {
							isAttackBomb = true;
							// bomb.setVisible(true);
							// setOpaque(false);
						}
						if (isAttackBomb) {
							y--;
							bomb.y = y;
							setLocation(x, y);
							bomb.x++;
							bomb.setLocation(bomb.x, bomb.y);
							y++;
						}
						if (y > 490) {
							isDown = false;
							isRightGround = true;
							pooyanApp.remove(bomb);
							wolf.moveRight();
							setIcon(iconWalkWolfR);
							if (wolfStatus == false) {
								isDie = true;
								pooyanApp.remove(wolf);

								// pooyanApp.wolves.remove(wolf);
							}
							break;
						}
						if (wolfStatus == false) {
							setIcon(iconFallingWolf);
							y++;
							setLocation(x, y);
							// break;
						}
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
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
//		System.out.println(TAG + "moveRight");
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				setIcon(iconWalkWolfR);

				while (isRight) {
					if (y == -30) { // 위에서 이동
						if (x >= rand) {
							isRight = false;
							moveFall();
							break;
						}
						x++;
						setLocation(x, y);

						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (y > 490 && wolfStatus == true) { // 밑에서 이동
						if (x > 540) {
							isRight = false;
							if (pooyanApp.floor < 4) {
								isUp = true;
								pooyanApp.floor = pooyanApp.floor + 1;
								floor = pooyanApp.floor;
								wolf.moveUP();
								break;
							} else {
								pooyanApp.remove(wolf);
								pooyanApp.wolves.remove(wolf);
								pooyanApp.repaint();
								pooyanApp.count--;
								System.out.println("늑대 " + pooyanApp.wolves.size());
								break;
							}

						}
						x++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	public void moveUP() {
		System.out.println(TAG + "moveUp");

		new Thread(new Runnable() {
			@Override
			public void run() {
				setIcon(iconAttackStayWolf);
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
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void attack() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				setIcon(iconAttackStayWolfR);
				while (isAttack) {
					try {
						Thread.sleep(5000); // 5초마다 공격
						setIcon(iconAttackWolf1);
						x = 500;
						setLocation(x, y);
						Thread.sleep(800); // 공격 모션 딜레이
						setIcon(iconAttackWolf2);
						// 플레이어 좌표가 울프의 근접공격 좌표와 같아지면 플레이어 죽음
						Thread.sleep(800); // 공격 모션 딜레이
						setIcon(iconAttackStayWolfR);
						x = 541;
						setLocation(x, y);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}).start();
	}
}