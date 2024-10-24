package studiplayer.audio;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PlayList implements Iterable<AudioFile> {
	
	
	private LinkedList<AudioFile> audiofiles = new LinkedList<>();
	private String search = "";
	private SortCriterion sortCriterion = SortCriterion.DEFAULT;
	ControllablePlayListIterator controlIt = new ControllablePlayListIterator(audiofiles);
	
	
	public String toString() {
		return audiofiles.toString();
	}
	public PlayList() {

	}

	public PlayList(String m3uPathname) {
		loadFromM3U(m3uPathname);
	}

	public void add(AudioFile file) {
		audiofiles.add(file);
		
		resetList();
	}

	public void remove(AudioFile file) {
		audiofiles.remove(file);
		
		resetList();
	}

	public int size() {
		return audiofiles.size();
	}

	public AudioFile currentAudioFile() {
		return controlIt.currentAudioFile();
	}

	public void nextSong() {

			controlIt.next();			

	}

	public void loadFromM3U(String pathname) {
		getList().clear();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(pathname));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty() || line.startsWith("#")) {
					continue;
				}
				else {
					try {
						getList().add(AudioFileFactory.createAudioFile(line));

					}
					catch (Exception e) {

					}
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			try {
				scanner.close();
			}
			catch (Exception e) {
				// ignore; probably because file could not be opened
			}
		}
	}

	public void saveAsM3U(String pathname) {
		FileWriter writer = null;
		String sep = System.getProperty("line.separator");

		try {
			writer = new FileWriter(pathname);
			for (AudioFile line : getList()) {
				writer.write(line.getPathname() + sep);

			}
		} 
		catch (IOException e) {
			throw new RuntimeException("Unable to write file " + pathname + "!");
		} 
		finally {
			try {
				writer.close();
			}
			catch (Exception e) {
				
			}
		}
	}

	public List<AudioFile> getList() {
		return audiofiles;
	}

	public void setSortCriterion(SortCriterion sort) {
		this.sortCriterion = sort;
		
		resetList();
	}
	
	public SortCriterion getSortCriterion() {
			return sortCriterion;
	}

	public void setSearch(String value) {
		this.search = value;
		
		resetList();
	}
	
	public String getSearch() {
		return search;
	}

	public AudioFile jumpToAudioFile(AudioFile file) {

		return controlIt.jumpToAudioFile(file);
	}

	public Iterator<AudioFile> iterator() {
		return new ControllablePlayListIterator(getList(), getSearch(), getSortCriterion());
	}
	public void resetList() {
		controlIt = new ControllablePlayListIterator(getList(), getSearch(), getSortCriterion());
		controlIt.setAudioNumber(0);
	}
}
