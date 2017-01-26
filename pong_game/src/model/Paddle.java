package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;

/**
 * Paddle.java beinhaltet Atrribute und Methoden die
 * für das Spieler-Paddel nötig sind.
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */
public class Paddle extends Observable{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	/**
	 * Konstruktor der Paddle-Klasse
	 * Paddle wird hinsichtlich seiner Maße, Platzierung und Farbe initialisiert.
	 * @param x x-Koordinate des Paddels
	 * @param y y-Koordinate des Paddels
	 * @param width Breite des Paddels
	 * @param height Höhe des Paddels
	 * @param color Farbe des Paddels
	 */
	public Paddle (int x, int y, int width, int height, Color color){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		
	}
	
	/**
	 * Methode die das Paddle nach oben bewegt.
	 */
	public void moveUp(){
		y -= 10;
	}
	
	/**
	 * Methode die das Paddle nach unten bewegt.
	 */
	public void moveDown(){
		y += 10;
	}
	
	/**
	 * Methode die überprüft ob der Ball mit dem
	 * Spielerpaddle kollidiert.
	 * @param ball Das Ball-Objekt wird übergeben
	 */
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
	
	/**
	 * Gibt die x-Koordinate des Paddels zurück.
	 * @return x x-Koordinate des Paddels
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setzt die x-Koordinate des Paddels.
	 * @param x x-Koordinate des Paddels
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Gibt die y-Koordinate des Paddels zurück.
	 * @return y y-Koordinate des Paddels
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setzt die y-Koordinate des Paddels.
	 * @param y y-Koordinate des Paddels
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Methode die das Paddel zeichnet.
	 * @param gr Grafikobjekt
	 */
	public void paintt(Graphics gr){
		gr.fillRect(x, y, width, height);
		gr.setColor(color);
	}

}
