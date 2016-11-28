package model;

public class Pads {
	
	
	//Variablen
	public int padNumber;
	public int x;
	public int y;
	public int width = 75;
	public int height = 300;
	
	//Konstruktor
	public Pads (int padNumber){
		this.padNumber = padNumber;
		
		//Pad 1 wird in der oberen linken Ecke des Frames gezeichnet
		if (padNumber == 1) {
			this.x = width;
		}
		
		// Pad 2 wird ab der Mitte in der unteren rechten Ecke des Frames gezeichnet
		if (padNumber == 2) {
			this.x = Pitch.height/2 - this.height/2;
		}
	}
	

}
