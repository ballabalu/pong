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
	
	public int getVelocityX(){
		return velocityX;
	}
	
	public int getVelocityY(){
		return velocityY;
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
	
	public void collideWithPaddle(boolean collision){
		if (collision) {
			velocityX = velocityX * -1;
			// wir könnten auch den y-wert ändern, wenn wir wollen
			// velocityY = velocityY * -1;
		} else if (collision) {
			//NICHTS
		}
	}
	
	public void paint(Graphics gr){
		gr.fillOval(x, y, 50, 50);
		gr.setColor(color);
	}

}
