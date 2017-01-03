package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AutoPaddle {
	
	
	//Variablen
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	//Konstruktor
	public AutoPaddle (int x, int y, int width, int height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void moveUp(){
		y -= 10;
	}
	
	public void moveDown(){
		y += 10;
	}
	
	public void checkCollisionWithBall(Ball ball){
		Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), 50,50);
		Rectangle paddleRect = new Rectangle(this.x, this.y, this.width, this.height);
		
		if (paddleRect.intersects(ballRect)) {
			ball.collideWithPaddle(true);
		}
	}
	
	
	public void moveAutoPaddle(Ball ball){
		
		int midPaddle = this.y + this.height/2;
		
		if (ball.getVelocityX() > 0) {
			if (midPaddle != ball.getY()) {
				if (ball.getY() < midPaddle) {
					moveUp();
				} else if (ball.getY() > midPaddle) {
					moveDown();
				}
			}
		} else if (ball.getVelocityX() < 0) {
			if (midPaddle != 302) {
				if (midPaddle < 302) {
					moveDown();
				} else if (midPaddle > 302) {
					moveUp();
				}
			}
		}
	}
	
	public void paint(Graphics gr){
		gr.fillRect(x, y, width, height);
		gr.setColor(color);
	}

}
