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

	private ArrayList<Spieler> highscore;
	
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	
	private static final String HIGHSCORE_FILE = "pongHighscore2.txt";
	
	public Highscore(){
		highscore = new ArrayList<Spieler>();
	}
	
	
	public ArrayList<Spieler> getHighscore(){
		return highscore;
	}
	
	public void addPlayer(String name, int score){
		highscore.add(new Spieler(name, score));
	}
	
	public void addPlayer(Spieler player){
		highscore.add(player);
	}
	
	public void sort(){
		Collections.sort(this.getHighscore());
	}
	
	public void addPlayerAndSortHighscore(String name, int score){
		highscore.add(new Spieler(name, score));
		sort();
	}
	
	public void addPlayerAndSortHighscore(Spieler player){
		highscore.add(player);
		sort();
	}
	
	@Override
	public String toString(){
		String highscoreString = "";
		for (Spieler s : this.getHighscore()){
			highscoreString += s.toString() + "\n";
		}
		return highscoreString;
	}
	
	
	public void loadLocalHighscoreFile(){
		System.out.println("loadScoreFile");
		ArrayList<Spieler> loadedHighscore = null;
		
		try{
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			loadedHighscore = (ArrayList<Spieler>) inputStream.readObject();
			//return loadedHighscore;
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
		this.highscore = loadedHighscore;
	}
	
	
	public void updateLocalScoreFile() {
		System.out.println("updateScoreFile");
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(this.highscore);
		}catch (FileNotFoundException e){
			System.out.println("FILE NOT FOUND: " + e.getMessage()) ;
		}catch (IOException e){
			System.out.println("IOException: " + e.getMessage()) ;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
}
