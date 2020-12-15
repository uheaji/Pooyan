package pooyan;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Meat extends JLabel {
	private static final String TAG = "Meat : ";

	private ImageIcon icMeat;
	public boolean isIn = false;
	public int x = 0;
	public int y = 0;

	public PooyanApp pooyanApp;
	public Pooyan pooyan;
	public Wolf wolf;

	public boolean isKill = false;

	public Meat(PooyanApp pooyanApp, Wolf wolf, Pooyan pooyan) {
		this.pooyanApp = pooyanApp;
		this.wolf = wolf;
		this.pooyan = pooyan;
		icMeat = new ImageIcon("images/meat.png");
		setIcon(icMeat);
		setSize(160, 90);
		setLocation(496, 70);
	}

	public void kill() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isKill) {
					try {
						System.out.println(TAG + "Å³ ¾²·¹µå ÁøÇàÁß");
						for (int i = 0; i < pooyanApp.wolves.size(); i++) {
							if (pooyan.meatX >= pooyanApp.wolves.get(i).x
									&& pooyan.meatX + 30 <= pooyanApp.wolves.get(i).x + 80) {
								if (pooyan.meatY >= pooyanApp.wolves.get(i).y
										&& pooyan.meatY <= pooyanApp.wolves.get(i).y + 100) {
									if (pooyanApp.wolves.get(i).wolfStatus == true) {
										System.out.println(TAG + "Å³");
										pooyanApp.wolves.get(i).wolfStatus = false;
										pooyanApp.wolves.get(i).attackedFall();

									}
								}
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}