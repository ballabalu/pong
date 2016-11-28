package model;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Highscore {

	private ArrayList<Player> playerList;
	
	private static final String HIGHSCORE_FILE = "pongHighscore.txt";
	
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	
	
	public Highscore(){
		playerList = new ArrayList<Player>();
		
	}
	
	public ArrayList<Player> getScores(){
		System.out.println("getScores");
		loadScoreFile();
		sort();
		return playerList;
	}
	
	public void sort(){
		System.out.println("sort");
		PlayerComparator vergleich = new PlayerComparator();
		Collections.sort(playerList, vergleich);
		
	}
	
	public void scoreHinzufügen(String name, int score){
		System.out.println("scoreHinzufügen");
		loadScoreFile();
		playerList.add(new Player(name, score));
		updateScoreFile();
		
	}
	
	private void loadScoreFile() {
		System.out.println("loadScoreFile");
		try{
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			playerList = (ArrayList<Player>) inputStream.readObject();
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
	private void updateScoreFile() {
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

	public String getHighscoreString(){
		System.out.println("getHighscoreString");
		
		
		String highscoreString = "";
		int maxScores = 10;
		
		
		ArrayList<Player> player = null;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
}
