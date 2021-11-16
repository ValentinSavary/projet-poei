package projetMusic.entity;

public enum Genre {
	Rock("Rock"), Metal("Metal"), Classical("Classical"), Pop("Pop"), Rap("Rap"), RnB("RnB"),
	VarieteFrancaise("Variete francaise");

	private String genre;

	// Constructeurs

	private Genre(String genre) {
		this.genre = genre;
	}

	private Genre() {
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}