package com.sendemail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SoundCaller {
	
	public static void main(String args[]) {
		try {
		
		FileInputStream fileInputStream = new FileInputStream("stop.mp3");
		Player player = new Player(fileInputStream);
		player.play();
		System.out.println("Song is playing");
	}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		
		}
		catch(JavaLayerException e) {
			e.printStackTrace();
		}
		

}
	
}
