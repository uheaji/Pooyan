package pooyan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Pooyan extends JPanel {
	public Pooyan pooyan = this;
	public ArrayList<Arrow> listArrow;
	private Meat meat;
	private final static String TAG = "Pooyan : ";
	private Color transparency;

	private ImageIcon icElevator, icAttackBow, icAttackPy, icAttackMeatPy, icFallingPy, icDiePy;
	private JLabel laElevator, laAttackBow, laAttackPy, laAttackMeatPy, laFallingPy, laDiePy;
	private JPanel jpPlayer, jpDie;

	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isShoot = false;
	public boolean isArrow = false;
	public boolean isItem = false;
	public boolean isMeat = false;
	public boolean isAttackBomb = false;
	public boolean isCollisionWolf = false;
	public boolean isDie = false;
	public boolean isDrawMeat = false;

	public int x = 486;
	public int y = 130;
	public int dieX = 486;
	public int dieY = 130;
	public int arrowX = 486; 
	public int arrowY = 130;
	public int meatX = 0;
	public int meatY = 0;

	private int list = 0;

	private PooyanApp pooyanApp;
	private Wolf wolf;

	public Pooyan(PooyanApp pooyanApp, Wolf wolf) {

		this.pooyanApp = pooyanApp;
		this.wolf = wolf;

		init();
		setting();
		batch();

	}

	private void init() {

		icElevator = new ImageIcon("images/elevator.png");
		laElevator = new JLabel();

		icAttackBow = new ImageIcon("images/attackBowPy.png");
		laAttackBow = new JLabel();

		icAttackPy = new ImageIcon("images/attackPy.png");
		laAttackPy = new JLabel();

		icAttackMeatPy = new ImageIcon("images/attackMeatPy.png");
		laAttackMeatPy = new JLabel();

		icFallingPy = new ImageIcon("images/fallingPy.png");
		laFallingPy = new JLabel();

		icDiePy = new ImageIcon("images/diePy.png");
		laDiePy = new JLabel();

		transparency = new Color(255, 0, 0, 0);
		jpPlayer = new JPanel();

		transparency = new Color(255, 0, 0, 0);
		jpDie = new JPanel();

		listArrow = new ArrayList<Arrow>();
		meat = new Meat();

		// wolf = new Wolf(pooyanApp, pooyan);

	}

	private void setting() {

		setSize(620, 630);
		setLayout(null);
		setOpaque(false);
		setBackground(transparency);

		laElevator.setSize(50, 80);
		laElevator.setIcon(icElevator);
		laElevator.setBounds(10, 0, 50, 80);

		laAttackBow.setIcon(icAttackBow);
		laAttackBow.setBounds(0, 20, 50, 50);

		laAttackPy.setIcon(icAttackPy);
		laAttackPy.setBounds(0, 20, 50, 50);
		laAttackPy.setVisible(false);

		laAttackMeatPy.setIcon(icAttackMeatPy);
		laAttackMeatPy.setBounds(0, 20, 50, 50);
		laAttackMeatPy.setVisible(false);

		laFallingPy.setIcon(icFallingPy);
		laFallingPy.setBounds(0, 20, 50, 50);
		laFallingPy.setVisible(false);

		laDiePy.setIcon(icDiePy);
		laDiePy.setBounds(0, 20, 50, 50);
		laDiePy.setVisible(false);

		jpPlayer.setLayout(null);
		jpPlayer.setSize(80, 80);
		jpPlayer.setOpaque(false);
		jpPlayer.setBackground(transparency);
		jpPlayer.setLocation(x, y);

		jpDie.setLayout(null);
		jpDie.setSize(80, 80);
		jpDie.setOpaque(false);
		jpDie.setBackground(transparency);
		jpDie.setLocation(x, y);

		meat.setLocation(496, 70);

//		if (isItem == false) {
//			new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//
//					while (true) {
//						if (jpPlayer.getLocation().x == meat.getLocation().x - 10
//								&& jpPlayer.getLocation().y == meat.getLocation().y + 30) {
//							isItem = true;
//
//							meat.x = jpPlayer.getLocation().x;
//							meat.y = jpPlayer.getLocation().y;
//							meat.setLocation(meat.x, meat.y);
//
////							System.out.println("meat.getLocation.x" + meat.getLocation().x);
//							meat.setVisible(false);
//							laAttackBow.setVisible(false);
//							laAttackMeatPy.setVisible(true);
//						}
//						try {
//							if (isItem == false) {
//								meat.setVisible(false);
//								Thread.sleep(800);
//								meat.setVisible(true);
//								Thread.sleep(1000);
//							}
//
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//
//				}
//			}).start();
//
//		}

	}

	private void batch() {
		jpPlayer.add(laElevator);
		jpPlayer.add(laAttackBow);
		jpPlayer.add(laAttackPy);
		jpPlayer.add(laAttackMeatPy);
		// jpPlayer.add(laFallingPy);
		add(jpPlayer);
		add(jpDie);
		jpDie.add(laDiePy);
		jpDie.add(laFallingPy);
		createMeat();
	}

	public void createMeat() {
		if (isArrow == false) {
			if (isItem == false) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						isItem = true;
						System.out.println("½ÇÇàÁß");
						add(meat);
						isDrawMeat = true;
						meat.setLocation(496, 70);
						while (isItem) {
							try {
								meat.setVisible(true);
								Thread.sleep(500);
								meat.setVisible(false);
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				}).start();
			}
		}
	}

	public void die() {
		if (isCollisionWolf == false) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					isCollisionWolf = true;
					if (laAttackBow.isVisible())
						laAttackBow.setVisible(false);
					else if (laAttackMeatPy.isVisible())
						laAttackMeatPy.setVisible(false);
					else if (laAttackPy.isVisible())
						laAttackPy.setVisible(false);
					laFallingPy.setVisible(true);
					dieX = jpPlayer.getLocation().x;
					dieY = jpPlayer.getLocation().y;
					while (isCollisionWolf == true) {

						dieX--;
						dieY++;
						jpDie.setLocation(dieX, dieY);
						if (dieY > 510) {
							laFallingPy.setVisible(false);
							laDiePy.setVisible(true);
							dieY--;
							dieX++;
							isDie = true;
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}

				}
			}).start();

		}
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (isCollisionWolf) {
//					System.out.println(isCollisionWolf);
//					//if(isCollisionWolf) {
//						laAttackBow.setVisible(false);
//						try {
//							Thread.sleep(10);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					//}
////					if(wolf.isAttackColision) 
////						jpPlayer.remove(laAttackBow);
////					else break;
//				}
//
//			}
//		}).start();
	}

	public void moveUp() {
		if (isUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isUp = true;
					while (isUp) {
//						System.out.println(y);
						y--;
						if (y < 100) {
							if (meat.y == 70) {
								isItem = false;
								isMeat = true;
								laAttackBow.setVisible(false);
								laAttackMeatPy.setVisible(true);
							}
							y++;
							isUp = false;
						}
						jpPlayer.setLocation(x, y);
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}).start();
		}

	}

	public void moveDown() {
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while (isDown) {
//						System.out.println(y);
						y++;
						if (y > 413) {
							y--;
							isDown = false;

						}
						jpPlayer.setLocation(x, y);
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void attackMeat() {
//		if(isMeat == true) {
//			if(isAttackBomb == false) {
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						isMeat = true;
//						
//					}
//				}).start();
//			}
//			
//		}
		// if(isMeat == true) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isMeat = false;
				while (!isMeat) {

					if (meat.x <= -2) {
						System.out.println("meat.x" + meat.x);
						// meat.setLocation(496, 70);
						remove(meat);
						//isDrawMeat = false;
						createMeat();
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						break;
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					} else {
//						isArrow = false;
						meat.x--;
						meat.y+=0.2;
						meat.setLocation(meat.x, meat.y);
						laAttackMeatPy.setVisible(false);
						laAttackBow.setVisible(true);
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}

		}).start();
		// }
	}

	public void shoot() {
		if (isShoot == true) {
			if (isMeat == true) {
				if (!meat.isVisible())
					meat.setVisible(true);
				meat.x = x;
				meat.y = y;

				attackMeat();

			}

//			if (isArrow == true) {
//				
//			} else {
//				Arrow arrow = new Arrow(pooyanApp, wolf, pooyan);
//				if(isMeat == true) {
//					isArrow = false;
//				} else {
//					isArrow = true;
//				}
//			}

			if (isArrow == false) {
//				listArrow.add(new Arrow(pooyanApp, wolf, pooyan));
				Arrow arrow = new Arrow(pooyanApp, wolf, pooyan);
				if (isMeat == false) {
					isArrow = false;

				} else {
					isArrow = true;
				}
				if (isArrow == true) {

					if (arrow.isIn == false) {
						arrow.x = jpPlayer.getLocation().x - 60;
						arrow.y = jpPlayer.getLocation().y + 50;

						arrowX = arrow.x;
						arrowY = arrow.y;

						arrow.setLocation(arrowX, arrowY);

						add(arrow);

					}
					arrow.isIn = true;

					new Thread(new Runnable() {
						@Override
						public void run() {
							while (true) {

								arrow.x--;
								arrow.setLocation(arrow.x, arrow.y);
								if (arrow.x < -2) {
									remove(arrow);
									arrow.isKill = false;
									break;
								}
								try {
									Thread.sleep(2);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}

					}).start();

					laAttackBow.setVisible(false);
					laAttackPy.setVisible(true);
					repaint();
				}

			} else {

				laAttackBow.setVisible(true);
				laAttackPy.setVisible(false);
				repaint();

			}

		} else {

			laAttackBow.setVisible(true);
			laAttackPy.setVisible(false);
			isArrow = false;
			repaint();

		}

	}

	public void AttackBow() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 216, 225));
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(jpPlayer.getLocation().x + 35, 100, jpPlayer.getLocation().x + 35, jpPlayer.getLocation().y);
	}
}

