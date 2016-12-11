package model;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	
	
	//Variablen
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	//Konstruktor
	public Paddle (int x, int y, int width, int height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void moveUp(){
		y--;
		//ggf. y- = 5, 7 oder 10 oder so
	}
	
	public void moveDown(){
		y++;
		// hier vielleicht auch die Zahl erhöhen für eine schnellere Bewegung
	}
	
	public void paint(Graphics gr){
		gr.fillRect(x, y, width, height);
	}

}
