package pooyan;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Arrow extends JLabel{
	private Arrow arrow = this;
	private static final String TAG = "Arrow : ";
	
	private ImageIcon icBow;
	public boolean isIn = false;
	public boolean isRemove = false;
	public int x = 0;
	public int y = 0;
	
	private PooyanApp pooyanApp;
	private Wolf wolf;
	private Pooyan pooyan;
	
	public boolean isKill = true;
	
	public Arrow(PooyanApp pooyanApp, Wolf wolf, Pooyan pooyan) {
		this.pooyanApp = pooyanApp;
		this.wolf = wolf;
		this.pooyan = pooyan;
		icBow = new ImageIcon("images/bow.png");
		setIcon(icBow);
		setSize(60, 5);
		setLocation(0,0);
		kill();
	}
	
	public void kill() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(isKill) {
					try {
						System.out.println("킬 쓰레드 진행중"); // 이거 지우면 죽이는거 작동이 잘 안됨. 확인필요
						for (int i = 0; i < pooyanApp.wolves.size(); i++) {
							if(x==pooyanApp.wolves.get(i).x+40) {
								if(y>=pooyanApp.wolves.get(i).y+10 && y<=pooyanApp.wolves.get(i).y+60) {
									System.out.println(TAG+"킬");
									pooyanApp.wolves.get(i).wolfStatus = false;
									pooyan.remove(arrow);
									//pooyanApp.remove(pooyanApp.wolves.get(i));
									//pooyanApp.repaint();
									//pooyanApp.wolves.remove(pooyanApp.wolves.get(i));
									pooyanApp.count--;
									System.out.println(pooyanApp.count);
									pooyanApp.remainWolf --;
									pooyanApp.laRemainWolf.setText(""+pooyanApp.remainWolf);
									isKill = false;
									
									break;
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
