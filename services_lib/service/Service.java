package service;
import java.util.Date;


public class Service {
	private Long id;
	private String titre;
	private String description;
	private String categorie;
	private type typeService; 
	private Date dateLimite;
	
	
	
	public Service() {
		this("Service Standard","Description","Infomatique",type.OFFRE,new Date());
	}
	public Service(String titre, String description, String categorie,
			type typeService, Date dateLimite) {
		super();
		this.titre = titre;
		this.description = description;
		this.categorie = categorie;
		this.typeService = typeService;
		this.dateLimite = dateLimite;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public Date getDateLimite() {
		return dateLimite;
	}
	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}
	public type getTypeService() {
		return typeService;
	}
	public void setTypeService(type typeService) {
		this.typeService = typeService;
	}
	
}
