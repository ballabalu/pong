package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Highscore;
import model.NetworkConnection;
import model.Player;

public class HighscoreView  extends JFrame {

	
	private String title;
	public static HighscoreView hsView;
	private JButton loadHighscoreButton;
	private JButton addPlayerToHighscoreButton;
	private JButton addPlayerToServerHighscoreButton;
	private JButton testButton;
	private JTextArea highscoreTextarea ;
	private JTextField tfName;
	private JLabel nameLabel;
	private Player player;
	
	private Highscore highscore = new Highscore();
	
	
	public HighscoreView(String title, Player player){
		this.title = title;
		this.player = player;
		this.loadHighscoreButton = loadHighscoreButton;
		this.testButton = testButton;
		this.highscore = new Highscore();
		this.highscoreTextarea = highscoreTextarea = new JTextArea(12,30);
		this.tfName = new JTextField("", 15);
		this.nameLabel = new JLabel("Trag dich hier in die Highscore-Liste ein:");
	}
	
	public HighscoreView(){
		
	}
	
	public void init(ActionListener listener){
		
		System.out.println("-- HighscoreView --");
		
		
		
		
		hsView = new HighscoreView(" HIGHSCORE ", this.player);
		hsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 JPanel highscorePanel = new JPanel();
		 highscorePanel.setOpaque(true);
		 highscorePanel.setBackground(Color.WHITE);
		 highscorePanel.setLayout(null);     
	     
		
		 nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		 nameLabel.setBackground((Color.blue));
		 nameLabel.setBounds(80, 50, 200, 80);
		 highscorePanel.add(nameLabel);
		 
		
	     //JTextField tfName = new JTextField("", 15);
	     tfName.setForeground(Color.BLUE);
	     tfName.setBackground(Color.YELLOW);
	     tfName.setBounds(100, 100, 200, 40);
	     highscorePanel.add(tfName);
	     
	     
	     
	     /*  ******** lokale Speicherung *************
	      
	     addPlayerToHighscoreButton = new JButton("in Highscore eintragen");
	     addPlayerToHighscoreButton.setBounds(100, 140, 200, 40);
	     addPlayerToHighscoreButton.addActionListener(listener);
	     highscorePanel.add(addPlayerToHighscoreButton);
	     
	     loadHighscoreButton = new JButton("Highscore laden");
	     loadHighscoreButton.setBounds(100, 340, 200, 40);
	     loadHighscoreButton.addActionListener(listener);
	     highscorePanel.add(loadHighscoreButton);
	     */
	     
	     
	     addPlayerToServerHighscoreButton = new JButton("in Highscore eintragen -> Server");
	     addPlayerToServerHighscoreButton.setBounds(100, 240, 200, 40);
	     addPlayerToServerHighscoreButton.addActionListener(listener);
	     highscorePanel.add(addPlayerToServerHighscoreButton);
	     
	    
	     
	     
	    // highscoreTextarea = new JTextArea(12,30);
	     highscoreTextarea.setText("blubb");
	     highscoreTextarea.setLineWrap(true);
	     highscoreTextarea.setBounds(400, 50, 300, 450);
	     highscoreTextarea.setBorder(BorderFactory.createEtchedBorder());
	     
	     highscore.loadHighscoreFromServer();
	     showDownloadedHighscoreInTextarea(highscore);
	     
	     highscorePanel.add(highscoreTextarea);
	     

	     testButton = new JButton("showDownloadedHighscoreInTextarea");
	     testButton.setBounds(100, 445, 200, 40);
	     testButton.setBackground(Color.WHITE);
	     testButton.setFont(new Font("Arial", Font.PLAIN, 10));  
	     testButton.addActionListener(listener);
	  
		highscorePanel.add(testButton);
	     
	     hsView.setContentPane(highscorePanel);
	     hsView.setSize(900, 600);
	     hsView.setTitle(this.title);
	     hsView.setLocation(50,50);
	     hsView.setResizable(false);
	     hsView.setVisible(true);
	        
	}
	
	
	/*
	public void showHighscoreInTextarea(Highscore highscore) {
		highscore.loadLocalHighscoreFile();
		System.out.println(highscore.toString());
		this.highscoreTextarea.setText(highscore.toString());
		
	}
	*/
	
	
	public void showDownloadedHighscoreInTextarea(Highscore highscore) {
		this.highscoreTextarea.setText(highscore.toString());
	}
	
	/*
	public void addPlayer(Highscore highscore) {
		String playerName = this.tfName.getText();
		this.nameLabel.setVisible(false);
		this.tfName.setVisible(false);
		this.addPlayerToHighscoreButton.setVisible(false);
		highscore.addPlayerAndSortHighscore(playerName, 3);
		System.out.println(highscore.toString());
		highscore.updateLocalScoreFile();
		showHighscoreInTextarea(highscore);
		
	}
	*/
	
	public void postUpdatedHighscoreToServer(Highscore highscore){
		System.out.println("postUpdatedHighscoreToServer(Highscore highscore)");
		
		
		String playerName = this.tfName.getText();
		
		if (playerName.equals("") || playerName.equals("Name eingeben!") ){
			System.out.println("keine eingabe: playerName: " + playerName);
			this.tfName.setText("Name eingeben!");
		
		}else{
			this.highscoreTextarea.setText("updating Highscore.....");
			
			System.out.println("aktuellen Highscore laden");
			highscore.loadHighscoreFromServer();
			
			System.out.println("addPlayerAndSortHighscore(): " + playerName );
			this.player.setPlayerName(playerName);
			
			highscore.addPlayerAndSortHighscore(player);
			System.out.println(highscore.toString());
			
			
			//System.out.println("	highscore.toJson();     ");
			
			//highscore.toJson();
			
			highscore.saveHighscoreOnServer();
			
		}
	}
	
	
	/*public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        if(ae.getSource() == this.loadHighscoreButton){
            System.out.println("-----> Highscore laden!");   
        }else if(ae.getSource() == this.eintragenButton){
        	System.out.println("-----> in Highscore eintragen!");  
        }
        
    }
    */
}
