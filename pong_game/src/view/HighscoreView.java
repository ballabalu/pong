package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HighscoreView  extends JFrame implements ActionListener{

	private JButton addPlayerToHighscoreButton;
	private String title;
	public static HighscoreView hsView;
	private JButton loadHighscoreButton;
	
	public HighscoreView(String title){
		this.title = title;
	}
	
	public void init(){
		System.out.println("-- HighscoreView --");
		
		
		hsView = new HighscoreView(this.title);
		hsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 JPanel highscorePanel = new JPanel();
		 highscorePanel.setOpaque(true);
		 highscorePanel.setBackground(Color.WHITE);
		 highscorePanel.setLayout(null);     
	     
		 JLabel nameLabel = new JLabel("Name: ");
		 nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		 nameLabel.setBackground((Color.blue));
		 nameLabel.setBounds(100, 50, 200, 40);
		 highscorePanel.add(nameLabel);
		 
		
	     JTextField tfName = new JTextField("", 15);
	     tfName.setForeground(Color.BLUE);
	     tfName.setBackground(Color.YELLOW);
	     tfName.setBounds(100, 100, 200, 40);
	     highscorePanel.add(tfName);
	 
	     JButton buttonOK = new JButton("in Highscore eintragen");
	     buttonOK.setBounds(100, 140, 200, 40);
	     highscorePanel.add(buttonOK);
	     
	     
	     
	     loadHighscoreButton = new JButton("Highscore laden");
	     loadHighscoreButton.setBounds(100, 340, 200, 40);
	     loadHighscoreButton.addActionListener(this);
	     highscorePanel.add(loadHighscoreButton);
	     
	     
	     JTextArea highscoreTextarea = new JTextArea(12,30);
	     highscoreTextarea.setText("blubb");
	     highscoreTextarea.setLineWrap(true);
	     highscoreTextarea.setBounds(400, 50, 200, 40);
	     highscorePanel.add(highscoreTextarea);
	     
	     
	     hsView.setContentPane(highscorePanel);
	     hsView.setSize(900, 600);
	     hsView.setTitle(this.title);
	     hsView.setLocation(50,50);
	     hsView.setResizable(false);
	     hsView.setVisible(true);
	        
	}
	
	public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        if(ae.getSource() == this.loadHighscoreButton){
            System.out.println("-----> Highscore laden!");
            
        }
        
    }
}
