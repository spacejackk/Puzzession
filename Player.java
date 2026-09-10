//created by Cassidy Demir on 10/20/25
//game jam game player

//file package
package gamejam;

//imports
import java.io.*;
import javax.swing.*;
import java.io.File;

public class Player{
	//attributes
	private ImageIcon spriteU;
	private ImageIcon spriteWalkU;
	private ImageIcon spriteD;
	private ImageIcon spriteWalkD;
	private ImageIcon spriteL;
	private ImageIcon spriteWalkL;
	private ImageIcon spriteR;
	private ImageIcon spriteWalkR;
	private ImageIcon fadedSpriteU;
	private ImageIcon fadedSpriteWalkU;
	private ImageIcon fadedSpriteD;
	private ImageIcon fadedSpriteWalkD;
	private ImageIcon fadedSpriteL;
	private ImageIcon fadedSpriteWalkL;
	private ImageIcon fadedSpriteR;
	private ImageIcon fadedSpriteWalkR;
	private String facing;
	private boolean possessing;
	private boolean faded;
	
	//constructor method
	public Player(String name){
		//player sprites
		spriteU = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "U.png");
		spriteD = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "D.png");
		spriteL = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "L.png");
		spriteR = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "R.png");
		//faded player sprites
		fadedSpriteU = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name +"U.png");
		fadedSpriteD = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name +"D.png");
		fadedSpriteL = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name +"L.png");
		fadedSpriteR = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name +"R.png");
		if (name.contains("person")){
			//human sprites
			spriteWalkU = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "WalkU.png");
			spriteWalkD = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "WalkD.png");
			spriteWalkL = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "WalkL.png");
			spriteWalkR = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "WalkR.png");
			//possessed human sprites
			fadedSpriteWalkU = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name + "WalkU.png");
			fadedSpriteWalkD = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name + "WalkD.png");
			fadedSpriteWalkL = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name + "WalkL.png");
			fadedSpriteWalkR = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/faded" + name + "WalkR.png");
		}
		facing = "down";
		possessing = false;
		faded = false;
	}
	
	//get methods
	public ImageIcon getSpriteU(){
		return spriteU;
	}
	public ImageIcon getSpriteWalkU(){
		return spriteWalkU;
	}
	public ImageIcon getSpriteD(){
		return spriteD;
	}
	public ImageIcon getSpriteWalkD(){
		return spriteWalkD;
	}
	public ImageIcon getSpriteL(){
		return spriteL;
	}
	public ImageIcon getSpriteWalkL(){
		return spriteWalkL;
	}
	public ImageIcon getSpriteR(){
		return spriteR;
	}
	public ImageIcon getSpriteWalkR(){
		return spriteWalkR;
	}
	public ImageIcon getFadedSpriteU(){
		return fadedSpriteU;
	}
	public ImageIcon getFadedSpriteWalkU(){
		return fadedSpriteWalkU;
	}
	public ImageIcon getFadedSpriteD(){
		return fadedSpriteD;
	}
	public ImageIcon getFadedSpriteWalkD(){
		return fadedSpriteWalkD;
	}
	public ImageIcon getFadedSpriteL(){
		return fadedSpriteL;
	}
	public ImageIcon getFadedSpriteWalkL(){
		return fadedSpriteWalkL;
	}
	public ImageIcon getFadedSpriteR(){
		return fadedSpriteR;
	}
	public ImageIcon getFadedSpriteWalkR(){
		return fadedSpriteWalkR;
	}
	public String getFacing(){
		return facing;
	}
	public boolean getPossessing(){
		return possessing;
	}
	public boolean getFaded(){
		return faded;
	}
	
	//set methods
	public void setFacing(String facing){
		this.facing = facing;
	}
	public void setPossessing(boolean possessing){
		this.possessing = possessing;
	}
	public void setFaded(boolean faded){
		this.faded = faded;
	}
}