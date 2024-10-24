package studiplayer.audio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ControllablePlayListIterator implements Iterator<AudioFile>{
	private List<AudioFile> playList;
	private int audioNumber = 0;

	
	public ControllablePlayListIterator(List<AudioFile> audioFiles) {
		playList = audioFiles;
	}
	
	
	public ControllablePlayListIterator(List<AudioFile> audioFiles, String search, SortCriterion sortCriterion) {
		playList = audioFiles;
		List<AudioFile> newList = new LinkedList<>(audioFiles);
		if (search == null || search.isEmpty()) {
			newList = new LinkedList<AudioFile>(audioFiles);
		}
		else  {
			newList = new LinkedList<AudioFile>();
			for(AudioFile file : audioFiles) {
				if (file.getTitle().contains(search) || file.getAuthor().contains(search) || file.getAlbum().contains(search)) {
					newList.add(file);
				}
			}
		}
		if (sortCriterion == SortCriterion.ALBUM) {
			newList.sort(new AlbumComparator());
		}
		else if (sortCriterion == SortCriterion.AUTHOR) {
			newList.sort(new AuthorComparator());
		}
		else if (sortCriterion == SortCriterion.DURATION) {
			newList.sort(new DurationComparator());
		}
		else if (sortCriterion == SortCriterion.TITLE) {
			newList.sort(new TitleComparator());
		}
		playList = newList; 
	}

	@Override
	public boolean hasNext() {
		return audioNumber < playList.size();
	}

	@Override
	public AudioFile next() {
		AudioFile nextSong;
		if (playList.isEmpty()) {
			return null;
		}
		else {
			if (audioNumber < playList.size()) {
				nextSong = playList.get(getAudioNumber());
				audioNumber++;
			}
			else {
				setAudioNumber(0);
				nextSong = playList.get(getAudioNumber());
			}
		}
		return nextSong;
	}
	
	public AudioFile previous() {
		AudioFile prevSong;
		if (playList.isEmpty()) {
			return null;
		}
		else {
			if (audioNumber >= 0 && audioNumber < playList.size()) {
				prevSong = playList.get(getAudioNumber());
				audioNumber--;
			}
			else {
				setAudioNumber(playList.size() - 1);
				prevSong = playList.get(getAudioNumber());
			}
		}
		return prevSong;
	}
	
	public AudioFile jumpToAudioFile(AudioFile file) {
		if (playList.contains(file)) {
			
			int jumpTo = playList.indexOf(file);
			int jumpDiff = jumpTo - audioNumber;
			if (jumpDiff >= 0) {
				for (int i = 0; i < jumpDiff ; i++) {
					next();
				}
			}
			else if(jumpDiff < 0) {
				setAudioNumber(0);
				for (int i = 0; i <= jumpTo ; i++) {
					next();
				}
			}
			return playList.get(jumpTo);
		}
		else {
			return null;
		}
	}
	
	public void setAudioNumber(int audioNumber) {
		this.audioNumber = audioNumber;
	}
	
	public int getAudioNumber() {
		return audioNumber;
	}
	
	public AudioFile currentAudioFile() {
		if (playList.isEmpty()) {
			return null;
		}
		else if (audioNumber == playList.size()) {
			setAudioNumber(0);
		}
		return playList.get(getAudioNumber());
	}

}