/**
 * Klasse HighscoreView: realisiert die Darstellung einer Highscore-Liste.
 * Je nach gewähltem Konstruktor wird auch eine Ansicht dargestellt, die es dem Nutzer 
 * ermöglicht, sich in den Highscore einzutragen, falls er genügend Punkte hat.
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Highscore;
import model.Player;


public class HighscoreView  extends JFrame {
	

	private static final long serialVersionUID = 1L;
	public static HighscoreView hsView;
	private Highscore highscore = new Highscore();
	private Player player;
	private int top= 20;
	
	private String title;
	private  JPanel highscorePanel = new JPanel();
	private JLabel headerLabel;
	private JTextArea highscoreTextarea ;
	private JLabel info;
	private JTextField tfName;
	private JButton addPlayerToServerHighscoreButton;
	private boolean online;

	
	/**
	 * Konstruktor der Klasse HighscoreView mit zwei zu übergebenden Pararmetern.
	 * @param title
	 * @param player
	 */
	public HighscoreView(String title, Player player){
		this.title = title;
		this.player = player;
		this.highscore = new Highscore();
		this.highscoreTextarea = new JTextArea(12,30);
		this.tfName = new JTextField("", 15);
		this.info = new JLabel();
		this.highscorePanel = new JPanel();
		
	}
	
	/**
	 * Konstruktor der Klasse HighscoreView mit einem zu übergebenden Pararmeter.
	 * @param title
	 */
	public HighscoreView(String title){
		this.title = title;
		this.player = null;
		this.highscore = new Highscore();
		this.highscoreTextarea = new JTextArea(12,30);
		this.tfName = new JTextField("", 15);
	}
	
	
	/**
	 * init-Methode, die die Schnittstelle ActionListener implementiert.
	 * @param listener
	 */
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
		 
		/**
		 * fuer unterschiedliche Ansichten bei Klick im Menue bzw nach Game Over
		 */
		if(this.player != null){
			panelAddPlayer(listener, highscorePanel);
		}else{
			panelHSView(listener, highscorePanel);
		}
		 
		/**
		 * Aussehen und Position
		 */
	    hsView.setContentPane(highscorePanel);
	    hsView.setSize(900, 600);
	    hsView.setTitle(this.title);
	    hsView.setLocation(50,50);
	    hsView.setResizable(false);
	    hsView.setVisible(true);
	        
	}

	/**
	 * realisiert Ansicht "Player in Highscore eintragen"
	 * @param listener
	 * @param highscorePanel
	 */
	private void panelAddPlayer(ActionListener listener, JPanel highscorePanel) {
		
		setHeader(highscorePanel);
	
		
		/**
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
		
			
			// Player schafft es in den Highscore 
			if(this.player.getScore() > punkteLetzterInTop){  
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
			     
			}else{  // Player schafft es NICHT in den Highscore
				if(this.player.getScore() == 1){
					infoString = "<html>Schade! <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkt reicht leider nicht für den Highscore. Versuchs nochmal!<br></html>";
				}else{
					infoString = "<html>Schade! <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkte reichen leider nicht für den Highscore. Versuchs nochmal!<br></html>";
				}
			}
		}else if (online == false){
			infoString = "<html>Herzlichen Glückwunsch, du hast <font color='#1E90FF'>" +this.player.getScore() + " </font> Punkte! <br><br> Leider können im Offline-Modus deine Punkte nicht gespeichert werden.</html>";
		}
		
		// Label mit Info, ob Player in Highscore kann 
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
	
	
	/**
	 * realisiert Darstellung der Highscore-Liste
	 * @param listener
	 * @param highscorePanel
	 */
	private void panelHSView(ActionListener listener, JPanel highscorePanel) {
		setHeader(highscorePanel);
	    setHighscoreTextarea(highscorePanel, 270, 100);
	    setPlayAgainButton(listener, highscorePanel, 655, 500);
	    setBackButton(listener, highscorePanel, 25, 500);
	}

	
	/** 
	 * erzeugt Button "jetzt spielen!"
	 * @param listener
	 * @param highscorePanel
	 * @param xLoacation	x-Position des Buttons
	 * @param yLoaction		y-Position des Buttons
	 */
	private void setPlayAgainButton(ActionListener listener, JPanel highscorePanel, int xLoacation, int yLoaction) {
		JButton playButton = new JButton("jetzt spielen!");
		playButton.setFont(new Font("Arial", Font.BOLD, 16));
		playButton.setForeground(new Color(96,96,96));
		playButton.setLocation(xLoacation, yLoaction);
		playButton.setSize(200, 50);
		playButton.addActionListener(listener);
		highscorePanel.add(playButton);
	}
	
	/** 
	 * erzeugt Button "Zurueck zum Menue"
	 * @param listener
	 * @param highscorePanel
	 * @param xLoacation	x-Position des Buttons
	 * @param yLoaction		y-Position des Buttons
	 */
	private void setBackButton(ActionListener listener, JPanel highscorePanel, int xLoacation, int yLoaction) {
		JButton backButton = new JButton("zurück zum Menü");
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setForeground(new Color(96,96,96));
		backButton.setLocation(xLoacation, yLoaction);
		backButton.setSize(200, 50);
		backButton.addActionListener(listener);
		highscorePanel.add(backButton);
	}

	/**
	 * erzeugt Header "HIGHSCORE"
	 * @param highscorePanel
	 */
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

	
	
	/**
	 * erzeugt Textarea und zeigt dort online geladene Highscore-Liste, wenn Internetverbindung besteht; 
	 * ansonsten Meldung, dass keine Verbindung besteht.
	 * @param highscorePanel
	 * @param xLocation		x-Position der Textarea
	 * @param yLoaction		y-Position der Textarea
	 */
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
	
	
	/**
	 * Traegt eingegabenen Namen und erspielte Punkte des Players in den Highscore ein und 
	 * speichert neue Highscore-Liste auf Server
	 * @param highscore
	 */
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
