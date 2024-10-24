package studiplayer.audio;

import java.io.File;

public abstract class AudioFile {
	private String pathname;
	private String filename;
	protected String author;
	protected String title;
	
	
	public AudioFile() {
		this.pathname = "";
		this.filename = "";
		this.author = "";
		this.title = "";
	}
	
	private boolean isWindows() { 
		return System.getProperty("os.name").toLowerCase() 
		.indexOf("win") >= 0; 
	}
	
	public AudioFile(String path) throws NotPlayableException {
		parsePathname(path);
		parseFilename(this.filename);
		File file = new File(getPathname());
		if (!file.canRead() ) {
			throw new NotPlayableException(path,"The file is not readable!");
		}
	}

	public void parsePathname(String path) {
			
			if (path.indexOf('/') == -1) {		
				
				if (path.trim().startsWith("-")) {
					this.pathname = "-";
					this.filename = "-";	
				}
				else {
					this.pathname = path.trim();
					this.filename = path.trim();
				}
			}
			String iterateName = "";
			if (path.trim().contains("/") || path.trim().contains("\\")) {
				if (path.startsWith(" ")) {
					if (isWindows()) {
						this.pathname = path.replace("/", "\\").trim();
					}
					else {
						this.pathname = path;
					}
					this.filename = path.substring(path.lastIndexOf("/") + 1, path.length()).trim();
					
				}
				else {
					
					for (int i = 0; i < path.length(); i ++) {
						if (isWindows()) {
							iterateName += path.replace('/', '\\').charAt(i);
						}
						else {
							iterateName += path.charAt(i);
						}
						if (i + 1 == path.length() && path.charAt(i) == '/' || i + 1 == path.length() && path.charAt(i) == '\\') { 
							continue;
						}
						int secondIndex = i + 1;
						if (path.charAt(i) == '/' || path.charAt(i) == '\\') {
							while (path.charAt(i) == '/' && path.charAt(secondIndex) == '/' || path.charAt(i) == '\\' && path.charAt(secondIndex) == '\\') {
								i++ ;
								secondIndex++;
							}
						}
					}
					this.pathname = iterateName;
					
					if (isWindows()) {
						this.pathname = iterateName.replace('/', '\\').trim();
						
					}
					else {
						if (path.contains(":")) {
							this.pathname = '/' + this.pathname.charAt(0) + this.pathname.substring(2); 
						}
						else {
							this.pathname = iterateName.replace('\\', '/').trim();
						}
					}
					
					
					
					if (this.pathname.contains("\\")) {
						this.filename = iterateName.substring(this.pathname.lastIndexOf('\\') + 1).trim();
					}
					else {
						this.filename = iterateName.substring(this.pathname.lastIndexOf('/') + 1).trim();
					}

				}
					
			}
							
		}
	
	public String getPathname() {
		return pathname;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void parseFilename (String file) {
		
		if (file.endsWith(" ")) {			
			if (file == " - " || file == " ") {
				this.author = "";
				this.title = "";
			}
			else {
				this.author = file.substring(0, file.indexOf('-') - 1).trim();
				this.title = file.substring(file.indexOf('-') + 1, file.indexOf('.') - 1).trim();
			}
		}
		
		else if (file.contains("-") == false && file.contains(".")) {
			if (file.isEmpty()) {
				this.author = "";
				this.title = "";
			}
			else {
			this.author = "";
			this.title = file.substring(0, file.lastIndexOf('.'));
			}
		}
		
		else if (file.startsWith(".")) {
			this.author = "";
			this.title = "";
		}
		
		else if (file.endsWith(".")) {
			this.author = file.substring(0, file.indexOf('-') - 1).trim();
			this.title = file.substring(file.indexOf('-') + 1, file.indexOf('.')).trim();
		}
		
		else if (file == "-") {
			this.author = "";
			this.title = "-";
		}
		
		else {
			if (file.contains(" - ")) {
				
				if (file.indexOf('.') == 1) {
					this.author = file.substring(0, file.indexOf('-')).trim();
					this.title = file.substring(file.indexOf('-') + 1, file.lastIndexOf('.')).trim();
				}
				
				else if (file.contains("/")) {
					this.author = file.substring(file.lastIndexOf('/') + 1, file.lastIndexOf('-')).trim();
					this.title = file.substring(file.indexOf('-') + 1, file.lastIndexOf('.')).trim();
				}
				else {
					this.author = file.substring(0, file.indexOf(" - ")).trim();
					this.title = file.substring(file.indexOf(" - ") + 2, file.lastIndexOf('.')).trim();
				}
			}
			else if (file.contains(".")){
				
				this.author = "";
				this.title = file.substring(0, file.indexOf('.'));
			}
			
			else {
				this.author = "";
				this.title = file;
			}
		}
		
	}
	public String getAuthor() {
		return author == null ? "" : author.trim();
	}
	
	public String getTitle() {
		return title == null ? "" : title.trim();
	}
	
	public String toString() {
	
		if (getAuthor().isEmpty()) {
			return getTitle();
		}
		else {
			return getAuthor() + " - " + getTitle();
		}
		
	}

	public abstract void play() throws NotPlayableException;
	
	public abstract void togglePause();
	
	public abstract void stop();
	
	public abstract String formatDuration();
	
	public abstract String formatPosition();

	public long getDuration() {
		return 0;
	}

	public String getAlbum() {
		return "";
	}
	

}


	


