package formation.sopra.projetMusicBoot.entities;

public enum Genre {
	Rock("Rock"), Metal("Metal"), TechnicalDeathcore("Technical Deathcore"), Classical("Classical"), Pop("Pop"), Rap("Rap"), RnB("RnB"), VarieteFrancaise("VarieteFrancaise");
	
	private String name;

	private Genre(String name) {
		this.name = name;
	}

	private Genre() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}