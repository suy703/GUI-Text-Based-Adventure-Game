public class Artifact extends Items{
	
	String artifactID;
	String artifactName; 
	String artifactDescription; 
	int artifactPrice;
	
	public Artifact(String id, String name, String description, int price) {
		super(id, name, description, price);
		
	}

	public String getArtifactID() {
		return artifactID;
	}

	public void setArtifactID(String artifactID) {
		this.artifactID = artifactID;
	}

	public String getArtifactName() {
		return artifactName;
	}

	public void setArtifactName(String artifactName) {
		this.artifactName = artifactName;
	}

	public String getArtifactDescription() {
		return artifactDescription;
	}

	public void setArtifactDescription(String artifactDescription) {
		this.artifactDescription = artifactDescription;
	}

	public int getArtifactPrice() {
		return artifactPrice;
	}

	public void setArtifactPrice(int artifactPrice) {
		this.artifactPrice = artifactPrice;
	}

}
