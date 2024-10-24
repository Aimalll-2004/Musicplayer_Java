package studiplayer.audio;
import java.io.File;
import java.util.Map;

import studiplayer.basic.TagReader;

public class TaggedFile extends SampledFile {
	
	private String album;


	public TaggedFile() {
		this.album = "";
	}

	public TaggedFile(String path) throws NotPlayableException {
		super(path);
		readAndStoreTags();
		File file = new File(getPathname());
		if (!file.canRead() ) {
			throw new NotPlayableException(path,"The file is not readable!");
		}
	}
	
	public String getAlbum() {
		return album == null  ? "" : album.trim();
	}
	
	public void readAndStoreTags() throws NotPlayableException {
		try {
			TagReader.readTags(getPathname());
		}catch (Exception e) {
			throw new NotPlayableException(getPathname(), "tag not readable!");
		}
		// Finds title, author, album and duration and stores in respective attributes
		Map<String, Object> tagMap = TagReader.readTags(getPathname()); 
	
		if((String) tagMap.get("title") != null) {
			this.title = (String) tagMap.get("title");
		}
		if((String) tagMap.get("album") != null) {
			this.album = (String) tagMap.get("album");
		} 
		if((String) tagMap.get("author") != null) {
			this.author = (String) tagMap.get("author");
		}
		
			this.duration = (long) tagMap.get("duration");
		

	}
	
	public String toString() {
		
		if (getAlbum().isEmpty()) {
			return super.toString() + " - " + formatDuration();
		}
		else {
			return super.toString() + " - " + getAlbum() + " - " + formatDuration();
		}
	}

	

}
