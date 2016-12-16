package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MenueView extends JFrame{

	private Graphics gr;
	private JButton newGame, highscore, exitGame;
	public static MenueView pong;
	
	public MenueView(String title){
		super(title);
	}	

	public void init(ActionListener listener){
		pong = new MenueView("Pong - The Game - Menü");
		pong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);       

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