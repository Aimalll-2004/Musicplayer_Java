package studiplayer.audio;
import java.io.File;

import studiplayer.basic.BasicPlayer;

public abstract class SampledFile extends AudioFile {
	protected long duration;
	public SampledFile() {
			
	}

	public SampledFile(String path) throws NotPlayableException{
		super(path);
		File file = new File(getPathname());
		if (!file.canRead() ) {
			throw new NotPlayableException(path,"The file is not readable!");
		}
	}
	
	public void play() throws NotPlayableException {
		try {
			BasicPlayer.play(getPathname());
		}
		catch (Exception e) {
			throw new NotPlayableException(getPathname(), "Error occured!");
		}
	}
	
	public void togglePause() {
		BasicPlayer.togglePause();
	}
	
	public void stop() {		
		BasicPlayer.stop();
	}
	
	public String formatDuration() {
		return timeFormatter(getDuration());
	}

	public String formatPosition() {
		return timeFormatter(BasicPlayer.getPosition());
	}
	
	public static String timeFormatter(long timeInMicroSeconds) throws RuntimeException {
		String formattedTime;
		try {
			if (timeInMicroSeconds < 0) throw new RuntimeException("Time cannot be negative!");
				long seconds = timeInMicroSeconds/1000000;
				long minutes = 0;
				while (seconds >= 60) {
					minutes ++;
					seconds -= 60;
				}
			if (minutes > 99) throw new RuntimeException("Time limit exceeded!");
				
			formattedTime = String.format("%02d:%02d", minutes, seconds);
		}
		catch (Exception e) {
			throw new RuntimeException("Invalid values!");
		}
		return formattedTime;
	}
	
	public long getDuration() {
		return duration;
		
	}
}
