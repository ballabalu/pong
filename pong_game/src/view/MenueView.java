/**
 * MenuView.java zeigt den Startbildschirm unseres Spiels und
 * basiert auf der GUI-Klasse JFrame (Swing Framework).
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenueView extends JFrame{

	private JButton newGame, highscore, exitGame, highscore2;
	public static MenueView pong;

	public MenueView(String title){
		super(title);
	}	

	public void drawimg(Graphics gr){
	}
	
	/**
	 * init-Methode
	 * @param listener
	 * 
	 * "Zeichnet" den Screen
	 * beinhaltet alle Buttons, die dann weiterführende Aktionen aktivieren
	 */
	public void init(ActionListener listener){
		pong = new MenueView("Pong - The Game - Menü");
		pong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null); 
        
        Image myPicture = Toolkit.getDefaultToolkit().getImage("img/menue.png");
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(185, 105, 526, 189);
        contentPane.add(picLabel);

        newGame = new JButton("Spiel starten");
        highscore = new JButton("Highscores");
        exitGame = new JButton("Spiel beenden");
               
        newGame.setBounds(350, 345, 200, 40);
        highscore.setBounds(350, 395, 200, 40);
        exitGame.setBounds(350, 445, 200, 40);
        
		newGame.setBackground(Color.WHITE);
		newGame.setFont(new Font("Arial", Font.PLAIN, 18));
		  
		highscore.setBackground(Color.WHITE);
		highscore.setFont(new Font("Arial", Font.PLAIN, 18));
		
		// *******zum Testen********
		highscore2 = new JButton("Highscores (aus PongView)");
		highscore2.setBounds(600, 395, 200, 40);
		highscore2.setBackground(Color.WHITE);
		highscore2.setFont(new Font("Arial", Font.PLAIN, 18));
		highscore2.addActionListener(listener);
		contentPane.add(highscore2);
		//****************
		
		exitGame.setBackground(Color.WHITE);
		exitGame.setFont(new Font("Arial", Font.PLAIN, 18));
        
		newGame.addActionListener(listener);
		highscore.addActionListener(listener);
		exitGame.addActionListener(listener);
		
		contentPane.add(newGame);
		contentPane.add(highscore);
		contentPane.add(exitGame);
				
		pong.setContentPane(contentPane);
		pong.setSize(900, 600);
		pong.setLocation(50,50);
		pong.setResizable(false);
		pong.setVisible(true);

	}

}