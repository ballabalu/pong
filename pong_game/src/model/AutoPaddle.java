/**
 * AutoPaddle ist das KI-Paddle
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AutoPaddle {
	

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	/**
	 * Konstruktor der Klasse AutoPaddle
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public AutoPaddle (int x, int y, int width, int height, Color color){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	/**
	 * Methode, die das AutoPaddle nach oben bewegt
	 * um immer 10 Pixel entlang der y-Achse hin zum Nullpunkt des Koordinatensystems
	 */
	public void moveUp(){
		y -= 10;
	}
	
	/**
	 * Methode, die das AutoPaddle nach unten bewegt
	 * um immer 10 Pixel entlang der y-Achse weg vom Nullpunkt des Koordinatensystems
	 */
	public void moveDown(){
		y += 10;
	}
	
	/**
	 * Methode, die überprüft, ob sich Ball und AutoPaddle beruehren.
	 * Ist dies der Fall, wir die collideWithPaddle-Methode aus der Klasse Ball aufgerufen
	 * @param ball
	 */
	public void checkCollisionWithBall(Ball ball){
		Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), 50,50);
		Rectangle paddleRect = new Rectangle(this.x, this.y, this.width, this.height);
		
		if (paddleRect.intersects(ballRect)) {
			ball.collideWithPaddle(true);
		}
	}
	
	/**
	 * Methode, die für die Bewegung des AutoPaddle verantwortlich ist.
	 * @param ball
	 */
	public void moveAutoPaddle(Ball ball){
		
		int midPaddle = this.y + (this.height/2) - 30;
		
		if (midPaddle > ball.getY() && this.y > ball.getY()){
			moveUp();
		}
		
		if (midPaddle < ball.getY() || this.y + this.height < ball.getY()){
			moveDown();
		}
	}
	
	
	
	/**
	 * Getter und Setter fuer x
	 * @return
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter und Setter fuer y
	 * @return
	 */
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Paintt-Methode ist für die optische Erscheinung des AutoPaddle verantwortlich.
	 * @return
	 */
	public void paintt(Graphics gr){
		gr.fillRect(x, y, width, height);
		gr.setColor(color);
	}

}
