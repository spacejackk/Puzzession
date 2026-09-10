//created by Cassidy Demir on 10/20/25
//game jam game objects

//file package
package gamejam;

//imports
import java.io.*;
import javax.swing.*;
import java.io.File;

public class GameObject{
	//attributes
	private String name;
	private ImageIcon sprite;
	private ImageIcon sprite2;
	private ImageIcon spriteOff;
	private boolean on;
	
	//constructor method
	public GameObject(String name, boolean light, boolean on){
		this.name = name;
		sprite = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + ".png");
		//object has 2 sprites
		if (name.equals("radio") || name.equals("exit")){
			sprite2 = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "2.png");
		}
		//object has on and off state
		if (light == true){
			spriteOff = new ImageIcon(new File(System.getProperty("user.dir"), "sprites").getAbsolutePath() + "/" + name + "Off.png");
		}
		this.on = on;
	}
	
	//get methods
	public String getName(){
		return name;
	}
	public ImageIcon getSprite(){
		return sprite;
	}
	public ImageIcon getSprite2(){
		return sprite2;
	}
	public ImageIcon getSpriteOff(){
		return spriteOff;
	}
	public boolean getOn(){
		return on;
	}
	
	//set methods
	public void setOn(boolean on){
		this.on = on;
	}
}