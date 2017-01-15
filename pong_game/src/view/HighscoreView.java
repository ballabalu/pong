package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.concurrent.ThreadLocalRandom;

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
	private int top= 20;
	private JLabel info;
	private  JPanel highscorePanel = new JPanel();
	
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
		this.info = new JLabel();
		this.addPlayerToHighscoreButton = new JButton();
		this.highscorePanel = new JPanel();
		
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
		highscore.loadHighscoreFromServer();
		
		hsView = new HighscoreView(" HIGHSCORE ", this.player);
		hsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		 highscorePanel = new JPanel();
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
		
		
		setHeader(highscorePanel);
	
		/* testen mit random-punkten */
		int randomNum = ThreadLocalRandom.current().nextInt(1, 25 + 1);
		System.out.println("random: " + randomNum);
		this.player.setScore(randomNum);
		/* testen mit random-punkten */
		
	
		int punkteLetzterInTop = this.highscore.getHighscore().get(top-1).getScore();
		System.out.println("punkteLetzterInTop: " + punkteLetzterInTop);
		
		String infoString= "";
		if(this.player.getScore() > punkteLetzterInTop){
			 infoString = "<html>Herzlichen Glückwunsch!<br> Du hast <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkte!<br> Trag dich jetzt in den Highscore ein:</html>";
			 
			 //JTextField tfName = new JTextField("", 15);
		     tfName.setForeground(Color.BLUE);
		     tfName.setBackground(new Color(135,206,250));
		     tfName.setLocation(70,	 200);
		     tfName.setSize(200, 40);
		     highscorePanel.add(tfName);
		     
		     addPlayerToServerHighscoreButton = new JButton("eintragen");
		     addPlayerToServerHighscoreButton.setLocation(70,	 250);
		     addPlayerToServerHighscoreButton.setSize(200, 40);
		     addPlayerToServerHighscoreButton.addActionListener(listener);
		     addPlayerToServerHighscoreButton.setBackground(new Color(135,206,250));
		     addPlayerToServerHighscoreButton.setForeground(Color.DARK_GRAY);
		     addPlayerToServerHighscoreButton.setOpaque(true);
		     addPlayerToServerHighscoreButton.setFont(new Font("Arial", Font.BOLD, 16));
		     highscorePanel.add(addPlayerToServerHighscoreButton);
		     
		    
		}else{
			if(this.player.getScore() == 1){
				infoString = "<html>Schade! <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkt reicht leider nicht für den Highscore. Versuchs nochmal!<br></html>";
			}else{
				infoString = "<html>Schade! <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkte reichen leider nicht für den Highscore. Versuchs nochmal!<br></html>";
			}
		}
		
		
		info = new JLabel(infoString);
		
		info.setVerticalAlignment(JLabel.TOP);
		info.setFont(new Font("Arial", Font.BOLD, 18));
		info.setForeground(Color.DARK_GRAY);
		info.setLocation(70,	 120);
		info.setSize(400, 100);
		highscorePanel.add(info);
		
		/*
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		 nameLabel.setBackground((Color.blue));
		 nameLabel.setBounds(80, 50, 200, 80);
		 highscorePanel.add(nameLabel);
		 */
		
	    
	     
	     
	     
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
	     
	     
	   
	     
	     setHighscoreTextarea(highscorePanel, 500, 120);
	     setPlayAgainButton(listener, highscorePanel, 70, 380);
	     
	     setBackButton(listener, highscorePanel, 70, 450);

	     /*testButton = new JButton("showDownloadedHighscoreInTextarea");
	     testButton.setBounds(100, 445, 200, 40);
	     testButton.setBackground(Color.WHITE);
	     testButton.setFont(new Font("Arial", Font.PLAIN, 10));  
	     testButton.addActionListener(listener);
	  
		highscorePanel.add(testButton);
		*/
	}
	
	
