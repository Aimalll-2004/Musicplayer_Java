package studiplayer.audio;
import java.io.File;

import studiplayer.basic.WavParamReader;

public class WavFile extends SampledFile {

	public WavFile() {
		
	}

	public WavFile(String path) throws NotPlayableException {
		super(path);
		readAndSetDurationFromFile();
		File file = new File(getPathname());
		if (!file.canRead() ) {
			throw new NotPlayableException(path,"The file is not readable!");
		}
	}

	public void readAndSetDurationFromFile() throws NotPlayableException {
		try {
		WavParamReader.readParams(getPathname());
		}
		catch (Exception e) {
			throw new NotPlayableException(getPathname(), "Parameters cannot be read!");
		}
		long numberOfFrames = WavParamReader.getNumberOfFrames();
		float frameRate = WavParamReader.getFrameRate();
		this.duration = computeDuration(numberOfFrames, frameRate);
	}
	public String toString() {
		return super.toString().trim() + " - " + formatDuration();
	}
	public static long computeDuration(long numberOfFrames, float frameRate) {
		return (long) Math.round((numberOfFrames/frameRate) * 1000000);
		
	}


}
