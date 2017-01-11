package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.Highscore;
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
	private JLabel nameLabel, headerLabel;
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
	
	public HighscoreView(String title){
		this.title = title;
		this.player = null;
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
		
		System.out.println("-- HighscoreView  init()--");
		
		hsView = new HighscoreView(" HIGHSCORE ", this.player);
		hsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 JPanel highscorePanel = new JPanel();
		 highscorePanel.setOpaque(true);
		 highscorePanel.setBackground(Color.WHITE);
		 highscorePanel.setLayout(null);   
		 
	     
		if(this.player != null){
			panelAddPlayer(listener, highscorePanel);
		}else{
			panelHSView(listener, highscorePanel);
		}
		 
	     
	     hsView.setContentPane(highscorePanel);
	     hsView.setSize(900, 600);
	     hsView.setTitle(this.title);
	     hsView.setLocation(50,50);
	     hsView.setResizable(false);
	     hsView.setVisible(true);
	        
	}

	private void panelAddPlayer(ActionListener listener, JPanel highscorePanel) {
		
		System.out.println("panelAddPlayer");
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
	     highscoreTextarea.setText("loading Highscore.....");
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
	}
	
	
private void panelHSView(ActionListener listener, JPanel highscorePanel) {
		
		System.out.println("panelHSView()");
		
		headerLabel = new JLabel ();
		headerLabel.setText("HIGHSCORE");
		headerLabel.setVerticalAlignment(JLabel.TOP);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 50));
		headerLabel.setForeground(new Color(51,153,255));
		headerLabel.setLocation(300,	 25);
		headerLabel.setSize(400, 60);
		
		JLabel line = new JLabel();
		line.setBackground(new Color(51,153,255));
		line.setLocation(200, 80);
		line.setSize(500, 4);
		line.setBorder(BorderFactory.createEtchedBorder());
	    
	     highscoreTextarea.setText("loading Highscore.....");
	     highscoreTextarea.setLineWrap(false);
	     highscoreTextarea.setFont(new Font("Arial", Font.BOLD, 16));
	     highscoreTextarea.setForeground(new Color(96,96,96));
	     highscoreTextarea.setLocation(300,	 100);
	     highscoreTextarea.setSize(400, 480);
	     highscoreTextarea.setCaretColor(Color.WHITE);
	     //highscoreTextarea.setBorder(BorderFactory.createEtchedBorder());
	     
	     highscore.loadHighscoreFromServer();
	     showDownloadedHighscoreInTextarea(highscore);
	     
	     
	     JButton playButton = new JButton("jetzt spielen!");
	     playButton.setFont(new Font("Arial", Font.BOLD, 16));
	     playButton.setForeground(new Color(96,96,96));
	   
	     playButton.setLocation(625, 500);
	     playButton.setSize(200, 50);
	     playButton.addActionListener(listener);
	     highscorePanel.add(playButton);
	     
	     
	     JButton backButton = new JButton("zurück zum Menü");
	     backButton.setFont(new Font("Arial", Font.BOLD, 16));
	     backButton.setForeground(new Color(96,96,96));
	    
	     backButton.setLocation(25, 500);
	     backButton.setSize(200, 50);
	     backButton.addActionListener(listener);
	     highscorePanel.add(backButton);
	     
	     highscorePanel.add(headerLabel);
	     highscorePanel.add(highscoreTextarea);
	     highscorePanel.add(line);
	     
	}
	
	
	/*
	public void showHighscoreInTextarea(Highscore highscore) {
		highscore.loadLocalHighscoreFile();
		System.out.println(highscore.toString());
		this.highscoreTextarea.setText(highscore.toString());
		
	}
	*/
	
	
	public void showDownloadedHighscoreInTextarea(Highscore highscore) {
		//this.highscoreTextarea.setText(highscore.toString());
		this.highscoreTextarea.setText(highscore.getTop(20));
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
			
		
			
			highscore.saveHighscoreOnServer();
		
			this.highscoreTextarea.setText(highscore.toString());
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
