package studiplayer.audio;

import java.util.Comparator;

public class TitleComparator implements Comparator<AudioFile> {

	@Override
	public int compare(AudioFile audio1, AudioFile audio2) {
		int titleComparison = audio1.getTitle().compareTo(audio2.getTitle());
		if (titleComparison > 0) {
			return 1;
		}
		else if (titleComparison < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
