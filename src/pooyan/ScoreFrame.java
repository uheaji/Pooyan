package pooyan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoreFrame extends JFrame{
	
	private PooyanApp pooyanApp;
	
	public JLabel laScore;
	
	public ScoreFrame(PooyanApp pooyanApp) {
		this.pooyanApp= pooyanApp;
		setTitle("Score");
		setSize(300, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		laScore = new JLabel();
		laScore.setSize(200, 50);
		System.out.println(pooyanApp.score);
		laScore.setText(pooyanApp.score+"��");
		laScore.setLocation(20, 10);
		laScore.setFont(new Font("Serif", Font.BOLD, 30));
		laScore.setForeground(Color.BLACK);
		
		add(laScore);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					pooyanApp.dispose();
					new PooyanApp();
					dispose();
				} 
			}
		});
		setVisible(true);
		
	}
	
}