private void panelHSView(ActionListener listener, JPanel highscorePanel) {
		
		System.out.println("panelHSView()");
		
		setHeader(highscorePanel);
		
		
	     setHighscoreTextarea(highscorePanel, 300, 100);
	     
	     setPlayAgainButton(listener, highscorePanel, 655, 500);
	     
	     
	     setBackButton(listener, highscorePanel, 25, 500);
	     
	    
	    
	     
	     
	}

private void setPlayAgainButton(ActionListener listener, JPanel highscorePanel, int xLoacation, int yLoaction) {
	JButton playButton = new JButton("jetzt spielen!");
	 playButton.setFont(new Font("Arial", Font.BOLD, 16));
	 playButton.setForeground(new Color(96,96,96));
   
	 playButton.setLocation(xLoacation, yLoaction);
	 playButton.setSize(200, 50);
	 playButton.addActionListener(listener);
	 highscorePanel.add(playButton);
}

private void setBackButton(ActionListener listener, JPanel highscorePanel, int xLoacation, int yLoaction) {
	JButton backButton = new JButton("zurück zum Menü");
	 backButton.setFont(new Font("Arial", Font.BOLD, 16));
	 backButton.setForeground(new Color(96,96,96));
	
	 backButton.setLocation(xLoacation, yLoaction);
	 backButton.setSize(200, 50);
	 backButton.addActionListener(listener);
	 highscorePanel.add(backButton);
}

private void setHeader(JPanel highscorePanel) {
	headerLabel = new JLabel ();
	headerLabel.setText("HIGHSCORE");
	headerLabel.setVerticalAlignment(JLabel.TOP);
	headerLabel.setFont(new Font("Arial", Font.BOLD, 50));
	headerLabel.setForeground(new Color(51,153,255));
	headerLabel.setLocation(300,	 25);
	headerLabel.setSize(400, 60);
	highscorePanel.add(headerLabel);
	
	JLabel line = new JLabel();
	line.setBackground(new Color(51,153,255));
	line.setLocation(200, 80);
	line.setSize(500, 4);
	line.setBorder(BorderFactory.createEtchedBorder());
	highscorePanel.add(line);
}

private void setHighscoreTextarea(JPanel highscorePanel, int xLocation, int yLoacation) {
	highscoreTextarea.setText("loading Highscore.....");
	 highscoreTextarea.setLineWrap(false);
	 highscoreTextarea.setFont(new Font("Arial", Font.BOLD, 16));
	 highscoreTextarea.setForeground(new Color(96,96,96));
	 highscoreTextarea.setLocation(xLocation,	 yLoacation);
	 highscoreTextarea.setSize(350, 420);
	 highscoreTextarea.setCaretColor(Color.WHITE);
	 //highscoreTextarea.setBorder(BorderFactory.createEtchedBorder());
	 
	 highscore.loadHighscoreFromServer();
	 showDownloadedHighscoreInTextarea(highscore);
	 highscorePanel.add(highscoreTextarea);
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
		this.highscoreTextarea.setText(highscore.getTop(top));
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
			
			this.tfName.setVisible(false);
			//this.info.setVisible(false);
			this.highscorePanel.remove(addPlayerToServerHighscoreButton);
			
			// erfolgreich eingetragen
			this.info.setText("Du wurdest in den Highscore eingetragen.");
			
			this.highscorePanel.validate();
			this.highscorePanel.repaint();
			
			System.out.println("aktuellen Highscore laden");
			highscore.loadHighscoreFromServer();
			
			System.out.println("addPlayerAndSortHighscore(): " + playerName );
			this.player.setPlayerName(playerName);
			
			highscore.addPlayerAndSortHighscore(player);
			System.out.println(highscore.toString());
			
			
			//System.out.println("	highscore.toJson();     ");
			//this.tfName.setVisible(false);
			//this.info.setVisible(false);
			//this.addPlayerToHighscoreButton.setVisible(false);
		
			
			highscore.saveHighscoreOnServer();
		
			this.highscoreTextarea.setText(highscore.getTop(top));
			
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
