package main.model.maintenance;

public class ManagerImpl implements Manager {
	
	private String name;
	private int idNumber;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getIdNumber() {
		return idNumber;
	}

	@Override
	public void setIdNumber(int id) {
		this.idNumber = id;
	}

}
