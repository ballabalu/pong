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
	private boolean online;
	
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
		
		try {
			this.highscore = highscore.getHighscoreFromServer();
			online = true;
		} catch (Exception e) {
			this.highscore = new Highscore();
			online = false;
		}
		
		hsView = new HighscoreView(" HIGHSCORE ", this.player);
		hsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		highscorePanel = new JPanel();
		highscorePanel.setOpaque(true);
		highscorePanel.setBackground(Color.WHITE);
		highscorePanel.setLayout(null);   
		 
		/*
		 * für unterschiedliche Ansichten bei Klick im Menü bzw nach Game Over
		 */
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
		
		setHeader(highscorePanel);
	
		/* testen mit random-punkten */
		int randomNum = ThreadLocalRandom.current().nextInt(1, 25 + 1);
		this.player.setScore(randomNum);
		/* testen mit random-punkten */
		
		
		/*
		 * Ausgabe, je nachdem ob man in Highscore eingetragen wird oder nicht.
		 */
		String infoString= "";
		
		// wenn Internetverbindung besteht
		if(online == true){
			
			// Punkte am letzten Platz der Liste
			int punkteLetzterInTop;
			if(this.highscore.getHighscore().size() < top){
				// Punkte vom letzten Player in Liste, wenn Lister kürzer als top(20)
				punkteLetzterInTop = this.highscore.getHighscore().get(this.highscore.getHighscore().size()-1).getScore();
			}else{
				// Punkte vom letzten Player in Liste, wenn Lister länger als top(20)
				punkteLetzterInTop = this.highscore.getHighscore().get(top-1).getScore();
			}
		
			
			if(this.player.getScore() > punkteLetzterInTop){  /* Player schafft es in den Highscore */
				 infoString = "<html>Herzlichen Glückwunsch!<br> Du hast <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkte!<br> Trag dich jetzt in den Highscore ein:</html>";
				 
				 // Textfeld für Name setzen
			     tfName.setForeground(Color.BLUE);
			     tfName.setBackground(new Color(135,206,250));
			     tfName.setLocation(70,	 200);
			     tfName.setSize(200, 40);
			     highscorePanel.add(tfName);
			     
			     // eintragen-Button setzen
			     addPlayerToServerHighscoreButton = new JButton("eintragen");
			     addPlayerToServerHighscoreButton.setLocation(70,	 250);
			     addPlayerToServerHighscoreButton.setSize(200, 40);
			     addPlayerToServerHighscoreButton.addActionListener(listener);
			     addPlayerToServerHighscoreButton.setBackground(new Color(135,206,250));
			     addPlayerToServerHighscoreButton.setForeground(Color.DARK_GRAY);
			     addPlayerToServerHighscoreButton.setOpaque(true);
			     addPlayerToServerHighscoreButton.setFont(new Font("Arial", Font.BOLD, 16));
			     highscorePanel.add(addPlayerToServerHighscoreButton);
			     
			}else{  /* Player schafft es NICHT in den Highscore */
				if(this.player.getScore() == 1){
					infoString = "<html>Schade! <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkt reicht leider nicht für den Highscore. Versuchs nochmal!<br></html>";
				}else{
					infoString = "<html>Schade! <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkte reichen leider nicht für den Highscore. Versuchs nochmal!<br></html>";
				}
			}
		}else if (online == false){
			infoString = "<html>Herzlichen Glückwunsch, du hast <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkte! <br><br> Leider können im Offline-Modus deine Punkte nicht gespeichert werden.</html>";
		}
		
		/* Label mit Info, ob Player in Highscore kann  */
		info = new JLabel(infoString);
		info.setVerticalAlignment(JLabel.TOP);
		info.setFont(new Font("Arial", Font.BOLD, 16));
		info.setForeground(Color.DARK_GRAY);
		info.setLocation(70,	 120);
		info.setSize(400, 100);
		highscorePanel.add(info);
		
		
	    setHighscoreTextarea(highscorePanel, 500, 120);
	    setPlayAgainButton(listener, highscorePanel, 70, 380);
	    setBackButton(listener, highscorePanel, 70, 450);
	}
	
	
	private void panelHSView(ActionListener listener, JPanel highscorePanel) {
		setHeader(highscorePanel);
	    setHighscoreTextarea(highscorePanel, 270, 100);
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
		highscoreTextarea.setFont(new Font("Courier", Font.BOLD, 16));
		highscoreTextarea.setForeground(new Color(96,96,96));
		highscoreTextarea.setLocation(xLocation,	 yLoacation);
		highscoreTextarea.setSize(350, 420);
		highscoreTextarea.setCaretColor(Color.WHITE);
	 
		
		if(online == true){
			this.highscoreTextarea.setText(highscore.getTop(top));
			highscorePanel.add(highscoreTextarea);
		}else{
			highscoreTextarea.setText("Keine Verbindung zum Server möglich");
			highscorePanel.add(highscoreTextarea);
		}
		
	}
	
	
	
	public void postUpdatedHighscoreToServer(Highscore highscore){
		String playerName = this.tfName.getText();
		
		if (playerName.equals("") || playerName.equals("Name eingeben!") ){
		
			this.tfName.setText("Name eingeben!");
		}else{
			try{
				this.highscoreTextarea.setText("updating Highscore.....");
				
				// Textfeld, Button entfernen, Infotext anzeigen
				this.tfName.setVisible(false);
				this.highscorePanel.remove(addPlayerToServerHighscoreButton);
				this.info.setText("Du wurdest in den Highscore eingetragen.");
				this.highscorePanel.validate();
				this.highscorePanel.repaint();
				
				// aktuellen Highscore laden
				highscore.getHighscoreFromServer();
				
				//PlayerName setzen
				this.player.setPlayerName(playerName);
				
				// Player zum Highscore hinzufügen und Highscore sortieren
				highscore.addPlayerAndSortHighscore(player);
		
				//Highscore auf Server speichern
				highscore.saveHighscoreOnServer();
			
				this.highscoreTextarea.setText(highscore.getTop(top));
			}catch (Exception e){
				this.info.setText("Keine Internetverbindung!");
			}
		}
	}
	
}
