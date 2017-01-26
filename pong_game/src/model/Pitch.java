package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;

/**
 * Pitch.java beinhaltet Atrribute und Methoden die
 * f�r das Spielfeld notwendig sind.
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */
public class Pitch{

	private Color color = Color.black;
	private int width;
	private int height;
	private Component comp;
	
	/**
	 * Konstruktor der Pitch-Klasse
	 * Das Spielfeld wird hinsichtlich seiner Gr��e initialisiert.
	 * @param width Breite des Spielfelds
	 * @param height H�he des Spielfelds
	 * @param comp zum Zeichnen des Spielfelds n�tig
	 */
	public Pitch(int width, int height, Component comp){
		this.width = width;
		this.height = height;
		this.comp = comp;
	}
	
	/**
	 * Paintt-Methode ist für die optische Erscheinung des Spielfelds verantwortlich.
	 * @param gr Grafikobjekt
	 */
	public void paintt(Graphics gr){
		gr.drawImage(Toolkit.getDefaultToolkit().getImage("img/pitch.png"), width, height, comp);
	}
	
	/**
	 * Gibt die Farbe des Spielfelds zur�ck.
	 * @return color Farbe des Spielfelds
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setzt die Farbe des Spielfelds.
	 * @param color Farbe des Spielfelds
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Gibt die Breite des Spielfelds zur�ck.
	 * @return width Breite des Spielfelds
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Setzt die Breite des Spielfelds.
	 * @param width Breite des Spielfelds
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gibt die H�he des Spielfelds zur�ck.
	 * @return height H�he des Spielfelds
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setzt die H�he des Spielfelds.
	 * @param height H�he des Spielfelds
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
