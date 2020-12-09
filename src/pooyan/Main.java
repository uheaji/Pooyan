package pooyan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements Initable {

	// 컨텍스트 저장
	private Main main = this;
	// 태그
	private static final String TAG = "main : ";
	public Random random = new Random();
	public static int floor=0;
	public JLabel laBackground;
	public Wolf wolf;

	ArrayList<Wolf> wolves;

	public Main() {
		init();
		setting();
		batch();
		listener();

		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void init() {
		laBackground = new JLabel(new ImageIcon("images/background.png"));
		wolf = new Wolf();
		wolves = new ArrayList<Wolf>();
		
		
	}

	@Override
	public void setting() {
		setTitle("Pooyan");
		setSize(620, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setContentPane(laBackground);

	}

	@Override
	public void batch() {
//		add(wolf);
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 4; i++) {
					//wolf = new Wolf();
					wolves.add(new Wolf());
					wolves.get(i).moveFall();
					getContentPane().add(wolves.get(i));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
//		new Thread(new Runnable() {
//			public void run() {
//				for (int i = 0; i < wolves.size(); i++) {
//					wolves.get(i).moveFall();
//					getContentPane().add(wolves.get(i));
//					System.out.println(wolves.size());
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
		
		
	}

	
	@Override
	public void listener() {
		
//		addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//					wolf.moveFall();
//				}
//			}
//		});

		
	}

}
