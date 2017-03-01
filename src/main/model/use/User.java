package main.model.use;

import java.util.Random;

public class User {
	private String name;
	private int idNumber;
	
	public User(String name){
		this.name = name;
		this.setIdNumber();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getIdNumber() {
		return idNumber;
	}

	// TODO: generate unique ID number for each new user
	private void setIdNumber() {
		idNumber = -1;
		Random rand = new Random(); 
		idNumber = rand.nextInt(20) + 1;
	}
}
