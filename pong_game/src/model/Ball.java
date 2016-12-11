package model;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	
	private int x;
	private int y;
	private Color color;
	private int velocityX;
	private int velocityY;
	
	
	//Konstruktor
	public Ball (int x, int y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
		this.velocityX = 5;
		this.velocityY = 5;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void moveBall(){
		
		//Bewegung des Balls begrenzt durch das Spielfeld auf der x-Achse
		if (x + 50 > 900) {
			velocityX = -5;
		} else if (x < 0){
			velocityX = 5;
		}
		
		//Bewegung des Balls begrenzt durch das Spielfeld auf der y-Achse
		if (y + 50 > 600) {
			velocityY = -5;
		} else if (y < 20){
			velocityY = 5;
		}
		
		x+= velocityX;
		y+= velocityY;
	}
	
	public void paint(Graphics gr){
		gr.fillOval(x, y, 50, 50);
	}

}
