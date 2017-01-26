/**
 * PongStart.java beinhaltet die Main-Methode zum Start der Applikation.
 * In der Main-Methode wird eine Instanz der MenuView-Klasse erstellt.
 * Diese wiederum ruft ihre init-Methode auf, in der dann auch eine Instanz der 
 * Controller-Klasse erzeugt wird.
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

import controller.Controller;
import view.MenueView;

public class PongStart {

	public static void main(String[] args){
		MenueView pong = new MenueView("Pong - The Game");
		pong.init(new Controller());
	}  
}
