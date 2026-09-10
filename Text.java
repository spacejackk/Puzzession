//created by Cassidy Demir on 10/20/25
//game jam game text

//file package
package gamejam;

//imports
import java.io.*;
import javax.swing.*;
import java.io.File;

public class Text{
	//attributes
	private ImageIcon lvl1;
	private ImageIcon lvl2;
	private ImageIcon lvl3;
	private ImageIcon lvl4;
	private ImageIcon lvl5;

	
	//constructor method
	public Text(){
		lvl1 = new ImageIcon(new File(System.getProperty("user.dir"), "text").getAbsolutePath() + "/lvl1.png");
		lvl2 = new ImageIcon(new File(System.getProperty("user.dir"), "text").getAbsolutePath() + "/lvl2.png");
		lvl3 = new ImageIcon(new File(System.getProperty("user.dir"), "text").getAbsolutePath() + "/lvl3.png");
		lvl4 = new ImageIcon(new File(System.getProperty("user.dir"), "text").getAbsolutePath() + "/lvl4.png");
		lvl5 = new ImageIcon(new File(System.getProperty("user.dir"), "text").getAbsolutePath() + "/lvl5.png");
	}
	
	//get methods
	public ImageIcon getLvl1(){
		return lvl1;
	}
	public ImageIcon getLvl2(){
		return lvl2;
	}
	public ImageIcon getLvl3(){
		return lvl3;
	}
	public ImageIcon getLvl4(){
		return lvl4;
	}
	public ImageIcon getLvl5(){
		return lvl5;
	}
}