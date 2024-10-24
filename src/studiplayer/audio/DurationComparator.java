package studiplayer.audio;

import java.util.Comparator;

public class DurationComparator implements Comparator<AudioFile> {

	@Override
	public int compare(AudioFile audio1, AudioFile audio2) {
		if (audio1.getDuration() > audio2.getDuration()) {
			return 1;
		}
		
		else if (audio1.getDuration() < audio2.getDuration()) {
			return -1;
		}
		
		else {
			return 0;
		}
	}

}
