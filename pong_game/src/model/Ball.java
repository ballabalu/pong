package model;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;

import view.PongView;

public class Ball {
	
	
	private int x;
	private int y;
	private Component comp;
	private int velocityX;
	private int velocityY;
	private Paddle paddle;
	private AutoPaddle autoPaddle;
	
	//Konstruktor
	public Ball (int x, int y, Component comp, Paddle paddle, AutoPaddle autoPaddle){
		this.paddle = paddle;
		this.autoPaddle = autoPaddle;
		this.x = x;
		this.y = y;
		this.comp = comp;
		this.velocityX = 5;
		this.velocityY = 2;
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
		if (x + 50 > 875) {
			velocityX = 5;
			velocityY = 2;
		} else if (x < 0){
			velocityX = 5;
			velocityY = 2;
			x = 100;
			y = 100;
		}
		
		//Bewegung des Balls begrenzt durch das Spielfeld auf der y-Achse
		if (y + 50 > 575) {
			velocityY = velocityY * -1;
//			System.out.println("Ball geht nach oben.");
		} else if (y < 20){
			velocityY = velocityY * -1;
//			System.out.println("Ball geht nach unten.");
		}
		
		x+= velocityX;
		y+= velocityY;
	}
	
	public void collideWithPaddle(boolean collision){
		if (collision) {
			velocityX = velocityX * -1;
			int paddleY = 0;
			int autoPaddleY = 0;
			if (velocityX > 0) {
				paddleY = paddle.getY();
				velocityY += ((this.y - paddleY) / 10) - 2;
				velocityX += 1;
			} else {
				autoPaddleY = autoPaddle.getY();
				velocityY += ((this.y - autoPaddleY) / 10) - 2;
				velocityX -= 1;
			}
	
			//System.out.println(this.y + " - " + paddleY + "/10 = " + velocityY);
		} else if (collision) {
			//NICHTS
		}
	}
	
	//paintt
	public void paintt(Graphics gr){
		gr.drawImage(Toolkit.getDefaultToolkit().getImage("img/ball.png"), x, y, comp);
	}

}
