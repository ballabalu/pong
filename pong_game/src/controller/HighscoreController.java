package controller;
 

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Highscore;

import model.Spieler;


public class HighscoreController {

	private Highscore highscore;
	
	private static final String HIGHSCORE_FILE = "pongHighscore.txt";
	
	//ObjectOutputStream outputStream = null;
	//ObjectInputStream inputStream = null;
	
	public HighscoreController(){
		
	}

	private void newHighscore() {
		Highscore highscore = new Highscore();
	}
	
	
	/*public ArrayList<Spieler> getScores(){
		System.out.println("getScores");
		ArrayList<Spieler> highscoreArrayList = highscore.getPlayerList();
		sort();
		return highscoreArrayList;
		
	}
	*/

	/*public void addPlayer(Spieler player){
		System.out.println("HighscoreController: addPlayer");
		//loadScoreFile();
		highscore.addPlayer(player);
		//updateScoreFile();
	}
	*/
	
	/*public void addPlayer(String name, int score){
		System.out.println("HighscoreController: addPlayer");
		//loadScoreFile();
		highscore.addPlayer(name, score);
		//updateScoreFile();
	}
	*/
	
	/* private void loadScoreFile() {
		System.out.println("loadScoreFile");
		try{
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			ArrayList<Spieler> sortplayerList = (ArrayList<Spieler>) inputStream.readObject();
		}catch (FileNotFoundException e){
			System.out.println("FILE NOT FOUND: )" + e.getMessage()) ;
		}catch (IOException e){
			System.out.println("IOException: )" + e.getMessage()) ;
		}catch (ClassNotFoundException e){
			System.out.println("ClassNotFoundException: )" + e.getMessage()) ;
		}finally{
			try {
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException e){
				System.out.println("IO - ERROR: )" + e.getMessage()) ;
			}
		}
		
	}
	*/
	/*private void updateScoreFile() {
		System.out.println("updateScoreFile");
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(playerList);
		}catch (FileNotFoundException e){
			System.out.println("FILE NOT FOUND: )" + e.getMessage()) ;
		}catch (IOException e){
			System.out.println("IOException: )" + e.getMessage()) ;
		}finally{
			try {
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException e){
				System.out.println("IO - ERROR: )" + e.getMessage()) ;
			}
		}
		
	}
	*/

	/*public String getHighscoreString(){
		System.out.println("getHighscoreString");
		
		String highscoreString = "";
		int maxScores = 10;
		
		ArrayList<Spieler> player = null;
		player = getScores();
		
		System.out.println("player = getScores(); " + player);
		
		int i = 0;
		int size = player.size();
		
		if(size > maxScores){
			size = maxScores;
		}
		
		while (i < size){
			System.out.println(player.get(i).getPlayerName() + " " + player.get(i).getScore());
			highscoreString +=  (i+1) +  "." + player.get(i).getPlayerName() +  " " + player.get(i).getScore() + " Punkte ------ ";
			i++;
		}
		return highscoreString;
		
	}
	*/
	
	public static void main (String[] args){
		Highscore highscore = new Highscore();
		
		highscore.addPlayer("a", 1);
		highscore.addPlayer("b", 5);
		highscore.addPlayer("c", 3);
		highscore.addPlayer("d", 2);
		
		System.out.println(highscore.toString());
		
		highscore.sort();
		
		System.out.println(highscore.toString());
		
		Spieler p1 = new Spieler("p1" , 9);
		highscore.addPlayerAndSortHighscore(p1);
		System.out.println(highscore.toString());
		
		Spieler p2 = new Spieler("p2" , 10);
		p2.setScore(20);
		p2.setScore(4);
		highscore.addPlayerAndSortHighscore(p2);
		System.out.println(highscore.toString());
		
		//highscore.loadLocalHighscoreFile();
		//highscore.updateLocalScoreFile();
		
		//Highscore highscoreLocal = new Highscore();
		//highscoreLocal.loadLocalHighscoreFile();
		//System.out.println(highscoreLocal.toString());
	}
}
