//created by Cassidy Demir on 10/20/25
//game jam game class

//file package
package gamejam;

//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.nio.file.*;
import javax.sound.sampled.*;

public class Game{
	private Gui gui;
	private Player player;
	private Player human;
	private Timer timer;
	private GameObject[] objects;
	private Cutscenes cutscenes;
	private Text text;
	private String move;
	private String push;
	private int initialMove;
	private int initialPush;
	private int radioTime;
	private boolean lure;
	private Clip music;
	private Clip radioStatic;
	private Clip ambience;
	
	//constructor method
	public Game(){
		gameObjects();
		player = new Player("ghost");
		human = new Player("person");
		cutscenes = new Cutscenes();
		text = new Text();
		gui = new Gui(player, human, objects, cutscenes, text);
		move = "";
		push = "";
		initialMove = -1;
		initialPush = -1;
		radioTime = 1;
		lure = false;
		audioSetup();
		gameloop();
	}
	
	//creates game objects
	public void gameObjects(){
		GameObject restart = new GameObject("restart", true, false);
		GameObject light = new GameObject("light", true, true);
		GameObject lightTile = new GameObject("lightTile", false, false);
		GameObject light2 = new GameObject("light", true, true);
		GameObject lightTile2 = new GameObject("lightTile", false, false);
		GameObject lever = new GameObject("lever", true, true);
		GameObject radio = new GameObject("radio", true, false);
		GameObject key = new GameObject("key", false, false);
		GameObject door = new GameObject("door", true, false);
		GameObject exit = new GameObject("exit", false, false);
		GameObject wall = new GameObject("wall", false, false);
		GameObject start = new GameObject("start", true, false);
		GameObject sweater = new GameObject("sweater", true, false);
		objects = new GameObject[]{restart, light, lightTile, lever, radio, key, door, exit, wall, light2, lightTile2, start, sweater};
	}
	
