package studiplayer.audio;

public enum SortCriterion {
	DEFAULT("Default"),
	AUTHOR("Author"),
	TITLE("Title"),
	ALBUM("Album"),
	DURATION("Duration");


	private final String displayName;
	
	private SortCriterion(String displayName) {
		this.displayName = displayName;
	}
	
	public String toString() {
		return displayName;
	}
}




