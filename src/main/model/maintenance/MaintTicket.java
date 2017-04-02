package main.model.maintenance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface MaintTicket {
	
	public String getState(LocalDateTime atTime);
	public double calcCost();
	public String getDescription();
	public void setDescription(String description);
	public LocalDateTime getRequestTime();
	public void setRequestTime(LocalDateTime requestTime);
	public LocalDateTime getOrderTime();
	public void setOrderTime(LocalDateTime orderTime);
	public LocalDateTime getResolveTime();
	public void setResolveTime(LocalDateTime resolveTime);
	public List<Material> getMaterials();
	public void setMaterials(List<Material> materials);
	public void addMaterial(Material material);
	public void removeMaterial(Material material);
	public Set<Worker> getWorkers();
	public void setWorkers(Set<Worker> workers);
	public void addWorker(Worker worker);
	public void removeWorker(Worker worker);
	
}
