package studiplayer.audio;

import java.util.Comparator;

public class AlbumComparator implements Comparator<AudioFile> {

	@Override
	public int compare(AudioFile audio1, AudioFile audio2) {
		if (audio1 instanceof TaggedFile && !(audio2 instanceof TaggedFile)) {
			return 1;
		}
		
		else if (!(audio1 instanceof TaggedFile) && (audio2 instanceof TaggedFile))
			return -1;
		
		else {
			return 0;
		}
	}
}


