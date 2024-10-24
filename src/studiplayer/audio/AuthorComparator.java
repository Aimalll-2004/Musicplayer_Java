package studiplayer.audio;

import java.util.Comparator;

public class AuthorComparator implements Comparator<AudioFile>{

	@Override
	public int compare(AudioFile audio1, AudioFile audio2) {
		int authorComparison = audio1.getAuthor().compareTo(audio2.getAuthor());
		if (authorComparison > 0) {
			return 1;
		}
		else if (authorComparison < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
