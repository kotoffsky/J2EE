package service;
public enum ServiceTypeEnum{
	DEMANDE("demande"),
	OFFRE("offre");
	
	String type = "";
	
	private ServiceTypeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
};
