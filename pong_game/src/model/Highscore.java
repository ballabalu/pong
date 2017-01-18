package model;
 
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

 
public class Highscore {
	
	/** ArrayList in der Player-Objekte gespeichert werden */
	private ArrayList<Player> highscoreList;
	
	
	
	/**
	 * Konstruktor für neues Hoghscore-Objekt, legt neue highscoreList an
	 */
	public Highscore(){
		highscoreList = new ArrayList<Player>();
	}
	
	
	
	/**
	 * Speichert übergebene ArrayList<Player> als highscoreList ab
	 * @param al	ArrayList<Player>: ArrayList mit Player-Objekten
	 */
	public Highscore(ArrayList<Player> al){
		this.highscoreList = al;
	}
	
	
	
	/**
	 * Gibt highscoreList zurück
	 * @return	 ArrayList<Player>: highscoreList
	 */
	public ArrayList<Player> getHighscore(){
		return highscoreList;
	}
	
	
	
	/**
	 * Fügt neuen Spieler zur highscoreList hinzu
	 * @param name		String: Name des Spielers
	 * @param score		int: Punkte des Spielers
	 */
	public void addPlayer(String name, int score){
		highscoreList.add(new Player(name, score));
	}
	
	
	
	/**
	 * Fügt übergebenen Spieler zur highscoreList hinzu
	 * @param player	Player-Objekt
	 */
	public void addPlayer(Player player){
		highscoreList.add(player);
	}
	
	

	/**
	 * Fügt neuen Spieler (mit Name und Punktzahl) zum Highscore hinzu und sortiert ihn
	 * @param name		String: Name des Spielers
	 * @param score		int: Punkte des Spielers
	 */
	public void addPlayerAndSortHighscore(String name, int score){
		highscoreList.add(new Player(name, score));
		sort();
	}
	
	
	
	/**
	 * Fügt Spieler zum Highscore hinzu und sortiert ihn
	 * @param player
	 */
	public void addPlayerAndSortHighscore(Player player){
		highscoreList.add(player);
		sort();
	}
	 
	
	
	/**
	 * Sortiert die Highscore-Liste nach Punkten absteigend
	 */
	public void sort(){
		Collections.sort(this.getHighscore());
	}
		
	
	
	/**
	 * Läd Highscore-Liste vom Server und gibt Highscore-Objekt zurück
	 * @return	Highscore-Objekt mit heruntergeladenen Daten
	 */
	public Highscore getHighscoreFromServer() throws Exception {
		return getJsonAndSetAsHighscore();
	}
	
	
	
	/**
	 * Erzeugt Netzwerk-Verbingung, erhält von dort JSON-formatierten String und speichert ihn als highscoreList ab
	 * @return	Highscore-Objekt mit heruntergeladenen Daten
	 * @throws Exception	bei Verbindungsproblemen mit dem Server, z.B. Offline-Modus
	 */
	private Highscore getJsonAndSetAsHighscore() throws Exception{
		NetworkConnection networkConnection = new NetworkConnection("http://erdbeerwelt.com/mio/paf/hs.txt");
		
		String jsonString = "";
		
		jsonString = networkConnection.getJson();

		if (jsonString != "" ){
				//String test2 = "[{"playerName":"f","playerScore":5},{"playerName":"e","playerScore":2},{"playerName":"a","playerScore":1},{"playerName":"b","playerScore":1},{"playerName":"c","playerScore":1},{"playerName":"d","playerScore":1}]";
				Gson gson = new Gson();
				Type collectionType = new TypeToken<ArrayList<Player>>(){}.getType();
				ArrayList<Player> highscore = gson.fromJson(jsonString, collectionType);
		    
				this.highscoreList = highscore;
		}else{
			this.highscoreList= new Highscore().getHighscore();
		}
		return this;
	}
	
	
	
	/**
	 * Erzeugt Netzwerk-Verbindung und ruft dort Methode auf, die Highscore als JSON abspeichert
	 */
	public void saveHighscoreOnServer(){
		NetworkConnection networkConnection = new NetworkConnection("http://erdbeerwelt.com/mio/paf/upload.php");
		try {
			networkConnection.postJson(this);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	
	/**
	 * Verwandelt ArrayList<Player> (this.highscoreList) in String im JSON-Format
	 * @return	String, der gesamte highscoreList im JSON-Format enthält
	 */
    public String toJson(){
		Gson gson = new GsonBuilder().create();
		JsonArray jsonArray = gson.toJsonTree(this.highscoreList).getAsJsonArray();
		String jsonString = jsonArray.toString();
    	return jsonString;
    }

    
    
    /**
     * Gibt eine Top(x)-Liste als String zurück
     * @param x		Gewünschte Anzahl der Plätze
     * @return		formatierter String (mit Zeilenumbrüchen) der die Plätze 1 bis x enthält
     */
	public String getTop(int x) {
		boolean empty = this.getHighscore().isEmpty();
		int anz = this.getHighscore().size();
		String highscoreString = "";
		
		if (empty == false){
			if (anz>x){
				for(int i=0; i<x; i++){
					highscoreString +=  i+1 + ".    " + this.getHighscore().get(i).toString() + "\n";
				}
			}else{
				int i = 1;
				for (Player s : this.getHighscore()){
					highscoreString +=  i + ".    " + s.toString() + "\n";
					i++;
				}
			}
		}
		return highscoreString;
	}	
	
	
	
	@Override
	/**
	 * Gibt highscoreList als formatierten String zurück, mit Platz-Nr. und Zeilenumbruch
	 * @return Highscore als String
	 */
	public String toString(){
		String highscoreString = "";
		int i = 1;
		for (Player s : this.getHighscore()){
			highscoreString +=  i + ".    " + s.toString() + "\n";
			i++;
		}
		return highscoreString;
	}
	
}
