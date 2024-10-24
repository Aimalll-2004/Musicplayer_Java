package studiplayer.audio;


public class AudioFileFactory {

	public AudioFileFactory() {
			
	}
	
	public static AudioFile createAudioFile(String path) throws NotPlayableException {
		
		if (path.toLowerCase().endsWith(".wav")) {
			return new WavFile(path);
		}
		else if (path.toLowerCase().endsWith(".ogg") || path.toLowerCase().endsWith(".mp3")) {
			return new TaggedFile(path);
		}
		else {
			throw new NotPlayableException(path ,"Unknown suffix for AudioFile " + "\"" + path + "\"");
		}
	}

}
