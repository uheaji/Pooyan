package pooyan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements Initable {

	// 컨텍스트 저장
	private Main main = this;
	// 태그
	private static final String TAG = "main : ";
	private JLabel laBackground;
	private Wolf wolf;

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

	}

	@Override
	public void setting() {
		setTitle("Pooyan");
		setSize(700, 700); // 이미지 사이즈 확인하기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(laBackground);

	}

	@Override
	public void batch() {
		add(wolf);
	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					wolf.moveFall();
				} 
//				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//					wolf.moveRight();
//				}
			}
		});

	}

}