	public void gameloop(){
		//timer that checks for button presses
		timer = new Timer(2, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				move = gui.getMove();
				push = gui.getPush();
				lure = gui.getLure();
				if (push.equals("")){
					initialPush = -1;
				}
				if (move.equals("")){
					initialMove = -1;
				}
				//level transition
				if (move.equals("transition")){
					if (initialMove == -1){
						initialMove = 768;
					}
					gui.move(player, human, objects, cutscenes, text, initialMove, "left", "transition");
				}
				//player pushing objects
				if (push.substring(push.indexOf(' ') + 1).equals("up") || push.substring(push.indexOf(' ') + 1).equals("down")){
					if (initialPush == -1){
						initialPush = gui.getY(gui.findJLabel(push.substring(0, push.indexOf(' '))));
					}
					gui.move(player, human, objects, cutscenes, text, initialPush, push.substring(push.indexOf(' ') + 1), push.substring(0, push.indexOf(' ')));
				}
				else if (push.substring(push.indexOf(' ') + 1).equals("left") || push.substring(push.indexOf(' ') + 1).equals("right")){
					if (initialPush == -1){
						initialPush = gui.getX(gui.findJLabel(push.substring(0, push.indexOf(' '))));
					}
					gui.move(player, human, objects, cutscenes, text, initialPush, push.substring(push.indexOf(' ') + 1), push.substring(0, push.indexOf(' ')));
				}
				//player moving
				//y axis
				if (move.substring(move.indexOf(' ') + 1).equals("up") || move.substring(move.indexOf(' ') + 1).equals("down")){
					if (initialMove == -1){
						initialMove = gui.getY(gui.findJLabel(move.substring(0, move.indexOf(' '))));
					}
					gui.move(player, human, objects, cutscenes, text, initialMove, move.substring(move.indexOf(' ') + 1), move.substring(0, move.indexOf(' ')));
				}
				//x axis
				else if (move.substring(move.indexOf(' ') + 1).equals("left") || move.substring(move.indexOf(' ') + 1).equals("right")){
					if (initialMove == -1){
						initialMove = gui.getX(gui.findJLabel(move.substring(0, move.indexOf(' '))));
					}
					gui.move(player, human, objects, cutscenes, text, initialMove, move.substring(move.indexOf(' ') + 1), move.substring(0, move.indexOf(' ')));
				}
				//luring humans
				if (lure == true){
					gui.lure(player, human, objects, gui.getX(gui.findJLabel("radio")), gui.getY(gui.findJLabel("radio")));
				}
				//cutscenes
				if (move.equals("cutscene1")){
					if (radioTime == 1){
						gui.playCutscene(player, human, cutscenes, "1A");
						ambience.setFramePosition(0);
						ambience.start();
					}
					else if (radioTime == 800){
						gui.playCutscene(player, human, cutscenes, "1B");
					}
					else if (radioTime == 1600){
						gui.playCutscene(player, human, cutscenes, "1C");
					}
					else if (radioTime == 2400){
						gui.playCutscene(player, human, cutscenes, "1D");
					}
					else if (radioTime == 3200){
						gui.playCutscene(player, human, cutscenes, "start");
						ambience.stop();
						music.setFramePosition(0);
						music.loop(Clip.LOOP_CONTINUOUSLY);
						music.start();
						radioTime = 0;
					}
					radioTime++;
				}
				else if (move.equals("cutscene2")){
					if (radioTime == 1){
						gui.playCutscene(player, human, cutscenes, "2A");
						music.stop();
						ambience.setFramePosition(0);
						ambience.start();
					}
					else if (radioTime == 800){
						gui.playCutscene(player, human, cutscenes, "2B");
					}
					else if (radioTime == 1600){
						gui.playCutscene(player, human, cutscenes, "2C");
					}
					else if (radioTime == 2400){
						gui.playCutscene(player, human, cutscenes, "end");
						ambience.stop();
						music.setFramePosition(0);
						music.loop(Clip.LOOP_CONTINUOUSLY);
						music.start();
						radioTime = 0;
					}
					radioTime++;
				}
				else if (move.equals("credits")){
					if (radioTime == 1){
						gui.playCutscene(player, human, cutscenes, "credits1");
					}
					else if (radioTime == 1200){
						gui.playCutscene(player, human, cutscenes, "credits2");
					}
					else if (radioTime == 2400){
						gui.playCutscene(player, human, cutscenes, "credits3");
					}
					else if (radioTime == 3600){
						gui.playCutscene(player, human, cutscenes, "credits4");
					}
					else if (radioTime == 4800){
						gui.playCutscene(player, human, cutscenes, "credits5");
					}
					radioTime++;
				}
				//plays static and cycles sprites when radio is on
				else if (objects[4].getOn() == true){
					if (radioStatic.isActive() == false){
						radioStatic.start();
					}
					if (radioTime == 1){
						gui.setRadioSprite(objects[4].getSprite());
					}
					else if (radioTime == 100){
						gui.setRadioSprite(objects[4].getSprite2());
					}
					else if (radioTime == 200){
						radioTime = 1;
					}
					radioTime++;
				}
				//turns off static when radio is off
				else if (objects[4].getOn() == false){
					if (radioStatic.isActive() == true){
						radioStatic.stop();
						radioTime = 1;
					}
				}
				gui.checkLevel(player, human, false);
			}
		});
		timer.start();
	}
	
	public void audioSetup(){
		try{
			music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(Paths.get(System.getProperty("user.dir"), "audio", "/GHOSTZ.wav").toFile()));
			music.loop(Clip.LOOP_CONTINUOUSLY);
			music.stop();
			radioStatic = AudioSystem.getClip();
			radioStatic.open(AudioSystem.getAudioInputStream(Paths.get(System.getProperty("user.dir"), "audio", "/static.wav").toFile()));
			radioStatic.loop(Clip.LOOP_CONTINUOUSLY);
			radioStatic.stop();
			ambience = AudioSystem.getClip();
			ambience.open(AudioSystem.getAudioInputStream(Paths.get(System.getProperty("user.dir"), "audio", "/ambience.wav").toFile()));
			ambience.loop(Clip.LOOP_CONTINUOUSLY);
			ambience.stop();
		}
		catch (Exception e){
            e.printStackTrace();
        }
	}
}