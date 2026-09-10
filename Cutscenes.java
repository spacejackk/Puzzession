//created by Cassidy Demir on 10/20/25
//game jam game cutscenes

//file package
package gamejam;

//imports
import java.io.*;
import javax.swing.*;
import java.io.File;

public class Cutscenes{
	//attributes
	private ImageIcon menu;
	private ImageIcon cutscene1A;
	private ImageIcon cutscene1B;
	private ImageIcon cutscene1C;
	private ImageIcon cutscene1D;
	private ImageIcon cutscene2A;
	private ImageIcon cutscene2B;
	private ImageIcon cutscene2C;
	private ImageIcon credits1;
	private ImageIcon credits2;
	private ImageIcon credits3;
	private ImageIcon credits4;
	private ImageIcon credits5;
	
	//constructor method
	public Cutscenes(){
		//main menu
		menu = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/menu.png");
		//starting cutscene
		cutscene1A = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/cut1frame1.png");
		cutscene1B = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/cut1frame2.png");
		cutscene1C = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/cut1frame3.png");
		cutscene1D = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/cut1frame4.png");
		//ending cutscene
		cutscene2A = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/cut2frame1.png");
		cutscene2B = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/cut2frame2.png");
		cutscene2C = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/cut2frame3.png");
		//credits
		credits1 = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/credits1.png");
		credits2 = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/credits2.png");
		credits3 = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/credits3.png");
		credits4 = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/credits4.png");
		credits5 = new ImageIcon(new File(System.getProperty("user.dir"), "cutscenes").getAbsolutePath() + "/credits5.png");
	}
	
	//get methods
	public ImageIcon getMenu(){
		return menu;
	}
	public ImageIcon get1A(){
		return cutscene1A;
	}
	public ImageIcon get1B(){
		return cutscene1B;
	}
	public ImageIcon get1C(){
		return cutscene1C;
	}
	public ImageIcon get1D(){
		return cutscene1D;
	}
	public ImageIcon get2A(){
		return cutscene2A;
	}
	public ImageIcon get2B(){
		return cutscene2B;
	}
	public ImageIcon get2C(){
		return cutscene2C;
	}
	public ImageIcon getCredits1(){
		return credits1;
	}
	public ImageIcon getCredits2(){
		return credits2;
	}
	public ImageIcon getCredits3(){
		return credits3;
	}
	public ImageIcon getCredits4(){
		return credits4;
	}
	public ImageIcon getCredits5(){
		return credits5;
	}
}