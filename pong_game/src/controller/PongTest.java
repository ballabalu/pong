package controller;

import model.Pitch;
import view.PongView;

public class PongTest {

	public static void main(String[] args){
	    Pitch pitch = new Pitch();
	    PongView pongview = new PongView(); 
	    Controller controller = new Controller(pitch,pongview);
	    controller.control();
	}
   
}
