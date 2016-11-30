package info.michalak.aop.model;

public class Triangle {

	private String name;

	public String getName() {
		return name;
	}
	
	@Loggable
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
}
