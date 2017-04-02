package main.model.use;

public class UserImpl implements User {
	private String name;
	private int idNumber;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int id) {
		this.idNumber = id;
	}
}
