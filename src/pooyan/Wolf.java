package pooyan;

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
	private ImageIcon iconWolfM4, iconWolfM5,  iconWalkWolfR, iconAttackStayWolf, iconAttackStayWolfR, iconAttackWolf1,
			iconAttackWolf2, iconFallingWolf;
	public int x = 0;
	public int y = -30;
	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isRightGround = false;
	public boolean isUp = false;
	public boolean isAttack = false;
	public boolean isAttackColision = false;
	public boolean isDie = false;
	public Bomb bomb;
	public boolean wolfStatus = true;
	public boolean isAttackBomb = false;

	public int dieCount;
	public int rand;
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

	
	public void bombAttack() {
		if(isAttackBomb == false) {
			new Thread(new Runnable() {		
				@Override
				public void run() {
					isAttackBomb = true;
					while (isAttackBomb) {
						bomb.x+=2;
						bomb.setLocation(bomb.x, bomb.y);
//						if(bomb.x >= 400) {
//							bomb.y+=0.2;
//							bomb.setLocation(bomb.x, bomb.y);
//							try {
//								Thread.sleep(1);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//						}
						if(bomb.x >= 600) {
							pooyanApp.remove(bomb);
						}
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
	}
	
	public void moveFall() {
//		System.out.println(TAG + "moveFall");

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					setIcon(iconWolfM4);
					bomb.x = x+20;
					bomb.y = y+60;
					bomb.setLocation(bomb.x, bomb.y);
					randBomb = (int) (Math.random() * 4) + 1;
					if (randBomb == 2) {
						pooyanApp.add(bomb);
						bomb.setOpaque(false);
					}
					while (isDown) {
						bomb.y++;
						//bomb.y = y;
						bomb.setLocation(bomb.x,bomb.y);
						if(pooyan.y-30 == bomb.y) {
							bombAttack();
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
								break;

							}
							break;
						}
						if (wolfStatus == false) {
							setIcon(iconFallingWolf);
							y++;
							setLocation(x, y);

						}
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(5);
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
						if(floor == 1) {
							if(pooyan.y == wolf.y+14 && pooyan.x == wolf.x-14) {
								System.out.println("충돌");
								//isAttackColision = true;
								pooyan.die();
							}
						} else if (floor == 2) {
							if((pooyan.y >= 322 && pooyan.y <= 363) && wolf.y == 319 && pooyan.x == wolf.x-14) {
								System.out.println("충돌2");
								//pooyan.isCollisionWolf = true;
								pooyan.die();
							}
							
						}  else if (floor == 3) {
							if((pooyan.y >= 239 && pooyan.y <= 274) && wolf.y == 229 && pooyan.x == wolf.x-14) {
								System.out.println("충돌3");
								//pooyan.isCollisionWolf = true;
								pooyan.die();
							}
							
						} else if (floor == 4) {
							if((pooyan.y >= 151 && pooyan.y <= 184) && wolf.y == 149 && pooyan.x == wolf.x-14) {
								System.out.println("충돌4");
								//pooyan.isCollisionWolf = true;
								pooyan.die();
							}
							System.out.println("wolf.y: "+wolf.y);
						}
						Thread.sleep(500); // 공격 모션 딜레이
						setIcon(iconAttackWolf2);
						// 플레이어 좌표가 울프의 근접공격 좌표와 같아지면 플레이어 죽음
						
						
						
						Thread.sleep(500); // 공격 모션 딜레이
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