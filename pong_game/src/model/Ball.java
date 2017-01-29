/**
 * Ball.java wird um die Schnittstelle Observable erweitert und 
 * ist somit ein zu beobachtendes Objekt.
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

package model;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Observable;

public class Ball extends Observable{
	
	
	private int x;
	private int y;
	private Component comp;
	private int velocityX;
	private int velocityY;
	private Paddle paddle;
	private AutoPaddle autoPaddle;
	
	/**
	 * Konstruktor der Ball-Klasse
	 * @param x
	 * @param y
	 * @param comp
	 * @param paddle
	 * @param autoPaddle
	 */
	public Ball (int x, int y, Component comp, Paddle paddle, AutoPaddle autoPaddle){
		this.paddle = paddle;
		this.autoPaddle = autoPaddle;
		this.x = x;
		this.y = y;
		this.comp = comp;
		this.velocityX = 5;
		this.velocityY = 2;
	}
	
	/**
	 * Getter für alle benötigten Attribute
	 * @return
	 */
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
	
	/**
	 * Methode, die für die Bewegung des Balls auf dem Spielfeld verantwortlich ist.
	 */
	public void moveBall(){
		/**
		 * Bewegung des Balls begrenzt durch das Spielfeld auf der x-Achse
		 * Observable-Methoden, die die Bewegung des Balls überprüfen und 
		 * Änderungen an abhängige Strukturen übergeben.
		 */
		if (x + 50 > 875) {
			velocityX = 5;
			velocityY = 2;
			x = 100;
			y = 100;
			setChanged();
			notifyObservers(10);
			
		} else if (x < 0){
			velocityX = 5;
			velocityY = 2;
			setChanged();
			// Wert 0 steht für Game Over
			notifyObservers(0);
		}
		
		/**
		 * Bewegung des Balls begrenzt durch das Spielfeld auf der y-Achse
		 * 
		 */
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
	
	/**
	 * Methode, die definiert, wie der Ball reagiert, sobald er eines der Paddle beruehrt
	 * @param collision
	 */
	public void collideWithPaddle(boolean collision){
		/**
		 * Findet eine Kollision statt,
		 * aendert der Ball seine Richtung
		 */
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
	
//			System.out.println(this.y + " - " + paddleY + "/10 = " + velocityY);
		} else if (collision) {
			//NICHTS
		}
	}
	

	/**
	 * Paintt-Methode ist für die optische Erscheinung des Balls verantwortlich.
	 * @return
	 */
	public void paintt(Graphics gr){
		gr.drawImage(Toolkit.getDefaultToolkit().getImage("img/ball.png"), x, y, comp);
	}

}
