package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Player implements Serializable, Comparable<Player>, Observer {
 
	private static final long serialVersionUID = 1L;
	private String playerName;
	private int playerScore;
	
	
	/**
	 * Konstruktor für Spieler, noch ohne Name und Punktzahl
	 */
	public Player(){
		this.playerName = "";
		this.playerScore = 0;
	}
	
	
	/**
	 * Konstruktor für Spieler mit Name und Punktzahl
	 * @param playerName: Name des Spielers
	 * @param playerScore: Punktzahl des Spielers
	 */
	public Player (String playerName, int playerScore){
		this.playerName= playerName;
		this.playerScore = playerScore;
	}
	
	/**
	 * Gibt Punktzahl zurück
	 * @return int: aktuelle Punktzahl
	 */
	public int getScore(){
		return playerScore;
	}
	
	
	/**
	 * gibt Player-Namen zurück
	 * @return String: gespeicherter Namen des Spielers
	 */
	public String getPlayerName(){
		return playerName;
	}
	
	/**
	 * Setzt die Punktzahl auf übergebenen Wert
	 * @param score
	 */
	public void setScore(int score){
		this.playerScore = score;
	}
	
	/**
	 * Setzt den Namen des Spielers
	 * @param playerName: Spieler-Name
	 */
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	
	/**
	 * Erhöht die Punktzahl um übergebenen Wert
	 * @param 	punkte: Wert, um den die aktuelle Punktzahl erhöht werden soll
	 */
	public void increaseScore(int punkte){
		this.playerScore = this.playerScore + punkte;
	}
	
	
	@Override
	/**
	 * Vergleicht zwei Player-Objekte anhand der Punktzahl
	 * @return 		-1: wenn this größer ist; 1 wenn this kleiner ist; 0 bei gleicher Punktzahl
	 */
	public int compareTo(Player p2) {
		int scorePlayer1 = this.getScore();
		int scorePlayer2 = p2.getScore();
		
		if (scorePlayer1 > scorePlayer2){
			return -1;
		}else if  (scorePlayer1 < scorePlayer2){
			return 1;
		}else{
			return 0;
		}
		
		
	}
	/** 
	 * Formatiert die Ausgabe von Spielername und Punktzahl mit Tabs und Leerzeichen
	 * @return formatierter String
	 */
	@Override
	public String toString(){
		
		// Name (kürzen, falls zu lang) und Tabs
		String name = this.getPlayerName();
		int zeichen = name.length();
		String tabs = "";
		if (zeichen >= 13){
			name = name.substring(0, 13);
			tabs = "... \t ";
		}else if (zeichen >10){
			tabs = " \t ";
		}else if (zeichen >3){
			tabs = "\t \t ";
		}else {
			tabs = "\t \t \t ";
		}
		
		// Punkte mit oder ohne Leerzeichen davor
		String score = "";
		if(this.getScore() < 10){
			score = " " + this.getScore();
		}else{
			score = "" + this.getScore();
		}
		
		// Anzeige "Punkt" bei 1 Punkt, "Punkte" bei mehrern Punkten
		String punktOderPunkte = "";
		if(this.getScore() == 1 ){
			punktOderPunkte = " Punkt";
		}else{
			punktOderPunkte = " Punkte";
		}
		
		return name + tabs + score + punktOderPunkte;
	}


	@Override
	/**
	 * definiert wie der "Zuhörer" Player auf Veränderungen reagieren soll
	 * erhöht score um übergebenen Wert
	 */
	public void update(Observable o, Object arg) {
		// bei Observerupdate Punktestand increasen
		int p = (Integer) arg;

		if (p != 0){
			
			this.increaseScore(p);
			
			//************* Test für Observer *********************
			System.out.println("Punkte: " + this.playerScore);
			//************* Test für Observer *********************
			
		}

		


	}


	
}
