import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	
	// Gun shot sound
	public void shoot() {
		try {
			
			
			File soundFile = new File("colt45.wav");
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundFile));
			clip.start();
			
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Brass falling
	public void brassFalling() {
		try {
			
			
			File soundFile = new File("brass.wav");
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundFile));
			clip.start();
			
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// door opening
	public void doorOpen() {
		try {
			File soundFile = new File("doorOpening.wav");
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundFile));
			clip.start();
			
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
