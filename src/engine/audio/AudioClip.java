package engine.audio;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioClip {
	
	private Clip clip;
	private int state;

	public AudioClip(String path) {
		this.state = state;
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			AudioFormat baseFormate = ais.getFormat();
			AudioFormat decodeFormate = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					baseFormate.getSampleRate(), 16, baseFormate.getChannels(), baseFormate.getChannels() * 2,
					baseFormate.getSampleRate(), false);
			
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormate, ais);
			
			clip = AudioSystem.getClip();
			clip.open(dais);
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
	
	public int getState() {
		return state;
	}
}
