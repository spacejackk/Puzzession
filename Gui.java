//created by Cassidy Demir on 10/20/25
//game jam game gui

//file package
package gamejam;

//imports
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;
import java.util.*;

public class Gui{
	//attributes
	private InputMap iMap;
	private ActionMap aMap;
	private JFrame frame;
	private JLabel grid;
	private JLabel transition;
	private JLabel pause;
	private JLabel restart;
	private JLabel start;
	private JLabel tutorial;
	private JLabel cuts;
	private JLabel[] labels;
	private Rectangle transitionBounds;
	private Rectangle tutorialBounds;
	private Rectangle[] bounds;
	private String move;
	private String push;
	private boolean lure;
	private int level;
	
	//constructor method
	public Gui(Player player, Player human, GameObject[] objects, Cutscenes cutscenes, Text text){
		move = "";
		push = "";
		lure = false;
		level = -1;
		//objects and their bounds
		JLabel ghost = new JLabel(player.getSpriteD());
		Rectangle ghostBounds = new Rectangle(1, 1, 1, 1);
		JLabel person = new JLabel(human.getSpriteD());
		Rectangle personBounds = new Rectangle(1, 1, 1, 1);
		JLabel light = new JLabel(objects[1].getSprite());
		Rectangle lightBounds = new Rectangle(1, 1, 1, 1);
		JLabel lightTile = new JLabel(objects[2].getSprite());
		Rectangle lightTileBounds = new Rectangle(1, 1, 1, 1);
		JLabel light2 = new JLabel(objects[9].getSprite());
		Rectangle light2Bounds = new Rectangle(1, 1, 1, 1);
		JLabel lightTile2 = new JLabel(objects[10].getSprite());
		Rectangle lightTile2Bounds = new Rectangle(1, 1, 1, 1);
		JLabel lever = new JLabel(objects[3].getSprite());
		Rectangle leverBounds = new Rectangle(1, 1, 1, 1);
		JLabel radio = new JLabel(objects[4].getSpriteOff());
		Rectangle radioBounds = new Rectangle(1, 1, 1, 1);
		JLabel key = new JLabel(objects[5].getSprite());
		Rectangle keyBounds = new Rectangle(1, 1, 1, 1);
		JLabel door = new JLabel(objects[6].getSpriteOff());
		Rectangle doorBounds = new Rectangle(1, 1, 1, 1);
		JLabel exit = new JLabel(objects[7].getSprite());
		Rectangle exitBounds = new Rectangle(1, 1, 1, 1);
		JLabel wall = new JLabel(objects[8].getSprite());
		Rectangle wallBounds = new Rectangle(1, 1, 1, 1);
		JLabel wall2 = new JLabel(objects[8].getSprite());
		Rectangle wall2Bounds = new Rectangle(1, 1, 1, 1);
		JLabel sweater = new JLabel(objects[12].getSprite());
		Rectangle sweaterBounds = new Rectangle(1, 1, 1, 1);
		labels = new JLabel[]{ghost, person, light, lightTile, lever, radio, key, door, exit, wall, wall2, light2, lightTile2, sweater};
		bounds = new Rectangle[]{ghostBounds, personBounds, lightBounds, lightTileBounds, leverBounds, radioBounds, keyBounds, doorBounds, exitBounds, wallBounds, wall2Bounds, light2Bounds, lightTile2Bounds, sweaterBounds};
		//frame
		frame = new JFrame();
		frame.setSize(782, 805);
		frame.setTitle("Puzzession");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//buttons
		restart = new JLabel(objects[0].getSpriteOff());
		restart.setVisible(false);
		restart.setBounds(192, 576, 384, 64);
		frame.add(restart);
		start = new JLabel(objects[11].getSpriteOff());
		start.setVisible(false);
		start.setBounds(192, 576, 384, 64);
		frame.add(start);
		//cutscenes
		cuts = new JLabel(cutscenes.get1A());
		cuts.setVisible(false);
		cuts.setBounds(0, 0, 768, 768);
		frame.add(cuts);
		//pause menu
		pause = new JLabel(new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/pause.png"));
		pause.setVisible(false);
		pause.setBounds(0, 0, 768, 768);
		frame.add(pause);
		//level transition
		transition = new JLabel(new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/transition.png"));
		transition.setVisible(false);
		transition.setBounds(768, 0, 768, 768);
		transitionBounds = new Rectangle(768, 0, 768, 768);
		frame.add(transition);
		//text
		tutorial = new JLabel(text.getLvl1());
		tutorial.setVisible(false);
		tutorial.setBounds(0, 0, 320, 320);
		Rectangle tutorialBounds = tutorial.getBounds();
		frame.add(tutorial);
		levelLoad(player, human, objects, cutscenes, text);
		//adding player and objects to frame
		frame.add(ghost);
		frame.add(labels[1]);
		frame.add(labels[2]);
		frame.add(labels[4]);
		frame.add(labels[5]);
		frame.add(labels[7]);
		frame.add(labels[6]);
		frame.add(labels[8]);
		frame.add(labels[3]);
		frame.add(labels[9]);
		frame.add(labels[10]);
		frame.add(labels[11]);
		frame.add(labels[12]);
		frame.add(labels[13]);
		//background
		grid = new JLabel(new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/grid.png"));
		grid.setVisible(true);
		grid.setBounds(0, 0, 768, 768);
		frame.add(grid);
		setupKeyBind(player, human, objects, cutscenes, text);
		frame.setLayout(null);
        frame.setVisible(true);
		//main menu
		if (level == -1){
			menu(player, human, objects, cutscenes, text, "");
		}
	}
	
	//checks keyboard buttons
	public void setupKeyBind(Player player, Player human, GameObject[] objects, Cutscenes cutscenes, Text text){
        iMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        aMap = frame.getRootPane().getActionMap();
        iMap.put(KeyStroke.getKeyStroke("UP"), "up");
		iMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
		iMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
		iMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		iMap.put(KeyStroke.getKeyStroke("Z"), "z");
		iMap.put(KeyStroke.getKeyStroke("X"), "x");
		iMap.put(KeyStroke.getKeyStroke("C"), "c");
		iMap.put(KeyStroke.getKeyStroke("ESCAPE"), "esc");
		//player moving up
        aMap.put("up", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("")){
					moveCheck(player, human, objects, "up");
				}
				else if (move.equals("pause")){
					pause(player, human, objects, cutscenes, text, "up");
				}
				else if (move.equals("menu")){
					menu(player, human, objects, cutscenes, text, "up");
				}
            }
        });
		//player moving down
		aMap.put("down", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("")){
					moveCheck(player, human, objects, "down");
				}
				else if (move.equals("pause")){
					pause(player, human, objects, cutscenes, text, "down");
				}
				else if (move.equals("menu")){
					menu(player, human, objects, cutscenes, text, "down");
				}
            }
        });
		//player moving left
		aMap.put("left", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("")){
					moveCheck(player, human, objects, "left");
				}
            }
        });
		//player moving right
		aMap.put("right", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("")){
					moveCheck(player, human, objects, "right");
				}
            }
        });
		//player interacting
		aMap.put("z", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("")){
					interact(player, human, objects, "z");
				}
				else if (move.equals("pause")){
					pause(player, human, objects, cutscenes, text, "z");
				}
				else if (move.equals("menu")){
					menu(player, human, objects, cutscenes, text, "z");
				}
            }
        });
		//player leaving human
		aMap.put("x", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("")){
					interact(player, human, objects, "x");
				}
            }
        });
		//player fading
		aMap.put("c", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("")){
					fade(player);
				}
            }
        });
		//pausing
		aMap.put("esc", new AbstractAction(){
            public void actionPerformed(ActionEvent e){
				if (move.equals("") || move.equals("pause")){
					pause(player, human, objects, cutscenes, text, "");
				}
            }
        });
    }
	
	//checks if player can move
	public void moveCheck(Player player, Player human, GameObject[] objects, String direction){
		//player possessing human
		if (player.getPossessing() == true){
			//player moving up
			if (direction.equals("up") && bounds[1].getY() != 0){
				bounds[1].translate(0, -64);
				human.setFacing("up");
				labels[1].setIcon(human.getFadedSpriteWalkU());
			}
			//player moving down
			else if (direction.equals("down") && bounds[1].getY() != 704){
				bounds[1].translate(0, 64);
				human.setFacing("down");
				labels[1].setIcon(human.getFadedSpriteWalkD());
			}
			//player moving left
			else if (direction.equals("left") && bounds[1].getX() != 0){
				bounds[1].translate(-64, 0);
				human.setFacing("left");
				labels[1].setIcon(human.getFadedSpriteWalkL());
			}
			//player moving right
			else if (direction.equals("right") && bounds[1].getX() != 704){
				bounds[1].translate(64, 0);
				human.setFacing("right");
				labels[1].setIcon(human.getFadedSpriteWalkR());
			}
			//checks if tile is free for player to move to
			if (!bounds[1].intersects(bounds[9]) && !bounds[1].intersects(bounds[10]) && !bounds[1].intersects(bounds[13]) && !bounds[1].intersects(bounds[2]) && !bounds[1].intersects(bounds[11]) && !bounds[1].intersects(bounds[6])  && !bounds[1].intersects(bounds[4]) && !(objects[6].getOn() == false && (bounds[1].intersects(bounds[7]))) && (bounds[1].getY() != labels[1].getY() || bounds[1].getX() != labels[1].getX())){
				move = "person " + direction;
				//checks for pushable objects
				if (bounds[1].intersects(bounds[5])){
					if (direction.equals("up") && bounds[5].getY() != 0){
						bounds[5].translate(0, -64);
					}
					else if (direction.equals("down") && bounds[5].getY() != 704){
						bounds[5].translate(0, 64);
					}
					else if (direction.equals("left") && bounds[5].getX() != 0){
						bounds[5].translate(-64, 0);
					}
					else if (direction.equals("right") && bounds[5].getX() != 704){
						bounds[5].translate(64, 0);
					}
					if (!(objects[6].getOn() == false && (bounds[5].intersects(bounds[7]))) && !bounds[5].intersects(bounds[13]) && !bounds[5].intersects(bounds[9]) && !bounds[5].intersects(bounds[10]) && !bounds[5].intersects(bounds[4]) && !bounds[5].intersects(bounds[1]) && !bounds[5].intersects(bounds[2]) && !bounds[5].intersects(bounds[11]) && !bounds[5].intersects(bounds[6])){
						push = "radio " + direction;
					}
					else{
						move = "";
					}
				}
			}
		}
		else{
			if (direction.equals("up") && bounds[0].getY() != 0){
				bounds[0].translate(0, -64);
				player.setFacing("up");
				if (player.getFaded() == true){
					labels[0].setIcon(player.getFadedSpriteU());
				}
				else{
					labels[0].setIcon(player.getSpriteU());
				}
			}
			else if (direction.equals("down") && bounds[0].getY() != 704){
				bounds[0].translate(0, 64);
				player.setFacing("down");
				if (player.getFaded() == true){
					labels[0].setIcon(player.getFadedSpriteD());
				}
				else{
					labels[0].setIcon(player.getSpriteD());
				}
			}
			else if (direction.equals("left") && bounds[0].getX() != 0){
				bounds[0].translate(-64, 0);
				player.setFacing("left");
				if (player.getFaded() == true){
					labels[0].setIcon(player.getFadedSpriteL());
				}
				else{
					labels[0].setIcon(player.getSpriteL());
				}
			}
			else if (direction.equals("right") && bounds[0].getX() != 704){
				bounds[0].translate(64, 0);
				player.setFacing("right");
				if (player.getFaded() == true){
					labels[0].setIcon(player.getFadedSpriteR());
				}
				else{
					labels[0].setIcon(player.getSpriteR());
				}
			}
			//checks if tile is free for player to move to
			if (!bounds[0].intersects(bounds[1]) && !bounds[0].intersects(bounds[13]) && (!bounds[0].intersects(bounds[3]) || labels[3].isVisible() == false) && (!bounds[0].intersects(bounds[12]) || labels[12].isVisible() == false) && !(objects[6].getOn() == false && (bounds[0].intersects(bounds[7]))) && !(player.getFaded() == false && (bounds[0].intersects(bounds[9]) || bounds[0].intersects(bounds[10]) || bounds[0].intersects(bounds[2]) || bounds[0].intersects(bounds[11]) || bounds[0].intersects(bounds[5]) || bounds[0].intersects(bounds[4]))) && (bounds[0].getY() != labels[0].getY() || bounds[0].getX() != labels[0].getX())){
				move = "ghost " + direction;
				//checks for pushable objects
				if (player.getFaded() == false && bounds[0].intersects(bounds[6])){
					if (direction.equals("up") && bounds[6].getY() != 0){
						bounds[6].translate(0, -64);
					}
					else if (direction.equals("down") && bounds[6].getY() != 704){
						bounds[6].translate(0, 64);
					}
					else if (direction.equals("left") && bounds[6].getX() != 0){
						bounds[6].translate(-64, 0);
					}
					else if (direction.equals("right") && bounds[6].getX() != 704){
						bounds[6].translate(64, 0);
					}
					if (!bounds[6].intersects(bounds[9]) && !bounds[6].intersects(bounds[13]) && !bounds[6].intersects(bounds[10]) && !bounds[6].intersects(bounds[1]) && !bounds[6].intersects(bounds[4]) && !bounds[6].intersects(bounds[8]) && !bounds[6].intersects(bounds[2]) && !bounds[6].intersects(bounds[11]) && !bounds[6].intersects(bounds[5])){
						push = "key";
						//opens door
						if (objects[6].getOn() == false && bounds[6].intersects(bounds[7])){
							push += "Open";
						}
						push += " " + direction;
					}
					else{
						move = "";
					}
				}
			}
		}
		bounds[0] = labels[0].getBounds();
		bounds[1] = labels[1].getBounds();
		bounds[5] = labels[5].getBounds();
		bounds[6] = labels[6].getBounds();
	}
	
	//moves player and objects
	public void move(Player player, Player human, GameObject[] objects, Cutscenes cutscenes, Text text, int initial, String direction, String object){
		if (object.equals("ghost")){
			//player moving up
			if (direction.equals("up")){
				if (bounds[0].getY() != initial - 64){
					bounds[0].translate(0, -1);
				}
				else{
					move = "";
				}
			}
			//player moving down
			else if (direction.equals("down")){
				if (bounds[0].getY() != initial + 64){
					bounds[0].translate(0, 1);
				}
				else{
					move = "";
				}
			}
			//player moving left
			else if (direction.equals("left")){
				if (bounds[0].getX() != initial - 64){
					bounds[0].translate(-1, 0);
				}
				else{
					move = "";
				}
			}
			//player moving right
			else if (direction.equals("right")){
				if (bounds[0].getX() != initial + 64){
					bounds[0].translate(1, 0);
				}
				else{
					move = "";
				}
			}
			labels[0].setBounds(bounds[0]);
		}
		else if (object.equals("person")){
			//human moving up
			if (direction.equals("up")){
				if (bounds[1].getY() != initial - 64){
					if (bounds[1].getY() == initial - 1){
						labels[1].setIcon(human.getFadedSpriteWalkU());
					}
					else if (bounds[1].getY() == initial - 63){
						labels[1].setIcon(human.getFadedSpriteU());
					}
					bounds[1].translate(0, -1);
				}
				else{
					move = "";
				}
			}
			//human moving down
			else if (direction.equals("down")){
				if (bounds[1].getY() != initial + 64){
					if (bounds[1].getY() == initial + 1){
						labels[1].setIcon(human.getFadedSpriteWalkD());
					}
					else if (bounds[1].getY() == initial + 63){
						labels[1].setIcon(human.getFadedSpriteD());
					}
					bounds[1].translate(0, 1);
				}
				else{
					move = "";
				}
			}
			//human moving left
			else if (direction.equals("left")){
				if (bounds[1].getX() != initial - 64){
					if (bounds[1].getX() == initial - 1){
						labels[1].setIcon(human.getFadedSpriteWalkL());
					}
					else if (bounds[1].getX() == initial - 63){
						labels[1].setIcon(human.getFadedSpriteL());
					}
					bounds[1].translate(-1, 0);
				}
				else{
					move = "";
				}
			}
			//human moving right
			else if (direction.equals("right")){
				if (bounds[1].getX() != initial + 64){
					if (bounds[1].getX() == initial + 1){
						labels[1].setIcon(human.getFadedSpriteWalkR());
					}
					else if (bounds[1].getX() == initial + 63){
						labels[1].setIcon(human.getFadedSpriteR());
					}
					bounds[1].translate(1, 0);
				}
				else{
					move = "";
				}
			}
			labels[1].setBounds(bounds[1]);
		}
		else if (object.equals("radio")){
			//radio moving up
			if (direction.equals("up")){
				if (bounds[5].getY() != initial - 64){
					bounds[5].translate(0, -1);
				}
				else{
					push = "";
				}
			}
			//radio moving down
			else if (direction.equals("down")){
				if (bounds[5].getY() != initial + 64){
					bounds[5].translate(0, 1);
				}
				else{
					push = "";
				}
			}
			//radio moving left
			else if (direction.equals("left")){
				if (bounds[5].getX() != initial - 64){
					bounds[5].translate(-1, 0);
				}
				else{
					push = "";
				}
			}
			//radio moving right
			else if (direction.equals("right")){
				if (bounds[5].getX() != initial + 64){
					bounds[5].translate(1, 0);
				}
				else{
					push = "";
				}
			}
			labels[5].setBounds(bounds[5]);
		}
		else if (object.contains("key")){
			//key moving up and checking for door
			if (direction.equals("up")){
				if (bounds[6].getY() != initial - 64){
					bounds[6].translate(0, -1);
				}
				else{
					if (object.contains("Open")){
						bounds[6].setBounds(0, 0, 0, 0);
						objects[6].setOn(true);
						labels[6].setVisible(false);
						labels[7].setIcon(objects[6].getSprite());
					}
					push = "";
				}
			}
			//key moving down and checking for door
			else if (direction.equals("down")){
				if (bounds[6].getY() != initial + 64){
					bounds[6].translate(0, 1);
				}
				else{
					if (object.contains("Open")){
						bounds[6].setBounds(0, 0, 0, 0);
						objects[6].setOn(true);
						labels[6].setVisible(false);
						labels[7].setIcon(objects[6].getSprite());
					}
					push = "";
				}
			}
			//key moving left and checking for door
			else if (direction.equals("left")){
				if (bounds[6].getX() != initial - 64){
					bounds[6].translate(-1, 0);
				}
				else{
					if (object.contains("Open")){
						bounds[6].setBounds(0, 0, 0, 0);
						objects[6].setOn(true);
						labels[6].setVisible(false);
						labels[7].setIcon(objects[6].getSprite());
					}
					push = "";
				}
			}
			//key moving right and checking for door
			else if (direction.equals("right")){
				if (bounds[6].getX() != initial + 64){
					bounds[6].translate(1, 0);
				}
				else{
					if (object.contains("Open")){
						bounds[6].setBounds(0, 0, 0, 0);
						objects[6].setOn(true);
						labels[6].setVisible(false);
						labels[7].setIcon(objects[6].getSprite());
					}
					push = "";
				}
			}
			labels[6].setBounds(bounds[6]);
		}
		else if (object.equals("transition")){
			//moves level transition across screen and loads next level
			if (transitionBounds.getX() > initial - 1536){
				if (transitionBounds.getX() == 3){
					level++;
					labels[0].setVisible(true);
					labels[0].setIcon(player.getSpriteD());
					labels[1].setIcon(human.getSpriteD());
					player.setPossessing(false);
					levelLoad(player, human, objects, cutscenes, text);
				}
				transitionBounds.translate(-5, 0);
			}
			else{
				transitionBounds.setBounds(768, 0, 768, 768);
				move = "";
				if (level == -1){
					move = "menu";
				}
				else if (level == 10){
					move = "cutscene2";
				}
				else if (level == 11){
					move = "credits";
				}
			}
			transition.setBounds(transitionBounds);
		}
		frame.repaint();
	}
	
	//player interacting with objects
	public void interact(Player player, Player human, GameObject[] objects, String key){
		if (key.equals("z")){
			//player not possessing human and not faded
			if (player.getPossessing() == false && player.getFaded() == false && lure == false){
				bounds[0].grow(1, 1);
				//player possesses human
				if (labels[1].isVisible() == true && !bounds[0].intersection(bounds[1]).isEmpty() && bounds[0].intersection(bounds[1]).width * bounds[0].intersection(bounds[1]).height > 1){
					labels[0].setVisible(false);
					if (human.getFacing().equals("up")){
						labels[1].setIcon(human.getFadedSpriteU());
					}
					else if (human.getFacing().equals("down")){
						labels[1].setIcon(human.getFadedSpriteD());
					}
					else if (human.getFacing().equals("left")){
						labels[1].setIcon(human.getFadedSpriteL());
					}
					else if (human.getFacing().equals("right")){
						labels[1].setIcon(human.getFadedSpriteR());
					}
					player.setPossessing(true);
				}
				if (!bounds[0].intersection(bounds[5]).isEmpty() && bounds[0].intersection(bounds[5]).width * bounds[0].intersection(bounds[5]).height > 1){
					//player turns on radio
					if (objects[4].getOn() == false){
						objects[4].setOn(true);
						labels[5].setIcon(objects[4].getSprite());
						lure = true;
					}
					//player turns off radio
					else{
						objects[4].setOn(false);
						labels[5].setIcon(objects[4].getSpriteOff());
						lure = false;
					}
				}
				bounds[0] = labels[0].getBounds();
			}
			//player possessing human
			else if (player.getPossessing() == true){
				bounds[1].grow(1, 1);
				if (!bounds[1].intersection(bounds[2]).isEmpty() && bounds[1].intersection(bounds[2]).width * bounds[1].intersection(bounds[2]).height > 1){
					//player turns on light
					if (objects[1].getOn() == true){
						objects[1].setOn(false);
						labels[4].setIcon(objects[3].getSpriteOff());
						labels[2].setIcon(objects[1].getSpriteOff());
						labels[3].setVisible(false);
					}
					//player turns off light
					else{
						objects[1].setOn(true);
						labels[4].setIcon(objects[3].getSprite());
						labels[2].setIcon(objects[1].getSprite());
						labels[3].setVisible(true);
					}
				}
				else if (!bounds[1].intersection(bounds[11]).isEmpty() && bounds[1].intersection(bounds[11]).width * bounds[1].intersection(bounds[11]).height > 1){
					//player turns on light
					if (objects[10].getOn() == true){
						objects[10].setOn(false);
						labels[11].setIcon(objects[9].getSpriteOff());
						labels[12].setVisible(false);
					}
					//player turns off light
					else{
						objects[10].setOn(true);
						labels[11].setIcon(objects[9].getSprite());
						labels[12].setVisible(true);
					}
				}
				//player switches lever
				else if (!bounds[1].intersection(bounds[4]).isEmpty() && bounds[1].intersection(bounds[4]).width * bounds[1].intersection(bounds[4]).height > 1){
					if (objects[3].getOn() == true){
						objects[3].setOn(false);
						labels[4].setIcon(objects[3].getSpriteOff());
						labels[2].setIcon(objects[1].getSpriteOff());
						labels[3].setVisible(false);
					}
					else{
						objects[3].setOn(true);
						labels[4].setIcon(objects[3].getSprite());
						labels[2].setIcon(objects[1].getSprite());
						labels[3].setVisible(true);
					}
				}
				bounds[1] = labels[1].getBounds();
			}
		}
		else if (key.equals("x")){
			//player stops possessing human
			if (player.getPossessing() == true){
				bounds[1].grow(1, 0);
				bounds[1].translate(-1, 0);
				//finds an empty tile for the player to appear on
				if (!bounds[1].intersects(bounds[9]) && !bounds[1].intersects(bounds[10]) && !bounds[1].intersects(bounds[13]) && (!bounds[1].intersects(bounds[3]) || labels[3].isVisible() == false) && (!bounds[1].intersects(bounds[12]) || labels[12].isVisible() == false) && !(objects[6].getOn() == false && (bounds[1].intersects(bounds[7]))) && !bounds[1].intersects(bounds[2]) && !bounds[0].intersects(bounds[11]) && !bounds[1].intersects(bounds[5]) && !bounds[1].intersects(bounds[4])){
					bounds[0] = labels[1].getBounds();
					bounds[0].translate(-64, 0);
					labels[0].setBounds(bounds[0]);
					labels[0].setVisible(true);
					labels[0].setIcon(player.getSpriteL());
					if (human.getFacing().equals("up")){
						labels[1].setIcon(human.getSpriteU());
					}
					else if (human.getFacing().equals("down")){
						labels[1].setIcon(human.getSpriteD());
					}
					else if (human.getFacing().equals("left")){
						labels[1].setIcon(human.getSpriteL());
					}
					else if (human.getFacing().equals("right")){
						labels[1].setIcon(human.getSpriteR());
					}
					player.setPossessing(false);
				}
				else{
					bounds[1].translate(2, 0);
					if (!bounds[1].intersects(bounds[9]) && !bounds[1].intersects(bounds[10]) && !bounds[1].intersects(bounds[13]) && (!bounds[1].intersects(bounds[3]) || labels[3].isVisible() == false) && (!bounds[1].intersects(bounds[12]) || labels[12].isVisible() == false) && !(objects[6].getOn() == false && (bounds[1].intersects(bounds[7]))) && !bounds[1].intersects(bounds[2]) && !bounds[1].intersects(bounds[11]) && !bounds[1].intersects(bounds[5]) && !bounds[1].intersects(bounds[4])){
						bounds[0] = labels[1].getBounds();
						bounds[0].translate(64, 0);
						labels[0].setBounds(bounds[0]);
						labels[0].setVisible(true);
						labels[0].setIcon(player.getSpriteR());
						if (human.getFacing().equals("up")){
							labels[1].setIcon(human.getSpriteU());
						}
						else if (human.getFacing().equals("down")){
							labels[1].setIcon(human.getSpriteD());
						}
						else if (human.getFacing().equals("left")){
							labels[1].setIcon(human.getSpriteL());
						}
						else if (human.getFacing().equals("right")){
							labels[1].setIcon(human.getSpriteR());
						}
						player.setPossessing(false);
					}
					else{
						bounds[1] = labels[1].getBounds();
						bounds[1].grow(0, 1);
						bounds[1].translate(0, -1);
						if (!bounds[1].intersects(bounds[9]) && !bounds[1].intersects(bounds[10]) && !bounds[1].intersects(bounds[13]) && (!bounds[1].intersects(bounds[3]) || labels[3].isVisible() == false) && (!bounds[1].intersects(bounds[12]) || labels[12].isVisible() == false) && !(objects[6].getOn() == false && (bounds[1].intersects(bounds[7]))) && !bounds[0].intersects(bounds[2]) && !bounds[0].intersects(bounds[11]) && !bounds[0].intersects(bounds[5]) && !bounds[0].intersects(bounds[4])){
							bounds[0] = labels[1].getBounds();
							bounds[0].translate(0, -64);
							labels[0].setBounds(bounds[0]);
							labels[0].setVisible(true);
							labels[0].setIcon(player.getSpriteU());
							if (human.getFacing().equals("up")){
								labels[1].setIcon(human.getSpriteU());
							}
							else if (human.getFacing().equals("down")){
								labels[1].setIcon(human.getSpriteD());
							}
							else if (human.getFacing().equals("left")){
								labels[1].setIcon(human.getSpriteL());
							}
							else if (human.getFacing().equals("right")){
								labels[1].setIcon(human.getSpriteR());
							}
							player.setPossessing(false);
						}
						else{
							bounds[1].translate(0, 2);
							if (!bounds[1].intersects(bounds[9]) && !bounds[1].intersects(bounds[10]) && !bounds[1].intersects(bounds[13]) && (!bounds[1].intersects(bounds[3]) || labels[3].isVisible() == false) && (!bounds[1].intersects(bounds[12]) || labels[12].isVisible() == false) && !(objects[6].getOn() == false && (bounds[1].intersects(bounds[7]))) && !bounds[1].intersects(bounds[2]) && !bounds[1].intersects(bounds[11]) && !bounds[1].intersects(bounds[5]) && !bounds[1].intersects(bounds[4])){
								bounds[0] = labels[1].getBounds();
								bounds[0] = labels[1].getBounds();
								bounds[0].translate(0, 64);
								labels[0].setBounds(bounds[0]);
								labels[0].setVisible(true);
								labels[0].setIcon(player.getSpriteD());
								if (human.getFacing().equals("up")){
									labels[1].setIcon(human.getSpriteU());
								}
								else if (human.getFacing().equals("down")){
									labels[1].setIcon(human.getSpriteD());
								}
								else if (human.getFacing().equals("left")){
									labels[1].setIcon(human.getSpriteL());
								}
								else if (human.getFacing().equals("right")){
									labels[1].setIcon(human.getSpriteR());
								}
								player.setPossessing(false);
							}
						}
					}
				}
				bounds[1] = labels[1].getBounds();
			}
		}
		frame.repaint();
	}
	
	public void fade(Player player){
		//player fades
		if (player.getPossessing() == false && player.getFaded() == false){
			player.setFaded(true);
			if (player.getFacing().equals("up")){
				labels[0].setIcon(player.getFadedSpriteU());
			}
			else if (player.getFacing().equals("down")){
				labels[0].setIcon(player.getFadedSpriteD());
			}
			else if (player.getFacing().equals("left")){
				labels[0].setIcon(player.getFadedSpriteL());
			}
			else if (player.getFacing().equals("right")){
				labels[0].setIcon(player.getFadedSpriteR());
			}
		}
		//player stops fading
		else if (player.getPossessing() == false && player.getFaded() == true){
			player.setFaded(false);
			if (player.getFacing().equals("up")){
				labels[0].setIcon(player.getSpriteU());
			}
			else if (player.getFacing().equals("down")){
				labels[0].setIcon(player.getSpriteD());
			}
			else if (player.getFacing().equals("left")){
				labels[0].setIcon(player.getSpriteL());
			}
			else if (player.getFacing().equals("right")){
				labels[0].setIcon(player.getSpriteR());
			}
		}
	}
	
	public void lure(Player player, Player human, GameObject[] objects, int targetX, int targetY){
		if (player.getPossessing() == false){
			//moves human to radio
			if (bounds[1].getY() > targetY){
				bounds[1].translate(0, -1);
				human.setFacing("up");
			}
			else if (bounds[1].getY() < targetY){
				bounds[1].translate(0, 1);
				human.setFacing("down");
			}
			else if (bounds[1].getX() > targetX){
				bounds[1].translate(-1, 0);
				human.setFacing("left");
			}
			else if (bounds[1].getX() < targetX){
				bounds[1].translate(1, 0);
				human.setFacing("right");
			}
			//checks if tile is free for human to move to
			if (!bounds[1].intersects(bounds[9]) && !bounds[1].intersects(bounds[10]) && !bounds[1].intersects(bounds[13]) && (!bounds[1].intersects(bounds[0]) || player.getFaded() == true) && !(objects[6].getOn() == false && (bounds[1].intersects(bounds[7]))) && !bounds[1].intersects(bounds[2]) && !bounds[1].intersects(bounds[11]) && !bounds[1].intersects(bounds[5]) && !bounds[1].intersects(bounds[6]) && !bounds[1].intersects(bounds[4]) && (bounds[1].getY() != labels[1].getY() || bounds[1].getX() != labels[1].getX())){
				if (human.getFacing().equals("up")){
					labels[1].setIcon(human.getSpriteU());
				}
				else if (human.getFacing().equals("down")){
					labels[1].setIcon(human.getSpriteD());
				}
				else if (human.getFacing().equals("left")){
					labels[1].setIcon(human.getSpriteL());
				}
				else if (human.getFacing().equals("right")){
					labels[1].setIcon(human.getSpriteR());
				}
				labels[1].setBounds(bounds[1]);
			}
			else{
				//turns off radio when human reaches it
				if (bounds[1].intersects(bounds[5])){
					objects[4].setOn(false);
					labels[5].setIcon(objects[4].getSpriteOff());
				}
				bounds[1] = labels[1].getBounds();
				lure = false;
			}
		}
		frame.repaint();
	}
	
	public void pause(Player player, Player human, GameObject[] objects, Cutscenes cutscenes, Text text, String direction){
		if (direction.equals("")){
			//pasuing
			if (pause.isVisible() == false){
				move = "pause";
				pause.setVisible(true);
				restart.setVisible(true);
			}
			//unpausing
			else{
				move = "";
				pause.setVisible(false);
				restart.setVisible(false);
			}
		}
		//selecting buttons
		else if (direction.equals("up")){
			if (((ImageIcon)restart.getIcon()).getImage().equals(objects[0].getSprite().getImage())){
				objects[0].setOn(false);
				restart.setIcon(objects[0].getSpriteOff());
			}
		}
		else if (direction.equals("down")){
			if (((ImageIcon)restart.getIcon()).getImage().equals(objects[0].getSpriteOff().getImage())){
				objects[0].setOn(true);
				restart.setIcon(objects[0].getSprite());
			}
		}
		else if (direction.equals("z")){
			if (objects[0].getOn() == true){
				move = "";
				pause.setVisible(false);
				restart.setVisible(false);
				levelLoad(player, human, objects, cutscenes, text);
			}
		}
	}
	
	public void menu(Player player, Player human, GameObject[] objects, Cutscenes cutscenes, Text text, String direction){
		//main menu
		cuts.setIcon(cutscenes.getMenu());
		if (direction.equals("")){
			move = "menu";
			cuts.setVisible(true);
			start.setIcon(objects[11].getSpriteOff());
			start.setVisible(true);
		}
		//selecting buttons
		else if (direction.equals("up")){
			if (((ImageIcon)start.getIcon()).getImage().equals(objects[11].getSprite().getImage())){
				objects[11].setOn(false);
				start.setIcon(objects[11].getSpriteOff());
			}
		}
		else if (direction.equals("down")){
			if (((ImageIcon)start.getIcon()).getImage().equals(objects[11].getSpriteOff().getImage())){
				objects[11].setOn(true);
				start.setIcon(objects[11].getSprite());
			}
		}
		else if (direction.equals("z")){
			if (objects[11].getOn() == true){
				move = "";
				level = 0;
				start.setVisible(false);
				levelLoad(player, human, objects, cutscenes, text);
			}
		}
	}
	
	public void playCutscene(Player player, Player human, Cutscenes cutscenes, String frame){
		cuts.setVisible(true);
		//starting cutscene
		if (frame.equals("1A")){
			cuts.setIcon(cutscenes.get1A());
		}
		else if (frame.equals("1B")){
			cuts.setIcon(cutscenes.get1B());
		}
		else if (frame.equals("1C")){
			cuts.setIcon(cutscenes.get1C());
		}
		else if (frame.equals("1D")){
			cuts.setIcon(cutscenes.get1D());
		}
		//starts game
		else if (frame.equals("start")){
			checkLevel(player, human, true);
		}
		//ending cutscene
		else if (frame.equals("2A")){
			cuts.setIcon(cutscenes.get2A());
		}
		else if (frame.equals("2B")){
			cuts.setIcon(cutscenes.get2B());
		}
		else if (frame.equals("2C")){
			cuts.setIcon(cutscenes.get2C());
		}
		//ends game
		else if (frame.equals("end")){
			checkLevel(player, human, true);
		}
		//credits
		else if (frame.equals("credits1")){
			cuts.setIcon(cutscenes.getCredits1());
		}
		else if (frame.equals("credits2")){
			cuts.setIcon(cutscenes.getCredits2());
		}
		else if (frame.equals("credits3")){
			cuts.setIcon(cutscenes.getCredits3());
		}
		else if (frame.equals("credits4")){
			cuts.setIcon(cutscenes.getCredits4());
		}
		else if (frame.equals("credits5")){
			cuts.setIcon(cutscenes.getCredits5());
		}
	}
	
	//checks if player is at exit
	public void checkLevel(Player player, Player human, boolean override){
		if ((player.getFaded() == false && bounds[0].intersects(bounds[8])) || (player.getPossessing() == true && bounds[1].intersects(bounds[8])) || override == true){
			bounds[8].setBounds(0, 0, 0, 0);
			move = "transition";
			transition.setVisible(true);
		}
		
	}
	
	public void levelLoad(Player player, Player human, GameObject[] objects, Cutscenes cutscenes, Text text){
		//resets to default
		push = "";
		lure = false;
		player.setPossessing(false);
		labels[0].setIcon(player.getSpriteD());
		labels[1].setIcon(human.getSpriteD());
		objects[1].setOn(true);
		labels[2].setIcon(objects[1].getSprite());
		objects[3].setOn(true);
		labels[4].setIcon(objects[3].getSprite());
		objects[4].setOn(false);
		labels[5].setIcon(objects[4].getSpriteOff());
		objects[6].setOn(false);
		labels[7].setIcon(objects[6].getSpriteOff());
		labels[11].setIcon(objects[9].getSprite());
		objects[10].setOn(true);
		//main menu
		if (level == -1){
			cuts.setIcon(cutscenes.getMenu());
			cuts.setVisible(true);
			bounds[8].setBounds(0, 0, 0, 0);
			menu(player, human, objects, cutscenes, text, "");
		}
		//starting cutscene
		else if (level == 0){
			bounds[8].setBounds(0, 0, 0, 0);
			move = "cutscene1";
		}
		//fading intro
		else if (level == 1){
			cuts.setVisible(false);
			tutorial.setIcon(text.getLvl1());
			tutorial.setVisible(true);
			tutorial.setBounds(0, 0, 768, 320);
			tutorialBounds = tutorial.getBounds();
			labels[0].setVisible(true);
			labels[0].setBounds(64, 384, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(640, 384, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[9].setVisible(true);
			labels[9].setBounds(320, 0, 64, 768);
			bounds[9] = labels[9].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(704, 0, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[1].setVisible(false);
			bounds[1].setBounds(0, 0, 0, 0);
			labels[2].setVisible(false);
			bounds[2].setBounds(0, 0, 0, 0);
			labels[3].setVisible(false);
			bounds[3].setBounds(0, 0, 0, 0);
			labels[4].setVisible(false);
			bounds[4].setBounds(0, 0, 0, 0);
			labels[5].setVisible(false);
			bounds[5].setBounds(0, 0, 0, 0);
			labels[6].setVisible(false);
			bounds[6].setBounds(0, 0, 0, 0);
			labels[7].setVisible(false);
			bounds[7].setBounds(0, 0, 0, 0);
			labels[10].setVisible(false);
			bounds[10].setBounds(0, 0, 0, 0);
			labels[11].setVisible(false);
			bounds[11].setBounds(0, 0, 0, 0);
			labels[12].setVisible(false);
			bounds[12].setBounds(0, 0, 0, 0);
		}
		//possession + light intro
		else if (level == 2){
			tutorial.setIcon(text.getLvl2());
			tutorial.setVisible(true);
			tutorial.setBounds(0, 448, 768, 320);
			tutorialBounds = tutorial.getBounds();
			labels[0].setVisible(true);
			labels[0].setBounds(64, 128, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(576, 128, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(512, 448, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(384, 320, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(640, 448, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[9].setVisible(true);
			labels[9].setBounds(256, 0, 64, 768);
			bounds[9] = labels[9].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(0, 0, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[4].setVisible(false);
			bounds[4].setBounds(0, 0, 0, 0);
			labels[5].setVisible(false);
			bounds[5].setBounds(0, 0, 0, 0);
			labels[6].setVisible(false);
			bounds[6].setBounds(0, 0, 0, 0);
			labels[7].setVisible(false);
			bounds[7].setBounds(0, 0, 0, 0);
			labels[10].setVisible(false);
			bounds[10].setBounds(0, 0, 0, 0);
			labels[11].setVisible(false);
			bounds[11].setBounds(0, 0, 0, 0);
			labels[12].setVisible(false);
			bounds[12].setBounds(0, 0, 0, 0);
		}
		//switch intro
		else if (level == 3){
			tutorial.setIcon(text.getLvl3());
			tutorial.setVisible(true);
			tutorial.setBounds(320, 0, 448, 256);
			tutorialBounds = tutorial.getBounds();
			labels[0].setVisible(true);
			labels[0].setBounds(64, 128, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(64, 512, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(512, 384, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(384, 256, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[4].setVisible(true);
			labels[4].setBounds(64, 320, 64, 64);
			bounds[4] = labels[4].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(640, 384, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[9].setVisible(true);
			labels[9].setBounds(256, 0, 64, 768);
			bounds[9] = labels[9].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(704, 0, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[5].setVisible(false);
			bounds[5].setBounds(0, 0, 0, 0);
			labels[6].setVisible(false);
			bounds[6].setBounds(0, 0, 0, 0);
			labels[7].setVisible(false);
			bounds[7].setBounds(0, 0, 0, 0);
			labels[10].setVisible(false);
			bounds[10].setBounds(0, 0, 0, 0);
			labels[11].setVisible(false);
			bounds[11].setBounds(0, 0, 0, 0);
			labels[12].setVisible(false);
			bounds[12].setBounds(0, 0, 0, 0);
		}
		//key intro
		else if (level == 4){
			tutorial.setIcon(text.getLvl4());
			tutorial.setVisible(true);
			tutorial.setBounds(448, 448, 320, 320);
			tutorialBounds = tutorial.getBounds();
			labels[0].setVisible(true);
			labels[0].setBounds(128, 128, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(128, 576, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(512, 256, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(384, 128, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[6].setVisible(true);
			labels[6].setBounds(384, 576, 64, 64);
			bounds[6] = labels[6].getBounds();
			labels[7].setVisible(true);
			labels[7].setBounds(0, 384, 64, 64);
			bounds[7] = labels[7].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(640, 256, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[9].setVisible(true);
			labels[9].setBounds(256, 384, 64, 384);
			bounds[9] = labels[9].getBounds();
			labels[10].setVisible(true);
			labels[10].setBounds(64, 384, 192, 64);
			bounds[10] = labels[10].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(0, 0, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[4].setVisible(false);
			bounds[4].setBounds(0, 0, 0, 0);
			labels[5].setVisible(false);
			bounds[5].setBounds(0, 0, 0, 0);
			labels[11].setVisible(false);
			bounds[11].setBounds(0, 0, 0, 0);
			labels[12].setVisible(false);
			bounds[12].setBounds(0, 0, 0, 0);
		}
		//radio intro
		else if (level == 5){
			tutorial.setIcon(text.getLvl5());
			tutorial.setVisible(true);
			tutorial.setBounds(64, 0, 320, 320);
			tutorialBounds = tutorial.getBounds();
			labels[0].setVisible(true);
			labels[0].setBounds(384, 128, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(384, 448, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(320, 448, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(192, 320, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[5].setVisible(true);
			labels[5].setBounds(576, 192, 64, 64);
			bounds[5] = labels[5].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(256, 512, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[13].setVisible(true);
			labels[13].setBounds(0, 704, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[4].setVisible(false);
			bounds[4].setBounds(0, 0, 0, 0);
			labels[6].setVisible(false);
			bounds[6].setBounds(0, 0, 0, 0);
			labels[7].setVisible(false);
			bounds[7].setBounds(0, 0, 0, 0);
			labels[9].setVisible(false);
			bounds[9].setBounds(0, 0, 0, 0);
			labels[10].setVisible(false);
			bounds[10].setBounds(0, 0, 0, 0);
			labels[11].setVisible(false);
			bounds[11].setBounds(0, 0, 0, 0);
			labels[12].setVisible(false);
			bounds[12].setBounds(0, 0, 0, 0);
		}
		//end of new stuff
		else if (level == 6){
			tutorial.setVisible(false);
			labels[0].setVisible(true);
			labels[0].setBounds(128, 128, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(384, 576, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(448, 128, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(320, 0, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[5].setVisible(true);
			labels[5].setBounds(192, 64, 64, 64);
			bounds[5] = labels[5].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(512, 448, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[11].setVisible(true);
			labels[11].setBounds(128, 448, 64, 64);
			bounds[11] = labels[11].getBounds();
			labels[12].setVisible(true);
			labels[12].setBounds(0, 320, 320, 320);
			bounds[12] = labels[12].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(704, 704, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[4].setVisible(false);
			bounds[4].setBounds(0, 0, 0, 0);
			labels[6].setVisible(false);
			bounds[6].setBounds(0, 0, 0, 0);
			labels[7].setVisible(false);
			bounds[7].setBounds(0, 0, 0, 0);
			labels[9].setVisible(false);
			bounds[9].setBounds(0, 0, 0, 0);
			labels[10].setVisible(false);
			bounds[10].setBounds(0, 0, 0, 0);
		}
		else if (level == 7){
			tutorial.setVisible(false);
			labels[0].setVisible(true);
			labels[0].setBounds(128, 128, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(448, 320, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(512, 320, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(384, 192, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[5].setVisible(true);
			labels[5].setBounds(128, 320, 64, 64);
			bounds[5] = labels[5].getBounds();
			labels[6].setVisible(true);
			labels[6].setBounds(64, 512, 64, 64);
			bounds[6] = labels[6].getBounds();
			labels[7].setVisible(true);
			labels[7].setBounds(320, 320, 64, 64);
			bounds[7] = labels[7].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(640, 256, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[9].setVisible(true);
			labels[9].setBounds(320, 0, 64, 320);
			bounds[9] = labels[9].getBounds();
			labels[10].setVisible(true);
			labels[10].setBounds(320, 384, 64, 384);
			bounds[10] = labels[10].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(704, 0, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[4].setVisible(false);
			bounds[4].setBounds(0, 0, 0, 0);
			labels[11].setVisible(false);
			bounds[11].setBounds(0, 0, 0, 0);
			labels[12].setVisible(false);
			bounds[12].setBounds(0, 0, 0, 0);
		}
		else if (level == 8){
			tutorial.setVisible(false);
			labels[0].setVisible(true);
			labels[0].setBounds(128, 64, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(192, 320, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(576, 320, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(448, 192, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[5].setVisible(true);
			labels[5].setBounds(256, 576, 64, 64);
			bounds[5] = labels[5].getBounds();
			labels[6].setVisible(true);
			labels[6].setBounds(64, 320, 64, 64);
			bounds[6] = labels[6].getBounds();
			labels[7].setVisible(true);
			labels[7].setBounds(384, 320, 64, 64);
			bounds[7] = labels[7].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(640, 256, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[9].setVisible(true);
			labels[9].setBounds(384, 0, 64, 320);
			bounds[9] = labels[9].getBounds();
			labels[10].setVisible(true);
			labels[10].setBounds(384, 384, 64, 384);
			bounds[10] = labels[10].getBounds();
			labels[11].setVisible(true);
			labels[11].setBounds(128, 320, 64, 64);
			bounds[11] = labels[11].getBounds();
			labels[12].setVisible(true);
			labels[12].setBounds(0, 192, 320, 320);
			bounds[12] = labels[12].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(0, 704, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[4].setVisible(false);
			bounds[4].setBounds(0, 0, 0, 0);
		}
		else if (level == 9){
			tutorial.setVisible(false);
			labels[0].setVisible(true);
			labels[0].setBounds(128, 128, 64, 64);
			bounds[0] = labels[0].getBounds();
			labels[1].setVisible(true);
			labels[1].setBounds(192, 512, 64, 64);
			bounds[1] = labels[1].getBounds();
			labels[2].setVisible(true);
			labels[2].setBounds(576, 128, 64, 64);
			bounds[2] = labels[2].getBounds();
			labels[3].setVisible(true);
			labels[3].setBounds(448, 0, 320, 320);
			bounds[3] = labels[3].getBounds();
			labels[4].setVisible(true);
			labels[4].setBounds(448, 576, 64, 64);
			bounds[4] = labels[4].getBounds();
			labels[5].setVisible(true);
			labels[5].setBounds(576, 448, 64, 64);
			bounds[5] = labels[5].getBounds();
			labels[8].setVisible(true);
			labels[8].setBounds(640, 192, 64, 64);
			bounds[8] = labels[8].getBounds();
			labels[8].setIcon(objects[7].getSprite());
			labels[9].setVisible(true);
			labels[9].setBounds(0, 320, 768, 64);
			bounds[9] = labels[9].getBounds();
			labels[11].setVisible(true);
			labels[11].setBounds(128, 576, 64, 64);
			bounds[11] = labels[11].getBounds();
			labels[12].setVisible(true);
			labels[12].setBounds(0, 448, 320, 320);
			bounds[12] = labels[12].getBounds();
			labels[13].setVisible(true);
			labels[13].setBounds(0, 0, 64, 64);
			bounds[13] = labels[13].getBounds();
			labels[6].setVisible(false);
			bounds[6].setBounds(0, 0, 0, 0);
			labels[7].setVisible(false);
			bounds[7].setBounds(0, 0, 0, 0);
			labels[10].setVisible(false);
			bounds[10].setBounds(0, 0, 0, 0);
		}
		//ending cutscene
		else if (level == 10){
			cuts.setIcon(cutscenes.get2A());
			cuts.setVisible(true);
			tutorial.setVisible(false);
			labels[0].setVisible(false);
			labels[1].setVisible(false);
			labels[2].setVisible(false);
			labels[3].setVisible(false);
			labels[4].setVisible(false);
			labels[5].setVisible(false);
			labels[6].setVisible(false);
			labels[7].setVisible(false);
			labels[8].setVisible(false);
			labels[9].setVisible(false);
			labels[10].setVisible(false);
			labels[11].setVisible(false);
			labels[12].setVisible(false);
		}
		//credits
		else if (level == 11){
			cuts.setIcon(cutscenes.getCredits1());
			cuts.setVisible(true);
			tutorial.setVisible(false);
			labels[0].setVisible(false);
			labels[1].setVisible(false);
			labels[2].setVisible(false);
			labels[3].setVisible(false);
			labels[4].setVisible(false);
			labels[5].setVisible(false);
			labels[6].setVisible(false);
			labels[7].setVisible(false);
			labels[8].setVisible(false);
			labels[9].setVisible(false);
			labels[10].setVisible(false);
			labels[11].setVisible(false);
			labels[12].setVisible(false);
		}
	}
	
	public JLabel findJLabel(String label){
		if (label.equals("ghost")){
			return labels[0];
		}
		else if (label.equals("person")){
			return labels[1];
		}
		else if (label.equals("radio")){
			return labels[5];
		}
		else if (label.contains("key")){
			return labels[6];
		}
		return null;
	}
	
	//get methods
	public String getMove(){
		return move;
	}
	public String getPush(){
		return push;
	}
	public boolean getLure(){
		return lure;
	}
	public int getX(JLabel label){
		return label.getX();
	}
	public int getY(JLabel label){
		return label.getY();
	}
	
	//set methods
	public void setMove(String move){
		this.move = move;
	}
	public void setPush(String push){
		this.push = push;
	}
	public void setRadioSprite(ImageIcon sprite){
		labels[5].setIcon(sprite);
	}
}