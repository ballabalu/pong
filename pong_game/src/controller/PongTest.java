package controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.*;

import model.Pitch;
import view.MenueView;
import view.PongView;

public class PongTest extends JFrame{
		
	public static void main(String[] args){
		MenueView pong = new MenueView("Pong - The Game - Test");
		pong.init(new Controller());

	}
   
}
