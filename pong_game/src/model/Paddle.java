package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;

public class Paddle extends Observable{
	
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
		//y--;
		y -= 10;
		//ggf. y- = 5, 7 oder 10 oder so
	}
	
	public void moveDown(){
		//y++;
		y += 10;
		// hier vielleicht auch die Zahl erhöhen für eine schnellere Bewegung
	}
	
	public void checkCollisionWithBall(Ball ball){
		Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), 50,50);
		Rectangle paddleRect = new Rectangle(this.x, this.y, this.width, this.height);
		
		if (paddleRect.intersects(ballRect)) {
			ball.collideWithPaddle(true);
			//Observer informieren und 1 uebergeben
			setChanged();
			notifyObservers(1);
			
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	//paintt
	public void paintt(Graphics gr){
		gr.fillRect(x, y, width, height);
		gr.setColor(color);
	}

}
