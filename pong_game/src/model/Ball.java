package model;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Ball {
	
	
	private int x;
	private int y;
	private Component comp;
	private int velocityX;
	private int velocityY;
	
	
	//Konstruktor
	public Ball (int x, int y, Component comp){
		this.x = x;
		this.y = y;
		this.comp = comp;
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
		velocityX = 5;
		velocityY = 5;
		
		x += velocityX;
		y += velocityY;
		
		System.out.println(x);
		System.out.println(y);
		
//		System.out.println("moveBall() in Ball.java läuft.");
//		
//		//Bewegung des Balls begrenzt durch das Spielfeld auf der x-Achse
//		if (x + 50 > 900) {
//			velocityX = -5;
//			System.out.println("Ball geht nach links.");
//		} else if (x < 0){
//			velocityX = 5;
//			System.out.println("Ball geht nach rechts.");
//		}
//		
//		//Bewegung des Balls begrenzt durch das Spielfeld auf der y-Achse
//		if (y + 50 > 600) {
//			velocityY = -5;
//			System.out.println("Ball geht nach oben.");
//		} else if (y < 20){
//			velocityY = 5;
//			System.out.println("Ball geht nach unten.");
//		}
//		
//		x+= velocityX;
//		y+= velocityY;
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
		gr.drawImage(Toolkit.getDefaultToolkit().getImage("img/ball.png"), x, y, comp);
	}

}
