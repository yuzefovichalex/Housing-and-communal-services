package by.grsu.yuzefovich.datamodel;

import java.io.Serializable;

public class Request extends AbstractModel implements Serializable {
	
	private String typeOfWork;
	private Integer scopeOfWork;
	private Integer leadTime;
	private Brigade brigade;
	private Boolean isAccepted;
	
	public String getTypeOfWork() {
		return typeOfWork;
	}
	
	public void setTypeOfWork(final String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}
	
	public Integer getScopeOfWork() {
		return scopeOfWork;
	}
	
	public void setScopeOfWork(final Integer scopeOfWork) {
		this.scopeOfWork = scopeOfWork;
	}
	
	public Integer getLeadTime() {
		return leadTime;
	}
	
	public void setLeadTime(final Integer leadTime) {
		this.leadTime = leadTime;
	}
	
	public Brigade getBrigade() {
		return brigade;
	}
	
	public void setBrigade(final Brigade brigade) {
		this.brigade = brigade;
	}
	
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	
	public void setIsAccepted(final Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	public Request() {
		
	}
	
	public Request(Long id) {
		this.setId(id);
	}
	
	public Request(String typeOfWork, int scopeOfWork, int leadTime, boolean isAccepted) {
		this.typeOfWork = typeOfWork;
		this.scopeOfWork = scopeOfWork;
		this.leadTime = leadTime;
		this.isAccepted = isAccepted;
		brigade = null;
	}
	public Request(String typeOfWork, int scopeOfWork, int leadTime, int numberOfWorkers) {
		this.typeOfWork = typeOfWork;
		this.scopeOfWork = scopeOfWork;
		this.leadTime = leadTime;
		brigade = new Brigade(numberOfWorkers);
	}

}
